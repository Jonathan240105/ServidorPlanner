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

import com.planner.Planificador.Dtos.Actualizaciones.ActualizarListaSolicitud;
import com.planner.Planificador.Dtos.Entidades.ListaDto;
import com.planner.Planificador.Services.ListaServices;
import com.planner.Planificador.Variables.Endpoints;

@RestController
@RequestMapping(Endpoints.Lista.encabezadoLista)
public class ListaController {

	@Autowired
	private ListaServices listaServices;

	@GetMapping(Endpoints.Lista.getListasWorkspace)
	public ResponseEntity<List<ListaDto>> getTodasListasDeUnWorkSpace(@PathVariable Integer id) {
		return ResponseEntity.ok(listaServices.getTodasListasDeUnWorkSpace(id));
	}

	@DeleteMapping(Endpoints.Lista.eliminarLista)
	public ResponseEntity<?> eliminarLista(@PathVariable Integer id) {

		listaServices.deleteLista(id);
		return ResponseEntity.ok("Lista eliminada");

	}

	@PostMapping(Endpoints.Lista.nuevaLista)
	public ResponseEntity<?> crearLista(@RequestParam String nombre, @RequestParam Integer idWorkSpace) {

		ListaDto lista = listaServices.addLista(nombre, idWorkSpace);
		return ResponseEntity.ok(lista);

	}

	@PutMapping(Endpoints.Lista.actualizarLista)
	public ResponseEntity<?> actualizarLista(@PathVariable Integer id, @RequestBody ActualizarListaSolicitud body) {

		ListaDto listaActualizada = listaServices.updateNombreLista(id, body);
		return ResponseEntity.ok(listaActualizada);

	}
}