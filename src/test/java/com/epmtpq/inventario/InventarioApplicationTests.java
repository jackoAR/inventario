package com.epmtpq.inventario;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.epmtpq.inventario.model.Equipo;
import com.epmtpq.inventario.service.IEquipoService;

@SpringBootTest
class InventarioApplicationTests {
	@Autowired IEquipoService srv;
	@Test
	void contextLoads() {
		Equipo equipo = srv.buscarPorId(1);
		System.out.println(equipo.getMantenimiento());
	}

}
