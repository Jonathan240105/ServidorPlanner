package com.planner.Planificador.Dtos.Solicitudes;

public class CrearWorkSpaceSolicitud {

	private String titulo;
	private String descripcion;

	// Constructores
	public CrearWorkSpaceSolicitud() {
		super();
	}

	public CrearWorkSpaceSolicitud(String titulo, String desripcion) {
		super();
		this.titulo = titulo;
		this.descripcion = desripcion;
	}

	// Getters y Setters
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
