package com.epmtpq.inventario.service;

import java.util.List;

import com.epmtpq.inventario.model.Equipo;

public interface IEquipoService {
	public Equipo insertarEquipo(Equipo nuevo);
	public List<Equipo> listaEquipo();
	public Equipo buscarPorId(Integer id);
	public void eliminarEquipo(Integer id);
	public void actualizarPathMinio(String pathMinio, Integer id);
}
