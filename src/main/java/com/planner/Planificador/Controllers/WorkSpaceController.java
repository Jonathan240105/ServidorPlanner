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

import com.planner.Planificador.Dtos.Actualizaciones.ActualizarWorkSpaceSolicitud;
import com.planner.Planificador.Dtos.Entidades.WorkSpaceDto;
import com.planner.Planificador.Dtos.Solicitudes.CrearWorkSpaceSolicitud;
import com.planner.Planificador.Services.WorkSpaceService;
import com.planner.Planificador.Variables.Endpoints;

@RestController
@RequestMapping(Endpoints.Workspace.encabezadoWorkspace)
public class WorkSpaceController {

	@Autowired
	private WorkSpaceService workSpaceService;

	@GetMapping(Endpoints.Workspace.getWorkspacesUsuario)
	public ResponseEntity<List<WorkSpaceDto>> listarTodosWorkSpaceDeUnUsuario(@PathVariable Integer id) {
		return ResponseEntity.ok(workSpaceService.getTodosWorkSpaceDeUnUsuario(id));
	}

	@DeleteMapping(Endpoints.Workspace.eliminarWorkspace)
	public ResponseEntity<?> eliminarWorkSpace(@PathVariable Integer id) {

		workSpaceService.deleteWorkSpace(id);
		return ResponseEntity.ok("WorkSpace eliminado");

	}

	@PostMapping(Endpoints.Workspace.nuevoWorkspace)
	public ResponseEntity<?> crearWorkSpace(@RequestBody CrearWorkSpaceSolicitud body,
			@RequestParam Integer idUsuarioAsignado) {

		WorkSpaceDto workSpace = workSpaceService.addWorkSpace(body, idUsuarioAsignado);
		return ResponseEntity.ok(workSpace);

	}

	@GetMapping(Endpoints.Workspace.buscarWorkspace)
	public ResponseEntity<List<WorkSpaceDto>> listarWorkSpacePorNombre(@PathVariable Integer idUsuario,
			@RequestParam String nombre) {

		return ResponseEntity.ok(workSpaceService.getTodosWorkSpaceDeUnUsuarioPorTitulo(idUsuario, nombre));
	}

	@PutMapping(Endpoints.Workspace.actualizarWorkspace)
	public ResponseEntity<?> actualizarWorkSpace(@PathVariable Integer id,
			@RequestBody ActualizarWorkSpaceSolicitud body) {

		WorkSpaceDto wsActualizado = workSpaceService.updateWorkSpace(id, body);
		return ResponseEntity.ok(wsActualizado);

	}
}
