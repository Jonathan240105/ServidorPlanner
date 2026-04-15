package com.planner.Planificador.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.planner.Planificador.Dtos.InicioSesionDto;
import com.planner.Planificador.Dtos.UsuarioDto;
import com.planner.Planificador.Services.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@GetMapping
	public ResponseEntity<List<UsuarioDto>> listarUsuarios() {
		return ResponseEntity.ok(usuarioService.getTodosUsuarios());
	}

	@GetMapping("/{id}")
	public ResponseEntity<UsuarioDto> getUsuarioPorId(@PathVariable Integer id) {
		try {
			return ResponseEntity.ok(usuarioService.getUsuarioPorId(id));
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}

	}

	@PostMapping("/iniciarSesion")
	public ResponseEntity<?> iniciarSesionEmailContra(@RequestBody InicioSesionDto solicitudInicioSesion) {
		try {
			UsuarioDto usuario = usuarioService.iniciarSesion(solicitudInicioSesion.getEmail(),
					solicitudInicioSesion.getContra());

			return ResponseEntity.ok(usuario);
		} catch (RuntimeException e) {
			return ResponseEntity.status(401).body(e.getMessage());
		}
	}
}