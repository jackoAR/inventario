package com.epmtpq.inventario.controller;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.epmtpq.inventario.model.Usuario;
import com.epmtpq.inventario.service.IUsuarioService;

@Controller
public class UsuarioController implements Serializable{

	/**
	 * 
	 */
	@Autowired
	private IUsuarioService srv;
	
	private static final long serialVersionUID = 1L;
	
	@GetMapping("/listausuario")
	public String verCliente(Model model) {
		model.addAttribute("ListaUsuario", srv.listaUsuario());
		return "/usuario/listaUsuario";
		
	}
	
	@GetMapping("/nuevousuario")
	public String crearUsuario(Model model) {
		Usuario nuevo = new Usuario();
		model.addAttribute("nuevo", nuevo);
		return "/usuario/nuevoUsuario";
	}
	
	@GetMapping("/nuevousuario/{idUsuario}")
	public String editarUsuario(@PathVariable("idUsuario") Integer idUsuario, Model model) {
		Usuario existe = null;
		if(idUsuario>0) {
			existe = srv.buscarPorId(idUsuario);
		}else {
			return "redirect:/listaUsuario";
		}
		model.addAttribute("nuevo",existe);
		return "/usuario/nuevoUsuario";
	}
	
	@PostMapping("/guardarusuario")
	public String guardarUsuario(@ModelAttribute("nuevo") Usuario nuevo) {
		srv.insertarUsuario(nuevo);
		return "redirect:/listaUsuario";
	}
	
	@RequestMapping("/eliminarusuario/{idUsuario}")
	public String eliminarUsuario(@PathVariable("idUsuario") Integer idUsuario) {
		//TODO: process POST request
		if(idUsuario > 0 ) {
			srv.eliminarUsuario(idUsuario);
		}
		
		return "redirect:/listaUsuario";
	}

}
