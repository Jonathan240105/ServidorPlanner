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

import com.planner.Planificador.Dtos.CrearTareaSolicitud;
import com.planner.Planificador.Dtos.TareaDto;
import com.planner.Planificador.Services.TareaService;

@RestController
@RequestMapping("/tareas")
public class TareaController {

	@Autowired
	private TareaService tareaService;

	@GetMapping("/{id}")
	public ResponseEntity<List<TareaDto>> listarTareas(@PathVariable Integer id) {
		return ResponseEntity.ok(tareaService.getTodasTareasDeUnaLista(id));
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> eliminarTarea(@PathVariable Integer id) {
		try {
			tareaService.eliminarTarea(id);
			return ResponseEntity.ok("Tarea eliminada");
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PostMapping("/nuevo")
	public ResponseEntity<?> añadirTarea(@RequestBody CrearTareaSolicitud body, @RequestParam Integer usuario,
			@RequestParam Integer lista) {
		try {
			TareaDto tarea = tareaService.añadirTarea(body, usuario, lista);
			return ResponseEntity.ok(tarea);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

}
