package com.planner.Planificador.Dtos;

public class SubtareaDto {

	private String titulo;
	private String tarea;
	private Boolean estado;

	// Constructores
	public SubtareaDto() {
		super();
	}

	public SubtareaDto(String titulo, String tarea, Boolean estado) {
		super();
		this.titulo = titulo;
		this.tarea = tarea;
		this.estado = estado;
	}

	// Getters y Setters
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTarea() {
		return tarea;
	}

	public void setTarea(String tarea) {
		this.tarea = tarea;
	}

	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

}
