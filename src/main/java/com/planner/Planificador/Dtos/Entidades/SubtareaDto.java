package com.planner.Planificador.Dtos.Entidades;

public class SubtareaDto {

	private Integer id;
	private String titulo;
	private String tarea;
	private Boolean estado;

	// Constructores
	public SubtareaDto() {
		super();
	}

	public SubtareaDto(Integer id, String titulo, String tarea, Boolean estado) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.tarea = tarea;
		this.estado = estado;
	}

	// Getters y Setters
	public String getTitulo() {
		return titulo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
