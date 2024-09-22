package com.epmtpq.inventario.model;

import java.time.LocalDate;

import com.epmtpq.inventario.model.Equipo.EquipoCriticidad;
import com.epmtpq.inventario.model.Equipo.EstadoEquipo;
import com.epmtpq.inventario.model.Equipo.TipoEquipo;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class EquipoDTO {
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
//	private String PathFoto;
	private String Ult_Actividad;

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

	private Integer fkParada;

	public EquipoDTO(int idEquipo, String Serial, String Marca, String Host_Name, String Modelo, String IP,
			String Version, int Puertos_Disponibles_Fibra, int Puertos_Usados_Fibra, int Puertos_Disponibles_Cobre,
			int Puertos_Usados_Cobre, String Cod_Bien, String Ult_Actividad, com.epmtpq.inventario.model.Equipo.EstadoEquipo Estado,
			com.epmtpq.inventario.model.Equipo.EquipoCriticidad Criticidad, LocalDate RegistroDeCambio,
			LocalDate Mantenimiento, com.epmtpq.inventario.model.Equipo.TipoEquipo TipoEquipo, Integer fkParada
			) {

		this.idEquipo = idEquipo;
		this.Serial = Serial;
		this.Marca = Marca;
		this.Host_Name = Host_Name;
		this.Modelo = Modelo;
		this.IP = IP;
		this.Version = Version;
		this.Puertos_Disponibles_Fibra = Puertos_Disponibles_Fibra;
		this.Puertos_Usados_Fibra = Puertos_Usados_Fibra;
		this.Puertos_Disponibles_Cobre = Puertos_Disponibles_Cobre;
		this.Puertos_Usados_Cobre = Puertos_Usados_Cobre;
		this.Cod_Bien = Cod_Bien;
		this.Ult_Actividad = Ult_Actividad;
		this.Estado = Estado;
		this.Criticidad = Criticidad;
		this.RegistroDeCambio = RegistroDeCambio;
		this.Mantenimiento = Mantenimiento;
		this.TipoEquipo = TipoEquipo;
		this.fkParada = fkParada;
//		this.PathFoto = PathFoto;
		// TODO Auto-generated constructor stub
	}

	public int getIdEquipo() {
		return idEquipo;
	}

	public void setIdEquipo(int idEquipo) {
		this.idEquipo = idEquipo;
	}

	public String getSerial() {
		return Serial;
	}

	public void setSerial(String serial) {
		Serial = serial;
	}

	public String getMarca() {
		return Marca;
	}

	public void setMarca(String marca) {
		Marca = marca;
	}

	public String getHost_Name() {
		return Host_Name;
	}

	public void setHost_Name(String host_Name) {
		Host_Name = host_Name;
	}

	public String getModelo() {
		return Modelo;
	}

	public void setModelo(String modelo) {
		Modelo = modelo;
	}

	public String getIP() {
		return IP;
	}

	public void setIP(String iP) {
		IP = iP;
	}

	public String getVersion() {
		return Version;
	}

	public void setVersion(String version) {
		Version = version;
	}
	
	public EstadoEquipo getEstado() {
		return Estado;
	}

	public void setEstado(EstadoEquipo estado) {
		Estado = estado;
	}

	public EquipoCriticidad getCriticidad() {
		return Criticidad;
	}

	public void setCriticidad(EquipoCriticidad criticidad) {
		Criticidad = criticidad;
	}

	public LocalDate getRegistroDeCambio() {
		return RegistroDeCambio;
	}

	public void setRegistroDeCambio(LocalDate registroDeCambio) {
		RegistroDeCambio = registroDeCambio;
	}

	public LocalDate getMantenimiento() {
		return Mantenimiento;
	}

	public void setMantenimiento(LocalDate mantenimiento) {
		Mantenimiento = mantenimiento;
	}

	public TipoEquipo getTipoEquipo() {
		return TipoEquipo;
	}

	public void setTipoEquipo(TipoEquipo tipoEquipo) {
		TipoEquipo = tipoEquipo;
	}

	public Integer getFkParada() {
		return fkParada;
	}

	public void setFkParada(Integer fkParada) {
		this.fkParada = fkParada;
	}

	public int getPuertos_Disponibles_Fibra() {
		return Puertos_Disponibles_Fibra;
	}

	public void setPuertos_Disponibles_Fibra(int puertos_Disponibles_Fibra) {
		Puertos_Disponibles_Fibra = puertos_Disponibles_Fibra;
	}

	public int getPuertos_Usados_Fibra() {
		return Puertos_Usados_Fibra;
	}

	public void setPuertos_Usados_Fibra(int puertos_Usados_Fibra) {
		Puertos_Usados_Fibra = puertos_Usados_Fibra;
	}

	public int getPuertos_Disponibles_Cobre() {
		return Puertos_Disponibles_Cobre;
	}

	public void setPuertos_Disponibles_Cobre(int puertos_Disponibles_Cobre) {
		Puertos_Disponibles_Cobre = puertos_Disponibles_Cobre;
	}

	public int getPuertos_Usados_Cobre() {
		return Puertos_Usados_Cobre;
	}

	public void setPuertos_Usados_Cobre(int puertos_Usados_Cobre) {
		Puertos_Usados_Cobre = puertos_Usados_Cobre;
	}

	public String getCod_Bien() {
		return Cod_Bien;
	}

	public void setCod_Bien(String cod_Bien) {
		Cod_Bien = cod_Bien;
	}

	public String getUlt_Actividad() {
		return Ult_Actividad;
	}

	public void setUlt_Actividad(String ult_Actividad) {
		Ult_Actividad = ult_Actividad;
	}

	
}
