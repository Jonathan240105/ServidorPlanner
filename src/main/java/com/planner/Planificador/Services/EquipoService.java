package com.planner.Planificador.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.planner.Planificador.ClasesEntidades.Equipo;
import com.planner.Planificador.Dtos.Entidades.EquipoDto;
import com.planner.Planificador.Repositorys.EquipoRepository;

@Service
public class EquipoService {

	@Autowired
	private EquipoRepository equipoRepo;

	public EquipoDto getEquipo(Integer idEquipo) {
		Equipo equipo = equipoRepo.findById(idEquipo).orElseThrow(() -> new RuntimeException("Error en la bd"));

		return new EquipoDto(equipo.getNombreEquipo(), equipo.getUsuarioAdmin().getNombre_usuario());
	}
	
	

}
