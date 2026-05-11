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
	public ResponseEntity<List<WorkSpaceDto>> listarWorkSpaceDeUnUsuario(
			@AuthenticationPrincipal UsuarioToken usuario) {

		return ResponseEntity.ok(workSpaceService.getWorkSpaceDeUnUsuario(usuario.getId()));
	}

	@DeleteMapping(Endpoints.Workspace.eliminarWorkspace)
	public ResponseEntity<?> eliminarWorkSpace(@AuthenticationPrincipal UsuarioToken usuario) {

		workSpaceService.deleteWorkSpace(usuario.getId());
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
	public ResponseEntity<?> actualizarWorkSpace(@AuthenticationPrincipal UsuarioToken usuario,
			@RequestBody ActualizarWorkSpaceSolicitud body) {

		WorkSpaceDto wsActualizado = workSpaceService.updateWorkSpace(usuario.getId(), body);
		return ResponseEntity.ok(wsActualizado);

	}
}
