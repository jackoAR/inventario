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

import com.epmtpq.inventario.model.Parada;
import com.epmtpq.inventario.service.IParadaService;

@Controller
public class ParadaController implements Serializable{

	/**
	 * 
	 */
	@Autowired
	private IParadaService srv;
	
	private static final long serialVersionUID = 1L;
	
	@GetMapping("/listaparada")
	public String verCliente(Model model) {
		model.addAttribute("ListaParada", srv.listaParada());
		return "/parada/listaParada";
		
	}
	
	@GetMapping("/nuevoparada")
	public String crearParada(Model model) {
		Parada nuevo = new Parada();
		model.addAttribute("nuevo", nuevo);
		return "/parada/nuevoParada";
	}
	
	@GetMapping("/nuevoparada/{idParada}")
	public String editarParada(@PathVariable("idParada") Integer idParada, Model model) {
		Parada existe = null;
		if(idParada>0) {
			existe = srv.buscarPorId(idParada);
		}else {
			return "redirect:/listaParada";
		}
		model.addAttribute("nuevo",existe);
		return "/parada/nuevoParada";
	}
	
	@PostMapping("/guardarparada")
	public String guardarParada(@ModelAttribute("nuevo") Parada nuevo) {
		srv.insertarParada(nuevo);
		return "redirect:/listaParada";
	}
	
	@RequestMapping("/eliminarparada/{idParada}")
	public String eliminarParada(@PathVariable("idParada") Integer idParada) {
		//TODO: process POST request
		if(idParada > 0 ) {
			srv.eliminaParada(idParada);
		}
		
		return "redirect:/listaParada";
	}

}
