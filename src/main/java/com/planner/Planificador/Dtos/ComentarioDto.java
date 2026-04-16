package com.planner.Planificador.Dtos;

public class ComentarioDto {
	private String contenido;

	// Contructores
	public ComentarioDto() {
		super();
	}

	public ComentarioDto(String contenido) {
		super();
		this.contenido = contenido;
	}

	// Getters y Setters
	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

}
