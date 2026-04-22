package com.planner.Planificador.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.planner.Planificador.Dtos.ActualizarSubtareaSolicitud;
import com.planner.Planificador.Dtos.SubtareaDto;
import com.planner.Planificador.Services.SubtareaService;

@RestController
@RequestMapping("/subtareas")
public class SubtareaController {

	@Autowired
	private SubtareaService subtareaService;

	@GetMapping("/{idSubtarea}")
	public ResponseEntity<List<SubtareaDto>> listarSubtareas(@PathVariable Integer idSubtarea) {
		return ResponseEntity.ok(subtareaService.getTodasSubtareasDeUnaTarea(idSubtarea));
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> eliminarSubtarea(@PathVariable Integer id) {

		subtareaService.deleteSubtarea(id);
		return ResponseEntity.ok("Subtarea eliminada");

	}

	@PostMapping("/nuevo")
	public ResponseEntity<?> crearSubtarea(@RequestParam Integer idTarea, @RequestParam String titulo) {

		SubtareaDto subtareaNueva = subtareaService.addSubtarea(idTarea, titulo);
		return ResponseEntity.ok(subtareaNueva);

	}

	@PutMapping("/actualizar/{id}")
	public ResponseEntity<?> actualizarSubtarea(@PathVariable Integer id,
			@RequestBody ActualizarSubtareaSolicitud body) {

		SubtareaDto subtareaActualizada = subtareaService.updateSubtarea(id, body);
		return ResponseEntity.ok(subtareaActualizada);

	}
}
