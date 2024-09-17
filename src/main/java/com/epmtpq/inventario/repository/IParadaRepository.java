package com.epmtpq.inventario.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.epmtpq.inventario.model.Parada;

@Repository
public interface IParadaRepository extends JpaRepository<Parada, Integer>{
	@Query("select p from Parada p")
	public List<Parada> listaParada();
	@Query("select p from Parada p where p.id=?1")
	public Parada buscarPorId(Integer id);
	@Query("select p from Parada p where p.fkCorredor.id = ?1")
	public List<Parada> buscarParadaPorIdCorredor(Integer id);
}
