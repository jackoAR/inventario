package com.epmtpq.inventario;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.transaction.annotation.Transactional;

import io.minio.MinioClient;

@SpringBootTest
class InventarioApplicationTests {
	
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
		
		
		
		
		

}}
