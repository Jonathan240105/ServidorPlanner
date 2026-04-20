package com.planner.Planificador.Services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.planner.Planificador.ClasesEntidades.Lista;
import com.planner.Planificador.ClasesEntidades.WorkSpace;
import com.planner.Planificador.Dtos.ActualizarListaSolicitud;
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
		listaRepo.save(listaNueva);
		return new ListaDto(listaNueva.getNombre_lista(), listaNueva.getWorkspace());
	}

	public void eliminarLista(Integer id) {
		if (!listaRepo.existsById(id)) {
			throw new RuntimeException("Lista no encontrada");
		}
		listaRepo.deleteById(id);
	}

	public ListaDto actualizarNombreLista(Integer idLista, ActualizarListaSolicitud solicitud) {

		Lista lista = listaRepo.findById(idLista).orElseThrow(() -> new RuntimeException("Lista no encontrada"));

		if (solicitud.getNombre() != null) {
			lista.setNombre_lista(solicitud.getNombre());
		}

		listaRepo.save(lista);

		return new ListaDto(lista.getNombre_lista(), lista.getWorkspace());
	}
}