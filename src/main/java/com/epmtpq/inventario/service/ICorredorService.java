package com.epmtpq.inventario.service;

import java.util.List;

import com.epmtpq.inventario.model.Corredor;

public interface ICorredorService {
	public void insertarCorredor(Corredor nuevo);
	public List<Corredor> listaCorredor();
	public Corredor buscarPorId(Integer id);
	public void eliminarCorredor(Integer id);
}
