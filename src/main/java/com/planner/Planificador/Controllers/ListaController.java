package com.planner.Planificador.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.planner.Planificador.Dtos.ListaDto;
import com.planner.Planificador.Services.ListaServices;

@RestController
@RequestMapping("/lista")
public class ListaController {

	@Autowired
	private ListaServices listaServices;

	@GetMapping("/{id}")
	public ResponseEntity<List<ListaDto>> getTodasListasDeUnWorkSpace(@PathVariable Integer id) {
		return ResponseEntity.ok(listaServices.getTodasListasDeUnWorkSpace(id));
	}
}
