package com.epmtpq.inventario.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epmtpq.inventario.model.Parada;
import com.epmtpq.inventario.repository.IParadaRepository;
import com.epmtpq.inventario.service.IParadaService;

@Service
public class ParadaServiceImpl implements IParadaService{
	
	@Autowired
	private IParadaRepository repo;

	@Override
	public void insertarParada(Parada nuevo) {
		// TODO Auto-generated method stub
		repo.save(nuevo);
	}

	@Override
	public List<Parada> listaParada() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public Parada buscarPorId(Integer id) {
		// TODO Auto-generated method stub
		return repo.buscarPorId(id);
	}

	@Override
	public void eliminaParada(Integer id) {
		// TODO Auto-generated method stub
		repo.deleteById(id);
	}

	@Override
	public List<Parada> buscarParadaPorIdCorredor(Integer id) {
		// TODO Auto-generated method stub
		return repo.buscarParadaPorIdCorredor(id);
	}

}
