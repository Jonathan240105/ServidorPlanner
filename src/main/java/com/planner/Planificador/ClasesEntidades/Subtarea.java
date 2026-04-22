package com.planner.Planificador.ClasesEntidades;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Subtarea")
public class Subtarea {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idSubtarea;
	private String titulo;
	@ManyToOne
	@JoinColumn(name = "id_tarea")
	private Tarea tarea;
	private Boolean estado;

	// Constructores
	public Subtarea() {
		super();
	}

	public Subtarea(String titulo, Tarea tarea, boolean estado) {
		super();
		this.titulo = titulo;
		this.tarea = tarea;
		this.estado = estado;
	}

	// Getters y Setters
	public Integer getIdSubtarea() {
		return idSubtarea;
	}

	public void setIdSubtarea(Integer idSubtarea) {
		this.idSubtarea = idSubtarea;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Tarea getTarea() {
		return tarea;
	}

	public void setTarea(Tarea idTarea) {
		this.tarea = idTarea;
	}

	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

}
