package com.planner.Planificador.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.planner.Planificador.Dtos.ComentarioDto;
import com.planner.Planificador.Dtos.CrearComentarioSolicitud;
import com.planner.Planificador.Services.ComentarioService;

@RestController
@RequestMapping("/comentarios")
public class ComentarioController {

	@Autowired
	private ComentarioService comentarioService;

	@GetMapping("/{id}")
	public ResponseEntity<List<ComentarioDto>> listarComentarios(@PathVariable Integer id) {
		return ResponseEntity.ok(comentarioService.getTodosComentariosDeUnaTarea(id));
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> eliminarComentario(@PathVariable Integer id) {

		comentarioService.deleteComentario(id);
		return ResponseEntity.ok("Comentario eliminado");

	}

	@PostMapping("/nuevo")
	public ResponseEntity<?> crearComentario(@RequestBody CrearComentarioSolicitud contenido,
			@RequestParam Integer usuario, @RequestParam Integer tarea) {

		ComentarioDto comentario = comentarioService.addComentario(contenido.getContenido(), usuario, tarea);
		return ResponseEntity.ok(comentario);

	}
}
