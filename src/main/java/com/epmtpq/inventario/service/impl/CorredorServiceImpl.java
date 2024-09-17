package com.epmtpq.inventario.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epmtpq.inventario.model.Corredor;
import com.epmtpq.inventario.repository.ICorredorRepository;
import com.epmtpq.inventario.service.ICorredorService;

@Service
public class CorredorServiceImpl implements ICorredorService{
	
	@Autowired
	private ICorredorRepository rep;

	@Override
	public void insertarCorredor(Corredor nuevo) {
		// TODO Auto-generated method stub
		rep.save(nuevo);
		
	}

	@Override
	public List<Corredor> listaCorredor() {
		// TODO Auto-generated method stub
		return rep.findAll();
	}

	@Override
	public Corredor buscarPorId(Integer id) {
		// TODO Auto-generated method stub
		return rep.buscarPorId(id);
	}

	@Override
	public void eliminarCorredor(Integer id) {
		// TODO Auto-generated method stub
		rep.deleteById(id);
	}

}
