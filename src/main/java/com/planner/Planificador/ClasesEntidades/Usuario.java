package com.planner.Planificador.ClasesEntidades;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "Usuario")
public class Usuario {

	// Atributos
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_usuario")
	private Integer idUsuario;
	@Column(nullable = false)
	@NotBlank(message = "El nombre es obligatorio")
	private String nombre_usuario;
	@Column(unique = true, nullable = false)
	@Email(message = "El formato no es el correcto")
	private String email;
	@Column(nullable = false)
	@NotBlank(message = "La contraseña es obligatoria")
	@Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres")
	private String contra;
	private String foto_usuario;
	@Column(updatable = false)
	private LocalDateTime fecha_creacion;
	@Column(name = "esAdmin", nullable = false)
	private boolean esAdmin = false;

	// Constructor
	public Usuario() {
		super();
	}

	public Usuario(Integer id_usuario, String nombre_usuario, String email, String contra, String foto_usuario,
			LocalDateTime fecha_creacion) {
		super();
		this.idUsuario = id_usuario;
		this.nombre_usuario = nombre_usuario;
		this.email = email;
		this.contra = contra;
		this.foto_usuario = foto_usuario;
		this.fecha_creacion = fecha_creacion;
	}

	// Getters y Setters
	public Integer getId_usuario() {
		return idUsuario;
	}

	public void setId_usuario(Integer id_usuario) {
		this.idUsuario = id_usuario;
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

	public boolean getRol() {
		return esAdmin;
	}

	public void setRol(boolean esAdmin) {
		this.esAdmin = esAdmin;
	}

	// Tostring
	@Override
	public String toString() {
		return "Usuario [id_usuario=" + idUsuario + ", nombre_usuario=" + nombre_usuario + ", email=" + email
				+ ", contra=" + contra + ", foto_usuario=" + foto_usuario + ", fecha_creacion=" + fecha_creacion + "]";
	}
}