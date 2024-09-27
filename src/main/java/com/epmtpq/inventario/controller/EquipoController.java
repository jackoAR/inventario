package com.epmtpq.inventario.controller;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.epmtpq.inventario.model.Equipo;
import com.epmtpq.inventario.model.EquipoDTO;
import com.epmtpq.inventario.model.Parada;
import com.epmtpq.inventario.service.ICorredorService;
import com.epmtpq.inventario.service.IEquipoService;
import com.epmtpq.inventario.service.IImagenService;
import com.epmtpq.inventario.service.IParadaService;

@Controller
public class EquipoController implements Serializable {

	/**
	 * 
	 */
	@Autowired
	private IEquipoService srv;
	@Autowired
	private ICorredorService srvCorredor;
	@Autowired
	private IParadaService srvParada;
	@Autowired
	private IImagenService srvImage;

	private static final long serialVersionUID = 1L;

	@GetMapping("/listaequipo")
	public String verEquipo(Model model) {

		model.addAttribute("listaCorredor", srvCorredor.listaCorredor());

		return "/equipo/listaEquipo";

	}

	/*
	 * @GetMapping("/filtroequipo/{paradaId}") public
	 * ResponseEntity<List<EquipoDTO>> filtrarEquipo(@PathVariable("paradaId")
	 * Integer paradaId) {
	 * 
	 * Parada parada = srvParada.buscarPorId(paradaId);
	 * 
	 * if (parada == null) { return new ResponseEntity<>(HttpStatus.NOT_FOUND); }
	 * 
	 * List<EquipoDTO> equipoDTOs = parada.getListaEquipo().stream() .map(equipo ->
	 * new EquipoDTO(equipo.getIdEquipo(), equipo.getSerial(), equipo.getMarca(),
	 * equipo.getHost_Name(), equipo.getModelo(), equipo.getIP(),
	 * equipo.getVersion(), equipo.getDisponibles(), equipo.getUsado(),
	 * equipo.getDisponibles2(), equipo.getUsado3(), equipo.getEstado(),
	 * equipo.getCriticidad(), equipo.getRegistroDeCambio(),
	 * equipo.getMantenimiento(), equipo.getTipoEquipo()))
	 * .collect(Collectors.toList());
	 * 
	 * return new ResponseEntity<>(equipoDTOs, HttpStatus.OK);
	 * 
	 * }
	 */

	@GetMapping("/filtroequipo/{paradaId}")
	public String filtrarEquipo(@PathVariable("paradaId") Integer paradaId, Model model) {

		Parada parada = srvParada.buscarPorId(paradaId);

		if (parada == null) {
			return "No se encontró la parada";
		}

		List<EquipoDTO> equipoDTOs = parada.getListaEquipo().stream()
				.map(equipo -> new EquipoDTO(
						equipo.getIdEquipo(), 
						equipo.getSerial(), 
						equipo.getMarca(),
						equipo.getHost_Name(), 
						equipo.getModelo(), 
						equipo.getIP(), 
						equipo.getVersion(),
						equipo.getPuertos_Disponibles_Fibra(),
						equipo.getPuertos_Usados_Fibra(),
						equipo.getPuertos_Disponibles_Cobre(),
						equipo.getPuertos_Usados_Cobre(),
						equipo.getCod_Bien(),
						equipo.getUlt_Actividad(),						
						equipo.getEstado(), 
						equipo.getCriticidad(), 
						equipo.getRegistroDeCambio(),
						equipo.getMantenimiento(), 
						equipo.getTipoEquipo(),
						equipo.getPathMinio(),
						equipo.getDescripcion(),
						equipo.getUlt_Modificacion(), 
						equipo.getFkParada().getId()))
				.collect(Collectors.toList());

		model.addAttribute("ListaEquipo", equipoDTOs);

		return "/equipo/fragmentos :: equipoTable";

	}

	@GetMapping("/nuevoequipo")
	public String crearEquipo(Model model) {
		model.addAttribute("listaCorredor", srvCorredor.listaCorredor());
		model.addAttribute("listaParada", srvParada.listaParada());
		model.addAttribute("criticidades", Equipo.EquipoCriticidad.values());
		model.addAttribute("estados", Equipo.EstadoEquipo.values());
		model.addAttribute("tipos", Equipo.TipoEquipo.values());
		Equipo nuevo = new Equipo();
		model.addAttribute("nuevo", nuevo);
		return "/equipo/nuevoEquipo";
	}

	@GetMapping("/nuevoequipo/{idEquipo}/{fkParada}")
	public String editarEquipo(@PathVariable("idEquipo") Integer idEquipo, @PathVariable Integer fkParada,
			Model model) {
		Equipo existe = null;
		String carpetaParada = "";
		String carpetaCorredor = "";
		if (idEquipo > 0 && fkParada > 0) {
			existe = srv.buscarPorId(idEquipo);
			Parada parada = srvParada.buscarPorId(fkParada);
			carpetaCorredor = parada.getFkCorredor().getNombre();
			carpetaParada = parada.getNombre();
			
		} else {
			return "redirect:/listaequipo";
		}

		// TRANSFORMACION DE FORMATOS DE FECHAS
		if (existe != null) {
			System.out.println(existe.getMantenimiento());
		}

		model.addAttribute("nuevo", existe);
		model.addAttribute("listaCorredor", srvCorredor.listaCorredor());
		model.addAttribute("listaParada", srvParada.listaParada());
		model.addAttribute("criticidades", Equipo.EquipoCriticidad.values());
		model.addAttribute("estados", Equipo.EstadoEquipo.values());
		model.addAttribute("tipos", Equipo.TipoEquipo.values());
		model.addAttribute("carpetaCorredor", carpetaCorredor);
		model.addAttribute("carpetaParada", carpetaParada);
		return "/equipo/nuevoEquipo";

	}

	@PostMapping("/guardarequipo")
	public String guardarEquipo(@ModelAttribute("nuevo") Equipo nuevo,
			@RequestParam("carpetaCorredor") String carpetaCorredor,
			@RequestParam("carpetaParada") String carpetaParada,
			@RequestParam("file") MultipartFile file) {

		try {
				if (!file.isEmpty()) {
					
//					String nombreOriginal = file.getOriginalFilename();
					int idEquipo = nuevo.getIdEquipo();
					String nombreFoto = Integer.toString(idEquipo);
					
					System.out.println(nombreFoto);
										
					srvImage.uploadPhoto(carpetaCorredor, carpetaParada, nombreFoto, file);
					
					System.out.println("Archivo subido con éxito: " + nombreFoto);
															
					
					System.out.println("DIRECCION DE UPLOAD IMG: " + carpetaCorredor + "/" + carpetaParada);					
					
				}else {
					
					System.out.println("El archivo está vacío.");					
				}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		nuevo.setUlt_Modificacion(LocalDateTime.now());			
		srv.insertarEquipo(nuevo);
		
		return "redirect:/listaequipo";
	}

	@RequestMapping("/eliminarequipo/{idEquipo}")
	public String eliminarEquipo(@PathVariable("idEquipo") Integer idEquipo) {
		// TODO: process POST request
		if (idEquipo > 0) {
			srv.eliminarEquipo(idEquipo);
		}

		return "redirect:/listaequipo";
	}

}
