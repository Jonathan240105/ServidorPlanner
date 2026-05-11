package com.planner.Planificador.Config;

public class TokenPayload {

	private String nombreUsuario;
	private String rol;
	private Integer idUsuario;

	// Constructores
	public TokenPayload() {
		super();
	}

	public TokenPayload(String nombreUsuario, String rol, Integer idUsuario) {
		super();
		this.nombreUsuario = nombreUsuario;
		this.rol = rol;
		this.idUsuario = idUsuario;
	}

	// Getters y Setters
	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

}
