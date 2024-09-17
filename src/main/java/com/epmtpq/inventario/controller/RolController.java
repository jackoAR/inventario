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

import com.epmtpq.inventario.model.Rol;
import com.epmtpq.inventario.service.IRolService;

@Controller
public class RolController implements Serializable{

	/**
	 * 
	 */
	@Autowired
	private IRolService srv;
	
	private static final long serialVersionUID = 1L;
	
	@GetMapping("/listarol")
	public String verCliente(Model model) {
		model.addAttribute("ListaRol", srv.listaRol());
		return "/rol/listaRol";
		
	}
	
	@GetMapping("/nuevorol")
	public String crearRol(Model model) {
		Rol nuevo = new Rol();
		model.addAttribute("nuevo", nuevo);
		return "/rol/nuevoRol";
	}
	
	@GetMapping("/nuevorol/{idRol}")
	public String editarRol(@PathVariable("idRol") Integer idRol, Model model) {
		Rol existe = null;
		if(idRol>0) {
			existe = srv.buscarPorId(idRol);
		}else {
			return "redirect:/listaRol";
		}
		model.addAttribute("nuevo",existe);
		return "/rol/nuevoRol";
	}
	
	@PostMapping("/guardarrol")
	public String guardarRol(@ModelAttribute("nuevo") Rol nuevo) {
		srv.insertarRol(nuevo);
		return "redirect:/listaRol";
	}
	
	@RequestMapping("/eliminarrol/{idRol}")
	public String eliminarRol(@PathVariable("idRol") Integer idRol) {
		//TODO: process POST request
		if(idRol > 0 ) {
			srv.eliminarRol(idRol);
		}
		
		return "redirect:/listaRol";
	}

}
