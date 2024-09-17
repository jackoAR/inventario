package com.epmtpq.inventario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.epmtpq.inventario.model.Corredor;

@Repository
public interface ICorredorRepository extends JpaRepository<Corredor, Integer>{
	
	@Query("select c from Corredor c where c.Id=?1")
	public Corredor buscarPorId(Integer id);
	

}
