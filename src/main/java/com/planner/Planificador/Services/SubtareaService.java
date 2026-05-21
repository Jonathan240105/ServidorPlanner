package com.planner.Planificador.Services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.planner.Planificador.ClasesEntidades.Subtarea;
import com.planner.Planificador.ClasesEntidades.Tarea;
import com.planner.Planificador.Dtos.UsuarioToken;
import com.planner.Planificador.Dtos.Actualizaciones.ActualizarSubtareaSolicitud;
import com.planner.Planificador.Dtos.Entidades.SubtareaDto;
import com.planner.Planificador.Repositorys.SubtareaRepository;
import com.planner.Planificador.Repositorys.TareaRepository;

@Service
public class SubtareaService {

	@Autowired
	private SubtareaRepository subtareaRepo;

	@Autowired
	private TareaRepository tareaRepo;

	public List<SubtareaDto> getTodasSubtareasDeUnaTarea(Integer idTarea, Integer idUsuario) {
		Tarea tarea = tareaRepo.findById(idTarea).orElseThrow(() -> new RuntimeException("Tarea no encontrada"));

		Integer ownerId = tarea.getLista().getWorkspace().getUsuarioAsignado().getId_usuario();
		if (!ownerId.equals(idUsuario)) {
			throw new RuntimeException("No tienes permiso sobre los recursos de esta tarea");
		}

		List<Subtarea> listaSubtareas = subtareaRepo.findByTarea_IdTarea(idTarea);

		return listaSubtareas.stream().map(subtarea -> new SubtareaDto(subtarea.getIdSubtarea(), subtarea.getTitulo(),
				subtarea.getTarea().getTitulo(), subtarea.getEstado())).collect(Collectors.toList());
	}

	public void deleteSubtarea(Integer idSubtarea, Integer idUsuario) {
		Subtarea subtarea = subtareaRepo.findById(idSubtarea)
				.orElseThrow(() -> new RuntimeException("Subtarea no encontrada"));

		Integer ownerId = subtarea.getTarea().getLista().getWorkspace().getUsuarioAsignado().getId_usuario();
		if (!ownerId.equals(idUsuario)) {
			throw new RuntimeException("No tienes permiso sobre los recursos de esta tarea");
		}

		subtareaRepo.delete(subtarea);
	}

	public SubtareaDto addSubtarea(Integer idTarea, String titulo, Integer idUsuario) {
		Tarea tarea = tareaRepo.findById(idTarea).orElseThrow(() -> new RuntimeException("Tarea no encontrada"));

		Integer ownerId = tarea.getLista().getWorkspace().getUsuarioAsignado().getId_usuario();
		if (!ownerId.equals(idUsuario)) {
			throw new RuntimeException("No tienes permiso sobre los recursos de esta tarea");
		}

		Subtarea subtarea = new Subtarea(titulo, tarea, false);
		subtareaRepo.save(subtarea);

		return new SubtareaDto(subtarea.getIdSubtarea(), subtarea.getTitulo(), tarea.getTitulo(), subtarea.getEstado());
	}

	public SubtareaDto updateSubtarea(Integer idSubtarea, ActualizarSubtareaSolicitud solicitud, Integer idUsuario) {
		Subtarea subtarea = subtareaRepo.findById(idSubtarea)
				.orElseThrow(() -> new RuntimeException("Subtarea no encontrada"));

		Integer ownerId = subtarea.getTarea().getLista().getWorkspace().getUsuarioAsignado().getId_usuario();
		if (!ownerId.equals(idUsuario)) {
			throw new RuntimeException("No tienes permiso sobre los recursos de esta tarea");
		}

		if (solicitud.getTitulo() != null)
			subtarea.setTitulo(solicitud.getTitulo());
		if (solicitud.getEstado() != null)
			subtarea.setEstado(solicitud.getEstado());

		subtareaRepo.save(subtarea);

		return new SubtareaDto(subtarea.getIdSubtarea(), subtarea.getTitulo(), subtarea.getTarea().getTitulo(),
				subtarea.getEstado());
		
	}

	public SubtareaDto cambiarEstadoSubtarea(Integer idSubtarea, UsuarioToken usuarioToken) {
		Subtarea subtarea = subtareaRepo.findById(idSubtarea)
				.orElseThrow(() -> new RuntimeException("Subtarea no encontrada"));

		boolean esAdmin = "admin".equals(usuarioToken.getRol());

		if (!esAdmin) {
			Integer usuarioAsignado = subtarea.getTarea().getLista().getWorkspace().getUsuarioAsignado()
					.getId_usuario();
			if (!usuarioAsignado.equals(usuarioToken.getId())) {
				throw new RuntimeException("No tienes permiso sobre los recursos de esta tarea");
			}
		}

		subtarea.setEstado(!subtarea.getEstado());
		subtareaRepo.save(subtarea);

		return new SubtareaDto(subtarea.getIdSubtarea(), subtarea.getTitulo(), subtarea.getTarea().getTitulo(),
				subtarea.getEstado());
	}

}