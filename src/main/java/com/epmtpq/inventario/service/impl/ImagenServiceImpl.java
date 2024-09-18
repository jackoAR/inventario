package com.epmtpq.inventario.service.impl;

import java.io.InputStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.epmtpq.inventario.model.FileResponse;
import com.epmtpq.inventario.service.IImagenService;
import io.minio.GetObjectArgs;
import io.minio.ListObjectsArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.RemoveObjectArgs;
import io.minio.Result;
import io.minio.StatObjectArgs;
import io.minio.StatObjectResponse;
import io.minio.messages.Item;

@Service
public class ImagenServiceImpl implements IImagenService {

	@Autowired
	private MinioClient minioClient;

	@Value("${minio.bucket.name}")
	private String bucketName;

	@Override
	public String uploadPhoto(String filename, MultipartFile file) throws Exception {
		// TODO Auto-generated method stub
		minioClient.putObject(PutObjectArgs.builder().bucket(bucketName).object(filename)
				.stream(file.getInputStream(), file.getSize(), -1).contentType(file.getContentType()).build());
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
	public List<String> listPhotosInFolder(String folderName) throws Exception {
		// TODO Auto-generated method stub
		List<String> photos = new ArrayList<>();
		
		System.out.println("Listando todas las fotos en el bucket: " + bucketName);
		
		Iterable<Result<Item>> results = minioClient.listObjects(
				ListObjectsArgs.builder()
						.bucket(bucketName)
						.prefix(folderName + "/") // Carpeta específica
						.recursive(true) // Listar todos los objetos sin usar prefijo
						.build());

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
	public InputStream downloadPhoto(String folderName, String fileName) throws Exception {
		// TODO Auto-generated method stub
		String fullFilePath = folderName + "/" + fileName;
		
		return minioClient.getObject(GetObjectArgs.builder()
				.bucket(bucketName)
				.object(fullFilePath)
				.build());
	}

}