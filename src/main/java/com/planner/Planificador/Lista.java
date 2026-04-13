package com.planner.Planificador;

import jakarta.persistence.*;

@Entity
@Table(name = "lista")
public class Lista {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_lista;
	private String nombre_lista;
	private Integer posicion;
	@ManyToOne
	@JoinColumn(name = "id_workspace")
	private WorkSpace workspace;

	// Constructor
	public Lista(Integer id_lista, String nombre_lista, Integer posicion, WorkSpace workspace) {
		super();
		this.id_lista = id_lista;
		this.nombre_lista = nombre_lista;
		this.posicion = posicion;
		this.workspace = workspace;
	}

	// Getters y Setters
	public Integer getId_lista() {
		return id_lista;
	}

	public void setId_lista(Integer id_lista) {
		this.id_lista = id_lista;
	}

	public String getNombre_lista() {
		return nombre_lista;
	}

	public void setNombre_lista(String nombre_lista) {
		this.nombre_lista = nombre_lista;
	}

	public Integer getPosicion() {
		return posicion;
	}

	public void setPosicion(Integer posicion) {
		this.posicion = posicion;
	}

	public WorkSpace getWorkspace() {
		return workspace;
	}

	public void setWorkspace(WorkSpace workspace) {
		this.workspace = workspace;
	}

	// Tostring
	@Override
	public String toString() {
		return "Lista [id_lista=" + id_lista + ", nombre_lista=" + nombre_lista + ", posicion=" + posicion
				+ ", workspace=" + workspace + "]";
	}

}
