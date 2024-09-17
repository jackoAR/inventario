package com.epmtpq.inventario.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epmtpq.inventario.model.Usuario;
import com.epmtpq.inventario.repository.IUsuarioRepository;
import com.epmtpq.inventario.service.IUsuarioService;

@Service
public class UsuarioServiceImpl implements IUsuarioService{
	
	@Autowired
	private IUsuarioRepository repo;
	
	@Override
	public void insertarUsuario(Usuario nuevo) {
		// TODO Auto-generated method stub
		repo.save(nuevo);
	}

	@Override
	public List<Usuario> listaUsuario() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public Usuario buscarPorId(Integer id) {
		// TODO Auto-generated method stub
		return repo.buscarPorId(id);
	}

	@Override
	public void eliminarUsuario(Integer id) {
		// TODO Auto-generated method stub
		repo.deleteById(id);
	}

}
