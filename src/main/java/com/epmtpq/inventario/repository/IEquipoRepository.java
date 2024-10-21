package com.epmtpq.inventario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.epmtpq.inventario.model.Equipo;

@Repository
public interface IEquipoRepository extends JpaRepository<Equipo, Integer>{
	
	@Query("select e from Equipo e where e.idEquipo=?1")
	public Equipo buscarPorId(Integer id);
	@Transactional
	@Modifying
	@Query("update Equipo e set e.PathMinio = ?1 where e.idEquipo = ?2")
	public void actualizarPathMinio(String pathMinio, Integer id);
	
}
