package com.planner.Planificador.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.planner.Planificador.Dtos.UsuarioToken;
import com.planner.Planificador.Dtos.Entidades.ComentarioDto;
import com.planner.Planificador.Dtos.Solicitudes.CrearComentarioSolicitud;
import com.planner.Planificador.Services.ComentarioService;
import com.planner.Planificador.Variables.Endpoints;

@RestController
@RequestMapping(Endpoints.Comentario.encabezadoComentario)
public class ComentarioController {

	@Autowired
	private ComentarioService comentarioService;

	@GetMapping(Endpoints.Comentario.getComentariosTarea)
	public ResponseEntity<List<ComentarioDto>> listarComentarios(@PathVariable Integer id,
			@AuthenticationPrincipal UsuarioToken usuarioToken) {
		return ResponseEntity.ok(comentarioService.getTodosComentariosDeUnaTarea(id, usuarioToken.getId()));
	}

	@DeleteMapping(Endpoints.Comentario.eliminarComentario)
	public ResponseEntity<?> eliminarComentario(@PathVariable Integer id,
			@AuthenticationPrincipal UsuarioToken usuarioToken) {
		comentarioService.deleteComentario(id, usuarioToken.getId());
		return ResponseEntity.ok("Comentario eliminado");
	}

	@PostMapping(Endpoints.Comentario.nuevoComentario)
	public ResponseEntity<?> crearComentario(@RequestBody CrearComentarioSolicitud solicitud,
			@RequestParam Integer tarea, @AuthenticationPrincipal UsuarioToken usuarioToken) {
		// Usamos el ID del token para el usuario creador
		ComentarioDto comentario = comentarioService.addComentario(solicitud.getContenido(), usuarioToken.getId(),
				tarea);
		return ResponseEntity.ok(comentario);
	}
}