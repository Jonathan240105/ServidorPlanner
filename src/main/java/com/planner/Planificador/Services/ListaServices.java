package com.planner.Planificador.Services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.planner.Planificador.ClasesEntidades.Lista;
import com.planner.Planificador.ClasesEntidades.WorkSpace;
import com.planner.Planificador.Dtos.ListaDto;
import com.planner.Planificador.Repositorys.ListaRepository;
import com.planner.Planificador.Repositorys.WorkSpaceRepository;

@Service
public class ListaServices {

	@Autowired
	private ListaRepository listaRepo;

	@Autowired
	private WorkSpaceRepository workSpaceRepo;


	public List<ListaDto> getTodasListasDeUnWorkSpace(Integer idWorkSpace) {
		List<Lista> listaDeListas = listaRepo.findByWorkSpace_idWorkspace(idWorkSpace);

		if (listaDeListas.isEmpty()) {
			throw new RuntimeException("No se han encontrado listas");
		}
		return listaDeListas.stream().map(lista -> new ListaDto(lista.getNombre_lista(), lista.getWorkspace()))
				.collect(Collectors.toList());
	}

	public ListaDto crearLista(String nombre, Integer IdWorkSpace) {

		WorkSpace workSpaceAsignado = workSpaceRepo.findById(IdWorkSpace)
				.orElseThrow(() -> new RuntimeException("No se ha encontrado ningún workSpace"));
		Integer totalListas = listaRepo.countByWorkSpace_IdWorkspace(IdWorkSpace);

		Lista listaNueva = new Lista(nombre, totalListas + 1, workSpaceAsignado);
		return new ListaDto(listaNueva.getNombre_lista(), listaNueva.getWorkspace());
	}


	public void eliminarLista(Integer ig) {
		if (!listaRepo.existsById(ig)) {
			throw new RuntimeException("Lista no encontrada");
		}
		listaRepo.deleteById(ig);
	}
}
