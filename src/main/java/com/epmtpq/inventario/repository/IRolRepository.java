package com.epmtpq.inventario.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.epmtpq.inventario.model.Rol;

@Repository
public interface IRolRepository extends JpaRepository<Rol, Integer>{
	@Query("select rl from Rol rl")
	public List<Rol> listaRol();
	@Query("select rl from Rol rl where rl.idRol=?1")
	public Rol buscarporId(Integer id);

}
