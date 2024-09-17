package com.epmtpq.inventario.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.epmtpq.inventario.model.Imagen;

@Repository
public interface IImagenRepository extends JpaRepository<Imagen, Integer>{
	@Query("select i from Imagen i")
	public List<Imagen> listaImagen();
	@Query("select i from Imagen i where i.idImagen=?1")
	public Imagen buscarPorId(Integer id);

}
