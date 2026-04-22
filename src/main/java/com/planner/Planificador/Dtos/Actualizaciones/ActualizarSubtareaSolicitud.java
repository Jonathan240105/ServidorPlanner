package com.planner.Planificador.Dtos.Actualizaciones;

public class ActualizarSubtareaSolicitud {

	private String titulo;
	private Boolean estado;

	// Constructores
	public ActualizarSubtareaSolicitud() {
		super();
	}

	public ActualizarSubtareaSolicitud(String titulo, Boolean estado) {
		super();
		this.titulo = titulo;
		this.estado = estado;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

}
