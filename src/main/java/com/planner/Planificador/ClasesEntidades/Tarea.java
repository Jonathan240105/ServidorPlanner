package com.planner.Planificador.ClasesEntidades;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
public class Tarea {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_tarea;
	private String titulo;
	private String descripcion;
	private int posicion;
	private LocalDateTime fecha_limite;
	@Column(insertable = false, updatable = false)
	private LocalDateTime fecha_creacion;
	@ManyToOne
	@JoinColumn(name = "id_lista_asignada")
	private Lista lista;
	@ManyToOne
	@JoinColumn(name = "id_usuario_asignado")
	private Usuario usuarioAsignado;

	// Constructor

	public Tarea() {
		super();
	}

	public Tarea( String titulo, String descripcion, int posicion, LocalDateTime fecha_limite,
			LocalDateTime fecha_creacion, Lista lista, Usuario usuarioAsignado) {
		super();
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.posicion = posicion;
		this.fecha_limite = fecha_limite;
		this.fecha_creacion = fecha_creacion;
		this.lista = lista;
		this.usuarioAsignado = usuarioAsignado;
	}

	// Getters y Setters
	public Integer getId_tarea() {
		return id_tarea;
	}

	public void setId_tarea(Integer id_tarea) {
		this.id_tarea = id_tarea;
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

	public int getPosicion() {
		return posicion;
	}

	public void setPosicion(int posicion) {
		this.posicion = posicion;
	}

	public LocalDateTime getFecha_limite() {
		return fecha_limite;
	}

	public void setFecha_limite(LocalDateTime fecha_limite) {
		this.fecha_limite = fecha_limite;
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

	public Usuario getUsuarioAsignado() {
		return usuarioAsignado;
	}

	public void setUsuarioAsignado(Usuario usuarioAsignado) {
		this.usuarioAsignado = usuarioAsignado;
	}

	// Tostring
	@Override
	public String toString() {
		return "Tarea [id_tarea=" + id_tarea + ", titulo=" + titulo + ", descripcion=" + descripcion + ", posicion="
				+ posicion + ", fecha_limite=" + fecha_limite + ", fecha_creacion=" + fecha_creacion + ", lista="
				+ lista + ", usuarioAsignado=" + usuarioAsignado + "]";
	}

}
