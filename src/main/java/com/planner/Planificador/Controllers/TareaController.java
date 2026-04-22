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
import org.springframework.web.service.annotation.PutExchange;

import com.planner.Planificador.Dtos.Actualizaciones.ActualizarTareaSolicitud;
import com.planner.Planificador.Dtos.Entidades.TareaDto;
import com.planner.Planificador.Dtos.Solicitudes.CrearTareaSolicitud;
import com.planner.Planificador.Services.TareaService;
import com.planner.Planificador.Variables.Endpoints;

@RestController
@RequestMapping(Endpoints.Tarea.encabezadoTarea)
public class TareaController {

	@Autowired
	private TareaService tareaService;

	@GetMapping(Endpoints.Tarea.getTareasLista)
	public ResponseEntity<List<TareaDto>> listarTareas(@PathVariable Integer id) {
		return ResponseEntity.ok(tareaService.getTodasTareasDeUnaLista(id));
	}

	@DeleteMapping(Endpoints.Lista.eliminarLista)
	public ResponseEntity<?> eliminarTarea(@PathVariable Integer id) {

		tareaService.deleteTarea(id);
		return ResponseEntity.ok("Tarea eliminada");

	}

	@PostMapping(Endpoints.Tarea.nuevaLista)
	public ResponseEntity<?> crearTarea(@RequestBody CrearTareaSolicitud body, @RequestParam Integer usuario,
			@RequestParam Integer lista, @RequestParam Integer usuarioCreador) {

		TareaDto tarea = tareaService.addTarea(body, usuario, lista, usuarioCreador);
		return ResponseEntity.ok(tarea);

	}

	@GetMapping(Endpoints.Tarea.buscarTareasPorTitulo)
	public ResponseEntity<List<TareaDto>> listarTareasPorTitulo(@PathVariable Integer idLista,
			@RequestParam String titulo) {
		return ResponseEntity.ok(tareaService.getTareasPorTitulo(idLista, titulo));
	}

	@PutExchange(Endpoints.Tarea.moverTareaLista)
	public ResponseEntity<?> moverTareaDeLista(@PathVariable Integer idTarea, @PathVariable Integer idNuevaLista) {

		TareaDto tareaActualizada = tareaService.moverTareaDeLista(idTarea, idNuevaLista);
		return ResponseEntity.ok(tareaActualizada);

	}

	@PutMapping(Endpoints.Tarea.actualizarTarea)
	public ResponseEntity<?> actualizarTarea(@PathVariable Integer id, @RequestBody ActualizarTareaSolicitud body) {

		TareaDto tareaEditada = tareaService.updateTarea(id, body);
		return ResponseEntity.ok(tareaEditada);

	}
}
