package com.epmtpq.inventario.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.epmtpq.inventario.model.Usuario;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, Integer>{
	@Query("select u from Usuario u")
	public List<Usuario> listaUsuario();
	@Query("select u from Usuario u where u.idUsuario=?1")
	public Usuario buscarPorId(Integer id);
}
