package com.planner.Planificador.Services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.planner.Planificador.ClasesEntidades.Subtarea;
import com.planner.Planificador.ClasesEntidades.Tarea;
import com.planner.Planificador.Dtos.ActualizarSubtareaSolicitud;
import com.planner.Planificador.Dtos.SubtareaDto;
import com.planner.Planificador.Repositorys.SubtareaRepository;
import com.planner.Planificador.Repositorys.TareaRepository;

@Service
public class SubtareaService {

	@Autowired
	private SubtareaRepository subtareaRepo;

	@Autowired
	private TareaRepository tareaRepo;

	public List<SubtareaDto> getTodasSubtareasDeUnaTarea(Integer idSubtarea) {

		List<Subtarea> listaSubtareas = subtareaRepo.findByTarea_IdTarea(idSubtarea);

		return listaSubtareas.stream().map(subtarea -> new SubtareaDto(subtarea.getTitulo(),
				subtarea.getTarea().getTitulo(), subtarea.getEstado())).collect(Collectors.toList());
	}

	public void deleteSubtarea(Integer idSubtarea) {
		if (!subtareaRepo.existsById(idSubtarea)) {
			throw new RuntimeException("No se han encontrado subtareas");
		}
		subtareaRepo.deleteById(idSubtarea);
	}

	public SubtareaDto addSubtarea(Integer idTarea, String titulo) {

		Tarea tarea = tareaRepo.findById(idTarea).orElseThrow(() -> new RuntimeException("Tarea no encontrada"));

		Subtarea subtarea = new Subtarea(titulo, tarea, false);

		subtareaRepo.save(subtarea);

		return new SubtareaDto(subtarea.getTitulo(), tarea.getTitulo(), subtarea.getEstado());
	}

	public SubtareaDto updateSubtarea(Integer idSubtarea, ActualizarSubtareaSolicitud solicitud) {

		Subtarea subtarea = subtareaRepo.findById(idSubtarea)
				.orElseThrow(() -> new RuntimeException("Subtarea no encontrada"));

		if (solicitud.getTitulo() != null) {
			subtarea.setTitulo(solicitud.getTitulo());
		}

		if (solicitud.getEstado() != null) {
			subtarea.setEstado(solicitud.getEstado());
		}

		subtareaRepo.save(subtarea);

		return new SubtareaDto(subtarea.getTitulo(), subtarea.getTarea().getTitulo(), subtarea.getEstado());
	}
}
