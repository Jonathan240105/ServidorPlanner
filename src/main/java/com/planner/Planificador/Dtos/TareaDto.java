package com.planner.Planificador.Dtos;

import java.time.LocalDateTime;

public class TareaDto {
	private String titulo;
	private String descripcion;
	private String nombreLista;
	private String asignadoPor;
	private LocalDateTime fechaLimite;

	// Constructores
	public TareaDto() {
		super();
	}

	public TareaDto(String titulo, String descripcion, String nombreLista, LocalDateTime fechaLimite,
			String asignadoPor) {
		super();
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.nombreLista = nombreLista;
		this.fechaLimite = fechaLimite;
		this.asignadoPor = asignadoPor;
	}

	// Getters y Setters
	public String getNombreLista() {
		return nombreLista;
	}

	public void setNombreLista(String nombreLista) {
		this.nombreLista = nombreLista;
	}

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

	public String getAsignadoPor() {
		return asignadoPor;
	}

	public void setAsignadoPor(String asignadoPor) {
		this.asignadoPor = asignadoPor;
	}

}
