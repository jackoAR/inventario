package com.epmtpq.inventario.service;

import java.util.List;

import com.epmtpq.inventario.model.Parada;

public interface IParadaService {
	public void insertarParada(Parada nuevo);
	public List<Parada> listaParada();
	public Parada buscarPorId(Integer id);
	public void eliminaParada(Integer id);
	public List<Parada> buscarParadaPorIdCorredor(Integer id);
}
