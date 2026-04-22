package com.planner.Planificador.Dtos.Actualizaciones;

public class ActualizarWorkSpaceSolicitud {

	private String nombre;

	public ActualizarWorkSpaceSolicitud(String nombre) {
		super();
		this.nombre = nombre;
	}

	// Getters y Setters
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}