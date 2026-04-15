package com.planner.Planificador.ClasesDto;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "Usuario")
public class Usuario {

	// Atributos
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_usuario;
	private String nombre_usuario;
	private String email;
	private String contra;
	private String foto_usuario;
	private LocalDateTime fecha_creacion;

	// Constructor
	public Usuario() {
		super();
	}

	public Usuario(Integer id_usuario, String nombre_usuario, String email, String contra, String foto_usuario,
			LocalDateTime fecha_creacion) {
		super();
		this.id_usuario = id_usuario;
		this.nombre_usuario = nombre_usuario;
		this.email = email;
		this.contra = contra;
		this.foto_usuario = foto_usuario;
		this.fecha_creacion = fecha_creacion;
	}

	// Getters y Setters
	public Integer getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(Integer id_usuario) {
		this.id_usuario = id_usuario;
	}

	public String getNombre_usuario() {
		return nombre_usuario;
	}

	public void setNombre_usuario(String nombre_usuario) {
		this.nombre_usuario = nombre_usuario;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContra() {
		return contra;
	}

	public void setContra(String contra) {
		this.contra = contra;
	}

	public String getFoto_usuario() {
		return foto_usuario;
	}

	public void setFoto_usuario(String foto_usuario) {
		this.foto_usuario = foto_usuario;
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
		return "Usuario [id_usuario=" + id_usuario + ", nombre_usuario=" + nombre_usuario + ", email=" + email
				+ ", contra=" + contra + ", foto_usuario=" + foto_usuario + ", fecha_creacion=" + fecha_creacion + "]";
	}
}