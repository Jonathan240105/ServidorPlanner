package com.planner.Planificador.Dtos;

public class CrearWorkSpaceSolicitud {

	private String titulo;
	private String desripcion;

	// Constructores
	public CrearWorkSpaceSolicitud() {
		super();
	}

	public CrearWorkSpaceSolicitud(String titulo, String desripcion) {
		super();
		this.titulo = titulo;
		this.desripcion = desripcion;
	}

	// Getters y Setters
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDesripcion() {
		return desripcion;
	}

	public void setDesripcion(String desripcion) {
		this.desripcion = desripcion;
	}

}
