package com.epmtpq.inventario.service;

import java.io.InputStream;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.epmtpq.inventario.model.FileResponse;

public interface IImagenService {
//	public void insertarImagen(Imagen nuevo);
//	public List<Imagen> listaImagen();
//	public Imagen buscarPorId(Integer id);
//	public void eliminarImagen(Integer id);
	public String uploadPhoto(String corredor, String parada, String filename, MultipartFile file) throws Exception;
	public InputStream downloadPhoto(String fileName) throws Exception;
	public String deletePhoto(String filename) throws Exception;
	public List<String> listPhotosInFolder(String carpetaCorredor, String carpetaParada, String carpetaEquipo) throws Exception;
	public FileResponse getFile(String filename) throws Exception;
	public List<InputStream> listaFotosOrdenadoPorFecha(String pathFolder) throws Exception;
	public List<String> listaURLsFotos(String pathFolder) throws Exception;
}
