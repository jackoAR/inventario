package com.epmtpq.inventario.controller;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.epmtpq.inventario.model.Corredor;
import com.epmtpq.inventario.service.ICorredorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CorredorController implements Serializable{

	/**
	 * 
	 */
	@Autowired
	private ICorredorService srv;
	
	private static final long serialVersionUID = 1L;
	
	@GetMapping("/listacorredor")
	public String verCliente(Model model) {
		model.addAttribute("ListaCorredor", srv.listaCorredor());
		return "/corredor/listaCorredor";
		
	}
	
	@GetMapping("/nuevocorredor")
	public String crearCorredor(Model model) {
		Corredor nuevo = new Corredor();
		model.addAttribute("nuevo", nuevo);
		return "/corredor/nuevoCorredor";
	}
	
	@GetMapping("/nuevocorredor/{idCorredor}")
	public String editarCorredor(@PathVariable("idCorredor") Integer idCorredor, Model model) {
		Corredor existe = null;
		if(idCorredor>0) {
			existe = srv.buscarPorId(idCorredor);
		}else {
			return "redirect:/listaCorredor";
		}
		model.addAttribute("nuevo",existe);
		return "/corredor/nuevoCorredor";
	}
	
	@PostMapping("/guardarcorredor")
	public String guardarCorredor(@ModelAttribute("nuevo") Corredor nuevo) {
		srv.insertarCorredor(nuevo);
		return "redirect:/listaCorredor";
	}
	
	@RequestMapping("/eliminarcorredor/{idCorredor}")
	public String eliminarCorredor(@PathVariable("idCorredor") Integer idCorredor) {
		//TODO: process POST request
		if(idCorredor > 0 ) {
			srv.eliminarCorredor(idCorredor);
		}
		
		return "redirect:/listaCorredor";
	}


}
