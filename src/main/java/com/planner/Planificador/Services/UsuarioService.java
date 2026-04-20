package com.planner.Planificador.Services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.planner.Planificador.ClasesEntidades.Usuario;
import com.planner.Planificador.Dtos.UsuarioDto;
import com.planner.Planificador.Repositorys.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepo;

	public List<UsuarioDto> getTodosUsuarios() {
		return usuarioRepo.findAll().stream().map(usuario -> new UsuarioDto(usuario.getId_usuario(),
				usuario.getNombre_usuario(), usuario.getEmail(), usuario.getFoto_usuario()))
				.collect(Collectors.toList());

	}

	public UsuarioDto getUsuarioPorId(Integer id) {

		Usuario usuario = usuarioRepo.findById(id).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
		return new UsuarioDto(usuario.getId_usuario(), usuario.getNombre_usuario(), usuario.getEmail(),
				usuario.getFoto_usuario());
	}

	public UsuarioDto iniciarSesion(String email, String contra) {

		Usuario usuario = usuarioRepo.findByEmail(email)
				.orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

		if (!usuario.getContra().equals(contra)) {
			throw new RuntimeException("Contraseña incorrecta");
		}

		return new UsuarioDto(usuario.getId_usuario(), usuario.getNombre_usuario(), usuario.getEmail(),
				usuario.getFoto_usuario());
	}
}
