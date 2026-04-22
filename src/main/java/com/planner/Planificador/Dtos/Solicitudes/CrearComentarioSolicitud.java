package com.planner.Planificador.Dtos.Solicitudes;

public class CrearComentarioSolicitud {

	private String contenido;

	public CrearComentarioSolicitud(String contenido) {
		super();
		this.contenido = contenido;
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}
	
}
