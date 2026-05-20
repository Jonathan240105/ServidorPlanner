package com.planner.Planificador.Dtos.Entidades;

import java.util.List;

public class ListaConTareaDto {

	// Atributos
	private Integer idLista;
	private String nombreLista;
	private List<TareaCortaDto> tareas;
	private int posicion;

	// Constructor
	public ListaConTareaDto(Integer idLista, String nombreLista, List<TareaCortaDto> tareas, int posicion) {
		this.idLista = idLista;
		this.nombreLista = nombreLista;
		this.tareas = tareas;
		this.posicion = posicion;
	}

	// Getters y Setters
	public Integer getIdLista() {
		return idLista;
	}

	public void setIdLista(Integer idLista) {
		this.idLista = idLista;
	}

	public String getNombreLista() {
		return nombreLista;
	}

	public void setNombreLista(String nombreLista) {
		this.nombreLista = nombreLista;
	}

	public List<TareaCortaDto> getTareas() {
		return tareas;
	}

	public void setTareas(List<TareaCortaDto> tareas) {
		this.tareas = tareas;
	}

	public int getPosicion() {
		return posicion;
	}

	public void setPosicion(int posicion) {
		this.posicion = posicion;
	}

}
