package com.epmtpq.inventario.service.impl;

import java.io.InputStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.epmtpq.inventario.model.FileResponse;
import com.epmtpq.inventario.service.IImagenService;
import io.minio.GetObjectArgs;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.ListObjectsArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.RemoveObjectArgs;
import io.minio.Result;
import io.minio.StatObjectArgs;
import io.minio.StatObjectResponse;
import io.minio.http.Method;
import io.minio.messages.Item;

@Service
public class ImagenServiceImpl implements IImagenService {

	@Autowired
	private MinioClient minioClient;

	@Value("${minio.bucket.name}")
	private String bucketName;

	@Override
	public String uploadPhoto(String corredor, String parada, String filename, MultipartFile file) throws Exception {
		// TODO Auto-generated method stub
//		String fullPath = corredor + "/" + parada + "/" + filename;
		String originalName = file.getOriginalFilename();
		String fullPath = corredor + "/" + parada + "/" + filename + "/" + originalName;	
		
		minioClient.putObject(PutObjectArgs
				.builder()
				.bucket(bucketName)
				.object(fullPath)
				.stream(file.getInputStream(), file.getSize(), -1)
				.contentType(file.getContentType())
				.build());
		return "File uploaded successfully: " + filename;
	}

	@Override
	public String deletePhoto(String filename) throws Exception {
		// TODO Auto-generated method stub
		minioClient.removeObject(RemoveObjectArgs.builder()
				.bucket(bucketName)				
				.object(filename)
				.build());
		return "File deleted successfully: " + filename;
	}

	@Override
	public List<String> listPhotosInFolder(String carpetaCorredor, String carpetaParada, String carpetaEquipo) throws Exception {
		// TODO Auto-generated method stub
		
		
		System.out.println("Listando todas las fotos en el bucket: " + bucketName);
		
		Iterable<Result<Item>> results = minioClient.listObjects(
				ListObjectsArgs.builder()
						.bucket(bucketName)
						.prefix(carpetaCorredor + "/" + carpetaParada + "/" + carpetaEquipo) // Carpeta específica
						.recursive(true) // Listar todos los objetos sin usar prefijo
						.build());
		
		List<String> photos = new ArrayList<>();
		for (Result<Item> result : results) {
			Item item = result.get();
			System.out.println("Encontrado: " + item.objectName()); // Verifica qué archivos se listan
			photos.add(item.objectName());
						
		}
		
		System.out.println("Total de fotos encontradas: " + photos.size());
		
		return photos;
	}

	@Override
	public FileResponse getFile(String dirPhoto) throws Exception {

		// Convertir el nombre del archivo en un objeto Path
		Path path = Path.of(dirPhoto);
		
		// Obtener los metadatos del archivo (stat)
	    StatObjectArgs statArgs = StatObjectArgs.builder()
	            .bucket("equipos")  // Reemplaza con el nombre de tu bucket
	            .object(path.toString())     // Ruta completa del archivo dentro del bucket
	            .build();
	    
	    StatObjectResponse metadata = minioClient.statObject(statArgs);
	    
	 // Obtener el archivo como un InputStream
	    GetObjectArgs getObjectArgs = GetObjectArgs.builder()
	            .bucket("equipos")  // Reemplaza con el nombre de tu bucket
	            .object(path.toString())     // Ruta completa del archivo dentro del bucket
	            .build();
	    
	    InputStream inputStream = minioClient.getObject(getObjectArgs);
	    
	 // Crear InputStreamResource a partir del InputStream
	    InputStreamResource inputStreamResource = new InputStreamResource(inputStream);
	    
	 // Retornar los metadatos y el InputStream del archivo en un FileResponse
	    return new FileResponse.Builder()
	            .filename(metadata.object())                       // Nombre del archivo
	            .fileSize(metadata.size())                         // Tamaño del archivo
	            .contentType(metadata.contentType())               // Tipo de contenido (e.g., image/jpeg)
	            .createdTime(metadata.lastModified())   // Fecha de modificación
	            .stream(inputStreamResource)                       // El InputStreamResource del archivo
	            .build();

	}

	@Override
	public InputStream downloadPhoto(String fileName) throws Exception {
		// TODO Auto-generated method stub		
		return minioClient.getObject(GetObjectArgs.builder()
				.bucket(bucketName)
				.object(fileName)
				.build());
	}

	@Override
	public List<InputStream> listaFotosOrdenadoPorFecha(String pathFolder) throws Exception {
		// TODO Auto-generated method stub
		List<InputStream> photoStreams = new ArrayList<>();
		
		 // List all objects in the specified bucket and folder path
        Iterable<Result<Item>> results = minioClient.listObjects(
        		ListObjectsArgs.builder()
        		.bucket(bucketName)
        		.prefix(pathFolder)
        		.recursive(true)
        		.build());
        
        // Collect all objects into a list sorted by last modified date
        List<Item> sortedItems = StreamSupport.stream(results.spliterator(), false)
                .map(result -> {
                    try {
                        return result.get();
                    } catch (Exception e) {
                        throw new RuntimeException("Error while fetching object metadata", e);
                    }
                })
                .sorted((item1, item2) -> item2.lastModified().compareTo(item1.lastModified())) // Ordena por la fecha de modificación, de más reciente a más antigua
                .collect(Collectors.toList());
        
        for (Item item : sortedItems) {
        	String objectName = item.objectName();
        	
        	InputStream photoStream = minioClient.getObject(GetObjectArgs.builder()
        			.bucket(bucketName)
        			.object(objectName)
        			.build());
        	
        	photoStreams.add(photoStream);
        }
        
        return photoStreams;
	}

	@Override
	public List<String> listaURLsFotos(String pathFolder) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Listando todas las fotos en el bucket: " + bucketName);
		
		Iterable<Result<Item>> results = minioClient.listObjects(
        		ListObjectsArgs.builder()
        		.bucket(bucketName)
        		.prefix(pathFolder)
        		.recursive(true)
        		.build());
		
		// Collect all objects into a list sorted by last modified date
        List<Item> sortedItems = StreamSupport.stream(results.spliterator(), false)
                .map(result -> {
                    try {
                        return result.get();
                    } catch (Exception e) {
                        throw new RuntimeException("Error while fetching object metadata", e);
                    }
                })
                .sorted((item1, item2) -> item2.lastModified().compareTo(item1.lastModified())) // Ordena por la fecha de modificación, de más reciente a más antigua
                .collect(Collectors.toList());
		
        
		List<String> photoUrls = new ArrayList<>();
		for(Item item : sortedItems) {
			
//			Item item = result.get();
			String objectUrl = minioClient.getPresignedObjectUrl(
					GetPresignedObjectUrlArgs.builder()
					.method(Method.GET)
					.bucket(bucketName)
					.object(item.objectName())
					.expiry(7, TimeUnit.DAYS)  // La URL es válida por 7 días
					.build());
			
			System.out.println("Encontrado: " + item.objectName());
			photoUrls.add(objectUrl);
		}
		System.out.println("Total de fotos encontradas: " + photoUrls.size());
		return photoUrls;
	}
	
	
	
}