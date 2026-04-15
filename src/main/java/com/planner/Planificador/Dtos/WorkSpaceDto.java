package com.planner.Planificador.Dtos;

import com.planner.Planificador.ClasesEntidades.Usuario;

public class WorkSpaceDto {

	private String nombre;
	private String descripcion;
	private Usuario usuarioAsignado;

	// Constructores
	public WorkSpaceDto() {
		super();
	}

	public WorkSpaceDto(String nombre, String descripcion, Usuario usuarioAsignado) {
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

	public Usuario getUsuarioAsignado() {
		return usuarioAsignado;
	}

	public void setUsuarioAsignado(Usuario usuarioAsignado) {
		this.usuarioAsignado = usuarioAsignado;
	}

}
