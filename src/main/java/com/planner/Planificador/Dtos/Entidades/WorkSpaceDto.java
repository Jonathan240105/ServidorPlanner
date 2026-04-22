package com.planner.Planificador.Dtos.Entidades;

public class WorkSpaceDto {

	private String nombre;
	private String descripcion;
	private String usuarioAsignado;

	// Constructores
	public WorkSpaceDto() {
		super();
	}

	public WorkSpaceDto(String nombre, String descripcion, String usuarioAsignado) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.usuarioAsignado = usuarioAsignado;
	}

	// Getters y Setters
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getUsuarioAsignado() {
		return usuarioAsignado;
	}

	public void setUsuarioAsignado(String usuarioAsignado) {
		this.usuarioAsignado = usuarioAsignado;
	}

}
