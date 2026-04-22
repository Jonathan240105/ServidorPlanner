package com.planner.Planificador.ClasesEntidades;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Comentario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_comentario;
	@Column(nullable = false)
	@NotBlank(message = "El contenido del comentario no puede estar vacío")
	private String contenido;
	@Column(insertable = false, updatable = false)
	private LocalDateTime fecha_creacion;
	@ManyToOne
	@JoinColumn(name = "id_usuario_creador")
	private Usuario usuarioCreador;
	@ManyToOne
	@JoinColumn(name = "id_tarea_asignada")
	private Tarea tarea;

	// Constructor

	public Comentario() {
		super();
	}

	public Comentario(String contenido, LocalDateTime fecha_creacion, Tarea tarea, Usuario usuarioCreador) {
		super();
		this.contenido = contenido;
		this.fecha_creacion = fecha_creacion;
		this.tarea = tarea;
		this.usuarioCreador = usuarioCreador;
	}

	// Getters y Setters
	public Integer getId_comentario() {
		return id_comentario;
	}

	public void setId_comentario(Integer id_comentario) {
		this.id_comentario = id_comentario;
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	public LocalDateTime getFecha_creacion() {
		return fecha_creacion;
	}

	public void setFecha_creacion(LocalDateTime fecha_creacion) {
		this.fecha_creacion = fecha_creacion;
	}

	public Tarea getTarea() {
		return tarea;
	}

	public void setTarea(Tarea tarea) {
		this.tarea = tarea;
	}

	public Usuario getUsuarioCreador() {
		return usuarioCreador;
	}

	public void setUsuarioCreador(Usuario usuarioCreador) {
		this.usuarioCreador = usuarioCreador;
	}

	// Tostring
	@Override
	public String toString() {
		return "Comentario [id_comentario=" + id_comentario + ", contenido=" + contenido + ", fecha_creacion="
				+ fecha_creacion + ", tarea=" + tarea + ", usuarioCreador=" + usuarioCreador + "]";
	}

}
