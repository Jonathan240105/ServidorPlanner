package com.planner.Planificador.Dtos;

public class InicioSesionDto {
	private String email;
	private String contra;

	// Constructor
	public InicioSesionDto(String email, String contra) {
		super();
		this.email = email;
		this.contra = contra;
	}

	// Getters y Setters
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

}
