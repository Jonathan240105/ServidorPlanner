package com.planner.Planificador.Dtos;

import java.time.LocalDateTime;

public class TareaDto {
	private String titulo;
	private String descripcion;
	private LocalDateTime fechaLimite;
	
	//Constructores
	public TareaDto() {
		super();
	}


	public TareaDto(String titulo, String descripcion, LocalDateTime fechaLimite) {
		super();
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.fechaLimite = fechaLimite;
	}


	//Getters y Setters
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


	public LocalDateTime getFechaLimite() {
		return fechaLimite;
	}


	public void setFechaLimite(LocalDateTime fechaLimite) {
		this.fechaLimite = fechaLimite;
	}
	
	
	
}
