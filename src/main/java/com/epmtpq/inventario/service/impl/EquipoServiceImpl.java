package com.epmtpq.inventario.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epmtpq.inventario.model.Equipo;
import com.epmtpq.inventario.repository.IEquipoRepository;
import com.epmtpq.inventario.service.IEquipoService;

@Service
public class EquipoServiceImpl implements IEquipoService{
	
	@Autowired
	private IEquipoRepository repo;
	
	@Override
	public void insertarEquipo(Equipo nuevo) {
		// TODO Auto-generated method stub
		repo.save(nuevo);
	}

	@Override
	public List<Equipo> listaEquipo() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public Equipo buscarPorId(Integer id) {
		// TODO Auto-generated method stub
		return repo.buscarPorId(id);
	}

	@Override
	public void eliminarEquipo(Integer id) {
		// TODO Auto-generated method stub
		repo.deleteById(id);
	}

}
