package com.planner.Planificador.Controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.planner.Planificador.Config.TokenUtil;
import com.planner.Planificador.Dtos.InicioSesionSolicitud;
import com.planner.Planificador.Dtos.UsuarioDto;
import com.planner.Planificador.Services.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private TokenUtil tokenUtil;

	@GetMapping
	public ResponseEntity<List<UsuarioDto>> listarUsuarios() {
		return ResponseEntity.ok(usuarioService.getTodosUsuarios());
	}

	@GetMapping("/{id}")
	public ResponseEntity<UsuarioDto> listarUsuarioPorId(@PathVariable Integer id) {
		try {
			return ResponseEntity.ok(usuarioService.getUsuarioPorId(id));
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}

	}

	@PostMapping("/iniciarSesion")
	public ResponseEntity<?> iniciarSesionEmailContra(@RequestBody InicioSesionSolicitud solicitudInicioSesion) {
		try {
			UsuarioDto usuario = usuarioService.iniciarSesion(solicitudInicioSesion.getEmail(),
					solicitudInicioSesion.getContra());

			String token = tokenUtil.generateToken(usuario.getEmail());

			Map<String, Object> respuesta = new HashMap<>();
			respuesta.put("token", token);
			respuesta.put("usuario", usuario);

			return ResponseEntity.ok(respuesta);
		} catch (RuntimeException e) {
			return ResponseEntity.status(401).body(e.getMessage());
		}
	}
}