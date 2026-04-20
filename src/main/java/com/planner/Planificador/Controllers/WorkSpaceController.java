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

import com.planner.Planificador.Dtos.ActualizarWorkSpaceSolicitud;
import com.planner.Planificador.Dtos.CrearWorkSpaceSolicitud;
import com.planner.Planificador.Dtos.WorkSpaceDto;
import com.planner.Planificador.Services.WorkSpaceService;

@RestController
@RequestMapping("/workSpaces")
public class WorkSpaceController {

	@Autowired
	private WorkSpaceService workSpaceService;

	@GetMapping("/{id}")
	public ResponseEntity<List<WorkSpaceDto>> listarTodosWorkSpaceDeUnUsuario(@PathVariable Integer id) {
		return ResponseEntity.ok(workSpaceService.getTodosWorkSpaceDeUnUsuario(id));
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> eliminarWorkSpace(@PathVariable Integer id) {
		try {
			workSpaceService.deleteWorkSpace(id);
			return ResponseEntity.ok("WorkSpace eliminado");
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PostMapping("/nuevo")
	public ResponseEntity<?> crearWorkSpace(@RequestBody CrearWorkSpaceSolicitud body,
			@RequestParam Integer idUsuarioAsignado) {
		try {
			WorkSpaceDto workSpace = workSpaceService.addWorkSpace(body, idUsuarioAsignado);
			return ResponseEntity.ok(workSpace);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@GetMapping("/buscar/{idUsuario}")
	public ResponseEntity<List<WorkSpaceDto>> listarWorkSpacePorNombre(@PathVariable Integer idUsuario,
			@RequestParam String nombre) {

		return ResponseEntity.ok(workSpaceService.getTodosWorkSpaceDeUnUsuarioPorTitulo(idUsuario, nombre));
	}

	@PutMapping("/actualizar/{id}")
	public ResponseEntity<?> actualizarWorkSpace(@PathVariable Integer id,
			@RequestBody ActualizarWorkSpaceSolicitud body) {
		try {
			WorkSpaceDto wsActualizado = workSpaceService.updateWorkSpace(id, body);
			return ResponseEntity.ok(wsActualizado);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
}
