package com.planner.Planificador.ClasesEntidades;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "workspace")
public class WorkSpace {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_workspace;
	private String nombre;
	private String descripcion;
	@ManyToOne
	@JoinColumn(name = "id_usuario_asignado")
	private Usuario usuarioAsignado;
	@Column(insertable = false, updatable = false)
	private LocalDateTime fecha_creacion;

	// Constructor
	public WorkSpace() {
		super();
	}

	public WorkSpace(Integer id_workspace, String nombre, String descripcion, Usuario usuarioAsignado,
			LocalDateTime fecha_creacion) {
		super();
		this.id_workspace = id_workspace;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.usuarioAsignado = usuarioAsignado;
		this.fecha_creacion = fecha_creacion;
	}

	// Getters y Setters
	public Integer getId_workspace() {
		return id_workspace;
	}

	public void setId_workspace(Integer id_workspace) {
		this.id_workspace = id_workspace;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Usuario getUsuarioAsignado() {
		return usuarioAsignado;
	}

	public void setUsuarioAsignado(Usuario usuarioAsignado) {
		this.usuarioAsignado = usuarioAsignado;
	}

	public LocalDateTime getFecha_creacion() {
		return fecha_creacion;
	}

	public void setFecha_creacion(LocalDateTime fecha_creacion) {
		this.fecha_creacion = fecha_creacion;
	}

	// Tostring
	@Override
	public String toString() {
		return "WorkSpace [id_workspace=" + id_workspace + ", nombre=" + nombre + ", descripcion=" + descripcion
				+ ", usuarioAsignado=" + usuarioAsignado + ", fecha_creacion=" + fecha_creacion + "]";
	}

}