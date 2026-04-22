package com.planner.Planificador.Dtos.Entidades;

public class ListaDto {
	private String nombre;
	private String workSpace;

	// Constructores

	public ListaDto() {
		super();
	}

	public ListaDto(String nombre, String workSpace) {
		super();
		this.nombre = nombre;
		this.workSpace = workSpace;
	}

	// Getters y Setters
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getWorkSpace() {
		return workSpace;
	}

	public void setWorkSpace(String workSpace) {
		this.workSpace = workSpace;
	}

}