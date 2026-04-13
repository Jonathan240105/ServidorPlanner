package com.planner.Planificador;

import java.time.LocalDateTime;

import jakarta.persistence.*;

public class Comentario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_comentario;
	private String contenido;
	@Column(insertable = false, updatable = false)
	private LocalDateTime fecha_creacion;
	@ManyToOne
	@JoinColumn(name = "id_lista_asignada")
	private Lista lista;
	@ManyToOne
	@JoinColumn(name = "id_usuario_creador")
	private Usuario usuarioCreador;

	// Constructor
	public Comentario(Integer id_comentario, String contenido, LocalDateTime fecha_creacion, Lista lista,
			Usuario usuarioCreador) {
		super();
		this.id_comentario = id_comentario;
		this.contenido = contenido;
		this.fecha_creacion = fecha_creacion;
		this.lista = lista;
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

	public Lista getLista() {
		return lista;
	}

	public void setLista(Lista lista) {
		this.lista = lista;
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
				+ fecha_creacion + ", lista=" + lista + ", usuarioCreador=" + usuarioCreador + "]";
	}

}
