package com.planner.Planificador.Dtos.Entidades;

public class UsuarioEquipoDto {
	
	//Atributos
	private Integer idUsuario;
	private String nombreUsuario;

	//Constructor
	public UsuarioEquipoDto(Integer idUsuario, String nombreUsuario) {
		this.idUsuario = idUsuario;
		this.nombreUsuario = nombreUsuario;
	}

	//Getters y Setters
	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
}