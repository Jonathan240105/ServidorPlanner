package com.planner.Planificador.Controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.planner.Planificador.Config.TokenPayload;
import com.planner.Planificador.Config.TokenUtil;
import com.planner.Planificador.Dtos.UsuarioToken;
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
	public ResponseEntity<UsuarioDto> listarUsuarioPorId(@AuthenticationPrincipal UsuarioToken usuario) {

		return ResponseEntity.ok(usuarioService.getUsuarioPorId(usuario.getId()));

	}

	@PostMapping(Endpoints.Usuario.iniciarSesion)
	public ResponseEntity<?> iniciarSesionEmailContra(@RequestBody InicioSesionSolicitud solicitudInicioSesion) {

		UsuarioDto usuario = usuarioService.iniciarSesion(solicitudInicioSesion.getEmail(),
				solicitudInicioSesion.getContra());

		System.out.println("Rol del payload: " + usuario.getRol());
		TokenPayload payload = new TokenPayload(usuario.getNombre(), usuario.getRol(), usuario.getId());
		String token = tokenUtil.generateToken(payload);

		Map<String, Object> respuesta = new HashMap<>();
		respuesta.put("token", token);
		respuesta.put("usuario", usuario);

		return ResponseEntity.ok(respuesta);

	}
}