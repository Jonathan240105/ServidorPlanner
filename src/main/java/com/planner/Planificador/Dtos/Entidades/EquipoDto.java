package com.planner.Planificador.Dtos.Entidades;

public class EquipoDto {

	private String nombreEquipo;
	private String usuarioAdmin;

	//Constructores
	public EquipoDto() {

	}

	//Getters y Setters
	public EquipoDto(String nombreEquipo, String usuarioAdmin) {
		super();
		this.nombreEquipo = nombreEquipo;
		this.usuarioAdmin = usuarioAdmin;
	}

	public String getNombreEquipo() {
		return nombreEquipo;
	}

	public void setNombreEquipo(String nombreEquipo) {
		this.nombreEquipo = nombreEquipo;
	}

	public String getUsuarioAdmin() {
		return usuarioAdmin;
	}

	public void setUsuarioAdmin(String usuarioAdmin) {
		this.usuarioAdmin = usuarioAdmin;
	}

}
