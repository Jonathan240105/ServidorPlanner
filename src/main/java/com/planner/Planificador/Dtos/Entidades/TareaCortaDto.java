package com.planner.Planificador.Dtos.Entidades;

public class TareaCortaDto {

	// Atributos
	private Integer idTarea;
	private String titulo;

	// Constructor
	public TareaCortaDto(Integer idTarea, String titulo) {
		this.idTarea = idTarea;
		this.titulo = titulo;
	}

	// Getters y Setters
	public Integer getIdTarea() {
		return idTarea;
	}

	public void setIdTarea(Integer idTarea) {
		this.idTarea = idTarea;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
}