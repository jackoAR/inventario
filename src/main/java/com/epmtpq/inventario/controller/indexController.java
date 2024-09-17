package com.epmtpq.inventario.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class indexController {
	@GetMapping("/")
	public String abrirIndex() {
		// ruta del URL
		return "plantilla/blank";// nombre fisico del archivo

	}

}
