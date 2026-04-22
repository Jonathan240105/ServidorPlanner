package com.planner.Planificador.Dtos.Actualizaciones;

import java.time.LocalDateTime;

public class ActualizarTareaSolicitud {

	private String titulo;
	private LocalDateTime fecha_limite;

	//Constructor
	public ActualizarTareaSolicitud(String titulo, LocalDateTime fecha_limite) {
		super();
		this.titulo = titulo;
		this.fecha_limite = fecha_limite;
	}

	//Getters y Setters
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public LocalDateTime getFecha_limite() {
		return fecha_limite;
	}

	public void setFecha_limite(LocalDateTime fecha_limite) {
		this.fecha_limite = fecha_limite;
	}

}