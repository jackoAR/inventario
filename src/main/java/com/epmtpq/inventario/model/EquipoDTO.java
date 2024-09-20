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
	private int Disponibles;
	private int Usado;
	private int Disponibles2;
	private int Usado3;
	private String PathFoto;
	
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
			String Version, int Disponibles, int Usado, int Disponibles2, int Usado3,
			com.epmtpq.inventario.model.Equipo.EstadoEquipo Estado,
			com.epmtpq.inventario.model.Equipo.EquipoCriticidad Criticidad, LocalDate RegistroDeCambio,
			LocalDate Mantenimiento, com.epmtpq.inventario.model.Equipo.TipoEquipo TipoEquipo, Integer fkParada, String PathFoto) {
		
		this.idEquipo = idEquipo;
		this.Serial = Serial;
		this.Marca = Marca;
		this.Host_Name = Host_Name;
		this.Modelo = Modelo;
		this.IP = IP;
		this.Version = Version;
		this.Disponibles = Disponibles;
		this.Usado = Usado;
		this.Disponibles2 = Disponibles2;
		this.Usado3 = Usado3;
		this.Estado = Estado;
		this.Criticidad = Criticidad;
		this.RegistroDeCambio = RegistroDeCambio;
		this.Mantenimiento = Mantenimiento;
		this.TipoEquipo = TipoEquipo;
		this.fkParada = fkParada;
		this.PathFoto = PathFoto;
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

	public int getDisponibles() {
		return Disponibles;
	}

	public void setDisponibles(int disponibles) {
		Disponibles = disponibles;
	}

	public int getUsado() {
		return Usado;
	}

	public void setUsado(int usado) {
		Usado = usado;
	}

	public int getDisponibles2() {
		return Disponibles2;
	}

	public void setDisponibles2(int disponibles2) {
		Disponibles2 = disponibles2;
	}

	public int getUsado3() {
		return Usado3;
	}

	public void setUsado3(int usado3) {
		Usado3 = usado3;
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

	public String getPathFoto() {
		return PathFoto;
	}

	public void setPathFoto(String pathFoto) {
		PathFoto = pathFoto;
	}
	
}
