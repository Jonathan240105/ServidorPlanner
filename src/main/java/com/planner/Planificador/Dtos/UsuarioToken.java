package com.planner.Planificador.Dtos;

public class UsuarioToken {

	private final Integer id;
	private final String username;
	private final String rol;

	public UsuarioToken(Integer id, String username, String rol) {
		this.id = id;
		this.username = username;
		this.rol = rol;
	}

	public Integer getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getRol() {
		return rol;
	}
}
