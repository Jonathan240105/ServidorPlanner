package com.planner.Planificador.Dtos.Entidades;

public class UsuarioDto {
	private Integer id;
	private String nombre;
	private String email;
	private String contra;
	private String foto;

	// Constructores
	public UsuarioDto() {
		super();
	}

	public UsuarioDto(Integer id, String nombre, String email, String foto) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.email = email;
		this.foto = foto;
	}

	public UsuarioDto(String email, String contra) {
		super();
		this.email = email;
		this.contra = contra;
	}

	// Getters y Setters
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

}
