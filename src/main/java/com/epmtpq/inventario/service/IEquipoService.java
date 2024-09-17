package com.epmtpq.inventario.service;

import java.util.List;

import com.epmtpq.inventario.model.Equipo;

public interface IEquipoService {
	public void insertarEquipo(Equipo nuevo);
	public List<Equipo> listaEquipo();
	public Equipo buscarPorId(Integer id);
	public void eliminarEquipo(Integer id);
}
