package com.planner.Planificador.Dtos;

import com.planner.Planificador.ClasesEntidades.WorkSpace;

public class ListaDto {
	private String nombre;
	private WorkSpace workSpace;

	// Constructores

	public ListaDto() {
		super();
	}

	public ListaDto(String nombre, WorkSpace workSpace) {
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

	public WorkSpace getWorkSpace() {
		return workSpace;
	}

	public void setWorkSpace(WorkSpace workSpace) {
		this.workSpace = workSpace;
	}

}