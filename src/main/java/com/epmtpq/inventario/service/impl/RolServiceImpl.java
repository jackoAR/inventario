package com.epmtpq.inventario.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epmtpq.inventario.model.Rol;
import com.epmtpq.inventario.repository.IRolRepository;
import com.epmtpq.inventario.service.IRolService;

@Service
public class RolServiceImpl implements IRolService{
	
	@Autowired
	private IRolRepository repo;	
	
	@Override
	public void insertarRol(Rol nuevo) {
		// TODO Auto-generated method stub
		repo.save(nuevo);
	}

	@Override
	public List<Rol> listaRol() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public Rol buscarPorId(Integer id) {
		// TODO Auto-generated method stub
		return repo.buscarporId(id);
	}

	@Override
	public void eliminarRol(Integer id) {
		// TODO Auto-generated method stub
		repo.deleteById(id);
	}

}
