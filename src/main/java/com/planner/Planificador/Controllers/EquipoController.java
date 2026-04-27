package com.planner.Planificador.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.planner.Planificador.Dtos.Entidades.EquipoDto;
import com.planner.Planificador.Services.EquipoService;

@RestController
@RequestMapping("/equipos")
public class EquipoController {

	@Autowired
	private EquipoService equipoService;

	@GetMapping("/{id}")
	public ResponseEntity<EquipoDto> getEquipo(@PathVariable Integer id) {

		return ResponseEntity.ok(equipoService.getEquipo(id));

	}

}
