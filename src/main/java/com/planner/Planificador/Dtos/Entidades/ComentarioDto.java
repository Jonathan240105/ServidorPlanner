package com.planner.Planificador.Dtos.Entidades;

public class ComentarioDto {
	private String contenido;
	private String tareaAsignada;

	// Contructores
	public ComentarioDto() {
		super();
	}

	public ComentarioDto(String contenido, String tareaAsignada) {
		super();
		this.contenido = contenido;
		this.tareaAsignada = tareaAsignada;
	}

	// Getters y Setters
	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	public String getTareaAsignada() {
		return tareaAsignada;
	}

	public void setTareaAsignada(String tareaAsignada) {
		this.tareaAsignada = tareaAsignada;
	}

}
