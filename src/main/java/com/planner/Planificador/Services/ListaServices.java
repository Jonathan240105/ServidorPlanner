package com.planner.Planificador.Services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.planner.Planificador.ClasesEntidades.Lista;
import com.planner.Planificador.Dtos.ListaDto;
import com.planner.Planificador.Repositorys.ListaRepository;

@Service
public class ListaServices {

	@Autowired
	private ListaRepository listaRepo;

	public List<ListaDto> getTodasListasDeUnWorkSpace(Integer idWorkSpace) {
		List<Lista> listaDeListas = listaRepo.findByWorkSpace_idWorkspace(idWorkSpace);

		if (listaDeListas.isEmpty()) {
			throw new RuntimeException("No se han encontrado listas");
		}
		return listaDeListas.stream().map(lista -> new ListaDto(lista.getNombre_lista(), lista.getWorkspace()))
				.collect(Collectors.toList());
	}

}
