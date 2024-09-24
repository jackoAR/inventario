package com.epmtpq.inventario.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class Equipo implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idEquipo;
	private String Serial;
	private String Marca;
	private String Host_Name;
	private String Modelo;
	private String IP;
	private String Version;
	private int Puertos_Disponibles_Fibra;
	private int Puertos_Usados_Fibra;
	private int Puertos_Disponibles_Cobre;
	private int Puertos_Usados_Cobre;
	private String Cod_Bien;
	private String Ult_Actividad;
	private String PathMinio;
	private String Descripcion;
	
	@Column(name = "Ult_Modificacion")
	private LocalDateTime Ult_Modificacion;
		
	@Enumerated(EnumType.STRING)
	private EstadoEquipo Estado;
	
	@Enumerated(EnumType.STRING)
	private EquipoCriticidad Criticidad;
	
	@Column(name = "RegistroDeCambio")
	private LocalDate RegistroDeCambio;
	
	@Column(name = "Mantenimiento")
	private LocalDate Mantenimiento;
	
	@Enumerated(EnumType.STRING)
	private TipoEquipo TipoEquipo;
	
	public enum EstadoEquipo{
		ACTIVO,
		INACTIVO,
		MANT
	}
	
	public enum EquipoCriticidad{
		ALTA,
		BAJA,
		MEDIO
	}
	
	public enum TipoEquipo{
		AP,
		CONTROLADORA,
		FIREWALL,
		ROUTER,
		CAMARA,
		SWITCH		
	}
	
	@ManyToOne
	private Parada fkParada;
	@OneToMany(mappedBy = "fkEquipo")
	private List<Imagen> ListaImagen = new ArrayList<>();
}
