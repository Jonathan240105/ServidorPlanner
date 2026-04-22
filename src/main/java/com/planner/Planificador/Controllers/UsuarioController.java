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
import com.planner.Planificador.Dtos.Entidades.UsuarioDto;
import com.planner.Planificador.Dtos.Solicitudes.InicioSesionSolicitud;
import com.planner.Planificador.Services.UsuarioService;
import com.planner.Planificador.Variables.Endpoints;

@RestController
@RequestMapping(Endpoints.Usuario.encabezadoUsuario)
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private TokenUtil tokenUtil;

	@GetMapping
	public ResponseEntity<List<UsuarioDto>> listarUsuarios() {
		return ResponseEntity.ok(usuarioService.getTodosUsuarios());
	}

	@GetMapping(Endpoints.Usuario.getUsuarioPorId)
	public ResponseEntity<UsuarioDto> listarUsuarioPorId(@PathVariable Integer id) {

		return ResponseEntity.ok(usuarioService.getUsuarioPorId(id));

	}

	@PostMapping(Endpoints.Usuario.iniciarSesion)
	public ResponseEntity<?> iniciarSesionEmailContra(@RequestBody InicioSesionSolicitud solicitudInicioSesion) {

		UsuarioDto usuario = usuarioService.iniciarSesion(solicitudInicioSesion.getEmail(),
				solicitudInicioSesion.getContra());

		String token = tokenUtil.generateToken(usuario.getEmail());

		Map<String, Object> respuesta = new HashMap<>();
		respuesta.put("token", token);
		respuesta.put("usuario", usuario);

		return ResponseEntity.ok(respuesta);

	}
}