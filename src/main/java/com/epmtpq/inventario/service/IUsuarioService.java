package com.epmtpq.inventario.service;

import java.util.List;

import com.epmtpq.inventario.model.Usuario;

public interface IUsuarioService {
	public void insertarUsuario(Usuario nuevo);
	public List<Usuario> listaUsuario();
	public Usuario buscarPorId(Integer id);
	public void eliminarUsuario(Integer id);
}
