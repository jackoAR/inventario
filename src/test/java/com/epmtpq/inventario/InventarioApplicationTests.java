package com.epmtpq.inventario;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Hibernate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.transaction.annotation.Transactional;

import com.epmtpq.inventario.model.Parada;
import com.epmtpq.inventario.service.IImagenService;
import com.epmtpq.inventario.service.IParadaService;

import io.minio.MinioClient;

@SpringBootTest
class InventarioApplicationTests {
	
	@Autowired
	private IParadaService srvparada;
	@Autowired
	private IImagenService srvimagen;
	@MockBean
	private MinioClient minioClient;
	
	@Test
	@Transactional
	void contextLoads() throws Exception {
		/*
		 * Parada parada = srvparada.buscarPorId(1);
		 * 
		 * if (parada != null) { // Forzar la inicialización de la colección lazy-loaded
		 * Hibernate.initialize(parada.getNombre()); String nombre = parada.getNombre();
		 * System.out.println("La parada encontrada es: " + nombre); } else {
		 * System.out.println("Parada no encontrada"); }
		 */
		
		List<String> nombreFotos = srvimagen.listPhotosInFolder("1");
		
		
		if (!nombreFotos.isEmpty()) {
			
			for (String nombreArchivo : nombreFotos) {
				System.out.println("Fotos encontradas en la carpeta '1': " + nombreArchivo);
			}
				
		}else {
			System.out.println("No se encontraron fotos en la carpeta '1'");
		}
		
	}

}
