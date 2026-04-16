package com.planner.Planificador.ClasesEntidades;

import jakarta.persistence.*;

@Entity
@Table(name = "lista")
public class Lista {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idLista;
	private String nombre_lista;
	private int posicion;
	@ManyToOne
	@JoinColumn(name = "id_workspace")
	private WorkSpace workSpace;

	// Constructor

	public Lista() {
		super();
	}

	public Lista( String nombre_lista, int posicion, WorkSpace workspace) {
		super();
		this.nombre_lista = nombre_lista;
		this.posicion = posicion;
		this.workSpace = workspace;
	}

	// Getters y Setters
	public Integer getIdLista() {
		return idLista;
	}

	public void setIdLista(Integer id_lista) {
		this.idLista = id_lista;
	}

	public String getNombre_lista() {
		return nombre_lista;
	}

	public void setNombre_lista(String nombre_lista) {
		this.nombre_lista = nombre_lista;
	}

	public int getPosicion() {
		return posicion;
	}

	public void setPosicion(int posicion) {
		this.posicion = posicion;
	}

	public WorkSpace getWorkspace() {
		return workSpace;
	}

	public void setWorkspace(WorkSpace workspace) {
		this.workSpace = workspace;
	}

	// Tostring
	@Override
	public String toString() {
		return "Lista [id_lista=" + idLista + ", nombre_lista=" + nombre_lista + ", posicion=" + posicion
				+ ", workspace=" + workSpace + "]";
	}

}
