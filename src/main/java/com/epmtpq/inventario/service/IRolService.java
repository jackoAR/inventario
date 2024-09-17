package com.epmtpq.inventario.service;

import java.util.List;

import com.epmtpq.inventario.model.Rol;

public interface IRolService {
	public void insertarRol(Rol nuevo);
	public List<Rol> listaRol();
	public Rol buscarPorId(Integer id);
	public void eliminarRol(Integer id);
}
