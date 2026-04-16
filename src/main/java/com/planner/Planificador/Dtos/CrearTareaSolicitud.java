package com.planner.Planificador.Dtos;

import java.time.LocalDateTime;

public class CrearTareaSolicitud {

	private String titulo;
	private String descripcion;
	private LocalDateTime fecha_limite;
	
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
	public LocalDateTime getFecha_limite() {
		return fecha_limite;
	}
	public void setFecha_limite(LocalDateTime fecha_limite) {
		this.fecha_limite = fecha_limite;
	}
	
}
