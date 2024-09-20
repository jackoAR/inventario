package com.epmtpq.inventario.controller;

import java.io.InputStream;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.epmtpq.inventario.model.Corredor;
import com.epmtpq.inventario.model.Parada;
import com.epmtpq.inventario.model.ParadaDTO;
import com.epmtpq.inventario.service.ICorredorService;
import com.epmtpq.inventario.service.IImagenService;
import com.epmtpq.inventario.service.IParadaService;

@RestController
@RequestMapping("/api/photos")
public class ImagenController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	@Autowired
	private IImagenService minioService;
	@Autowired
	private ICorredorService srvCorredor;
	@Autowired
	private IParadaService srvParada;

//	@PostMapping("/upload")
//	public ResponseEntity<String> uploadPhoto(@RequestParam("file") MultipartFile file) {
//		try {
//			String filename = file.getOriginalFilename();
//			return ResponseEntity.ok(minioService.uploadPhoto(filename, file));
//		} catch (Exception e) {
//			return ResponseEntity.status(500).body("Error uploading file: " + e.getMessage());
//		}
//	}

	@GetMapping("/download/{fkParada}/{filename}")
	public ResponseEntity<byte[]> downloadPhoto(@PathVariable Integer fkParada, @PathVariable String filename) {
		try {
			//Busca la Parada por Id de parada
			Parada parada = srvParada.buscarPorId(fkParada);
			//Obtiene el nombre de la Parada
		    String folderName = parada.getNombre();
			
			InputStream inputStream = minioService.downloadPhoto(folderName,filename);
			byte[] content = inputStream.readAllBytes();
			return ResponseEntity.ok()
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"")
					.contentType(MediaType.APPLICATION_OCTET_STREAM).body(content);
		} catch (Exception e) {
			return ResponseEntity.status(500).body(null);
		}

	}

	@DeleteMapping("/delete/{filename}")
	public ResponseEntity<String> deletePhoto(@PathVariable String filename) {
		try {
			return ResponseEntity.ok(minioService.deletePhoto(filename));
		} catch (Exception e) {
			return ResponseEntity.status(500).body("Error deleting file: " + e.getMessage());
		}
	}

	@GetMapping("/list/{folderName}")
	public ResponseEntity<List<String>> listPhotos(@PathVariable String folderName) {
		try {
			List<String> photos = minioService.listPhotosInFolder(folderName); // Obtiene fotos desde la carpeta
//			model.addAttribute("photos", photos);
//			model.addAttribute("folderName", folderName); // El nombre de la carpeta para mostrar en la vista
//			return "/equipo/listaEquipo"; // Nombre de la vista Thymeleaf que presentará las fotos
			return new ResponseEntity<>(photos, HttpStatus.OK);
		} catch (Exception e) {
//			model.addAttribute("errorMessage", "Error al cargar las fotos.");
//			return "error"; // Página de error si algo falla
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/*
	 * @GetMapping("/view/{fkParada}/{serial}") public
	 * ResponseEntity<InputStreamResource> viewPhoto(@PathVariable Integer
	 * fkParada, @PathVariable String serial) { try { // Buscar el nombre de la
	 * carpeta para la parada específica Parada parada =
	 * srvParada.buscarPorId(fkParada); String folderName = parada.getNombre();
	 * 
	 * // Listar las fotos en la carpeta de la parada List<String> photos =
	 * minioService.listPhotosInFolder(folderName);
	 * 
	 * // Buscar el archivo que coincida con el número de serie String photoFilename
	 * = photos.stream() .filter(name -> name.contains(serial)) // Filtrar por el
	 * nombre del archivo que contenga el número de serie .findFirst()
	 * .orElseThrow(() -> new Exception("Foto no encontrada para el serial: " +
	 * serial));
	 * 
	 * // Obtener los metadatos y el archivo desde Minio // Path photoPath =
	 * Path.of(folderName + "/" + photoFilename); // var metadata =
	 * minioService.getMetadata(photoPath); // InputStream inputStream =
	 * minioService.get(photoPath);
	 * 
	 * String dirPhoto = folderName + "/" + photoFilename;
	 * 
	 * 
	 * // Crear un InputStreamResource // InputStreamResource resource = new
	 * InputStreamResource(inputStream);
	 * 
	 * FileResponse fileResponse = minioService.getFile(dirPhoto);
	 * 
	 * // Devolver la imagen con el tipo de contenido adecuado // return
	 * ResponseEntity.ok() //
	 * .contentType(MediaType.parseMediaType(metadata.contentType())) // Tipo de
	 * contenido correcto (e.g., image/jpeg) // .header("Content-disposition",
	 * "inline; filename=" + metadata.name()) // Mostrar en línea //
	 * .body(resource);
	 * 
	 * return ResponseEntity.ok()
	 * .contentType(MediaType.parseMediaType(fileResponse.getContentType()))
	 * .header("Content-disposition", "inline; filename=" +
	 * fileResponse.getFilename()) .body(fileResponse.getStream());
	 * 
	 * 
	 * } catch (Exception e) { return new ResponseEntity<>(HttpStatus.NOT_FOUND); }
	 * }
	 */

	@GetMapping("/view/{fkParada}/{serial}")
	public ResponseEntity<byte[]> viewPhoto(@PathVariable Integer fkParada, @PathVariable String serial) {
	    try {
	    	// Aquí puedes imprimir los valores recibidos para comprobar
		    System.out.println("fkParada: " + fkParada);
		    System.out.println("Serial: " + serial);
		    
		    //busca la parada por el id de la parada
		    Parada parada = srvParada.buscarPorId(fkParada);
		    //obtiene el nombre de la parada
		    String folderName = parada.getNombre();
		    
		    // Listar las fotos en la carpeta de la parada
	        List<String> photos = minioService.listPhotosInFolder(folderName);
		    
	        // Buscar el archivo que coincida con el número de serie
	        String photoFilename = photos.stream()
	                .filter(name -> name.contains(serial))
	                .findFirst()
	                .orElseThrow(() -> new Exception("Foto no encontrada para el serial: " + serial));
	        
	        InputStream inputStream = minioService.downloadPhoto(folderName, photoFilename);
	        byte[] photoBytes = inputStream.readAllBytes();
	        
	     // Determine the content type based on the file extension
	        String contentType = "image/jpeg"; // Default value
	        if (photoFilename.endsWith(".png")) {
	            contentType = "image/png";
	        } else if (photoFilename.endsWith(".gif")) {
	            contentType = "image/gif";
	        }
		    
	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.parseMediaType(contentType));
	        
	        return new ResponseEntity<>(photoBytes, headers, HttpStatus.OK);
		    
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	 

	/*
	 * @GetMapping("/view/{filename}") public ResponseEntity<byte[]>
	 * viewPhoto(@PathVariable String filename) { try { InputStream inputStream =
	 * minioService.downloadPhoto(filename); byte[] photoBytes =
	 * IOUtils.toByteArray(inputStream);
	 * 
	 * // Determina el tipo de contenido basado en la extensión del archivo String
	 * contentType = "image/jpeg"; // Valor por defecto if
	 * (filename.endsWith(".png")) { contentType = "image/png"; } else if
	 * (filename.endsWith(".gif")) { contentType = "image/gif"; }
	 * 
	 * HttpHeaders headers = new HttpHeaders();
	 * headers.setContentType(MediaType.parseMediaType(contentType));
	 * 
	 * return new ResponseEntity<>(photoBytes, headers, HttpStatus.OK); } catch
	 * (Exception e) { return new ResponseEntity<>(HttpStatus.NOT_FOUND); } }
	 */

	@GetMapping("/cargarSelectParada/{corredorId}")
	public ResponseEntity<List<ParadaDTO>> cargarSelectParada(@PathVariable Integer corredorId) {
		try {

			Corredor corredor = srvCorredor.buscarPorId(corredorId);

			// Verificar si el corredor existe
			if (corredor == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}

			List<ParadaDTO> paradaDTOs = corredor.getListaParada().stream()
					.map(parada -> new ParadaDTO(parada.getId(), parada.getNombre())).collect(Collectors.toList());

			// Devuelve la lista de paradas en formato JSON
			return new ResponseEntity<>(paradaDTOs, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

//    @GetMapping("/cargarSelectParada/{corredorId}")
//	public String cargarSelect(@PathVariable("corredorId") Integer corredorId, Model model) {
//		List<Parada> existe = null;
//		if(corredorId>0) {
//			existe = srvParada.buscarParadaPorIdCorredor(corredorId);
//		}else {
//			return "redirect:/listaequipo";
//		}
//		
//		model.addAttribute("listaParadasPorCorredor", existe);
//		return "/equipo/listaEquipo";
//	}

//	@Autowired
//	private IImagenService srv;
//	
//	private static final long serialVersionUID = 1L;
//	
//	@GetMapping("/listaimagen")
//	public String verCliente(Model model) {
//		model.addAttribute("ListaImagen", srv.listaImagen());
//		return "/imagen/listaImagen";
//		
//	}
//	
//	@GetMapping("/nuevoimagen")
//	public String crearImagen(Model model) {
//		Imagen nuevo = new Imagen();
//		model.addAttribute("nuevo", nuevo);
//		return "/imagen/nuevoImagen";
//	}
//	
//	@GetMapping("/nuevoimagen/{idImagen}")
//	public String editarImagen(@PathVariable("idImagen") Integer idImagen, Model model) {
//		Imagen existe = null;
//		if(idImagen>0) {
//			existe = srv.buscarPorId(idImagen);
//		}else {
//			return "redirect:/listaImagen";
//		}
//		model.addAttribute("nuevo",existe);
//		return "/imagen/nuevoImagen";
//	}
//	
//	@PostMapping("/guardarimagen")
//	public String guardarImagen(@ModelAttribute("nuevo") Imagen nuevo) {
//		srv.insertarImagen(nuevo);
//		return "redirect:/listaImagen";
//	}
//	
//	@RequestMapping("/eliminarimagen/{idImagen}")
//	public String eliminarImagen(@PathVariable("idImagen") Integer idImagen) {
//		//TODO: process POST request
//		if(idImagen > 0 ) {
//			srv.eliminarImagen(idImagen);
//		}
//		
//		return "redirect:/listaImagen";
//	}

}
