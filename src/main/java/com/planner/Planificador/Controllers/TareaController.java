package com.planner.Planificador.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.planner.Planificador.Dtos.UsuarioToken;
import com.planner.Planificador.Dtos.Actualizaciones.ActualizarTareaSolicitud;
import com.planner.Planificador.Dtos.Entidades.TareaDto;
import com.planner.Planificador.Dtos.Entidades.UsuarioEquipoDto;
import com.planner.Planificador.Dtos.Solicitudes.CrearTareaSolicitud;
import com.planner.Planificador.Services.TareaService;
import com.planner.Planificador.Variables.Endpoints;

@RestController
@RequestMapping(Endpoints.Tarea.encabezadoTarea)
public class TareaController {

	@Autowired
	private TareaService tareaService;

	@GetMapping(Endpoints.Tarea.getTareasLista)
	public ResponseEntity<List<TareaDto>> listarTareas(@PathVariable Integer id,
			@AuthenticationPrincipal UsuarioToken usuarioToken) {
		return ResponseEntity.ok(tareaService.getTodasTareasDeUnaLista(id, usuarioToken.getId()));
	}

	@DeleteMapping(Endpoints.Tarea.eliminarTarea)
	public ResponseEntity<?> eliminarTarea(@PathVariable Integer id,
			@AuthenticationPrincipal UsuarioToken usuarioToken) {
		tareaService.deleteTarea(id, usuarioToken.getId());
		return ResponseEntity.ok("Tarea eliminada");
	}

	@PostMapping(Endpoints.Tarea.nuevaTarea)
	public ResponseEntity<?> crearTarea(@RequestBody CrearTareaSolicitud body, @RequestParam Integer usuario,
			@RequestParam Integer lista, @AuthenticationPrincipal UsuarioToken usuarioToken) {

		TareaDto tarea = tareaService.addTarea(body, usuario, lista, usuarioToken);
		return ResponseEntity.ok(tarea);
	}

	@PutMapping(Endpoints.Tarea.moverTareaLista)
	public ResponseEntity<?> moverTareaDeLista(@PathVariable Integer idTarea, @PathVariable Integer idNuevaLista,
			@AuthenticationPrincipal UsuarioToken usuarioToken) {
		TareaDto tareaActualizada = tareaService.moverTareaDeLista(idTarea, idNuevaLista, usuarioToken.getId());
		return ResponseEntity.ok(tareaActualizada);
	}

	@PutMapping(Endpoints.Tarea.actualizarTarea)
	public ResponseEntity<?> actualizarTarea(@PathVariable Integer id, @RequestBody ActualizarTareaSolicitud body,
			@AuthenticationPrincipal UsuarioToken usuarioToken) {
		TareaDto tareaEditada = tareaService.updateTarea(id, body, usuarioToken.getId());
		return ResponseEntity.ok(tareaEditada);
	}

	@GetMapping(Endpoints.Tarea.listarMiembros)
	public ResponseEntity<List<UsuarioEquipoDto>> listarMiembros(
			@AuthenticationPrincipal UsuarioToken usuarioToken) {
		return ResponseEntity.ok(tareaService.getMiembrosDropdownEquipo(usuarioToken.getId()));
	}
}
