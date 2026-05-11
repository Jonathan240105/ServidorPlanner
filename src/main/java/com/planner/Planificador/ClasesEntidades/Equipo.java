package com.planner.Planificador.ClasesEntidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "equipo")
public class Equipo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_equipo", length = 5)
	private Integer idEquipo;

	@Column(name = "nombre_equipo", length = 50)
	private String nombreEquipo;

	@ManyToOne
	@JoinColumn(name = "id_usuario_admin")
	private Usuario usuarioAdmin;

	// Constructores
	public Equipo() {
	}
 
	public Equipo(String nombreEquipo, Usuario usuarioAdmin) {
		super();
		this.nombreEquipo = nombreEquipo;
		this.usuarioAdmin = usuarioAdmin;
	}

	// Getters y Setters
	public Integer getIdEquipo() {
		return idEquipo;
	}

	public void setIdEquipo(Integer idEquipo) {
		this.idEquipo = idEquipo;
	}

	public String getNombreEquipo() {
		return nombreEquipo;
	}

	public void setNombreEquipo(String nombreEquipo) {
		this.nombreEquipo = nombreEquipo;
	}

	public Usuario getUsuarioAdmin() {
		return usuarioAdmin;
	}

	public void setUsuarioAdmin(Usuario usuarioAdmin) {
		this.usuarioAdmin = usuarioAdmin;
	}

}
