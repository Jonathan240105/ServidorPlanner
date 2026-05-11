package com.planner.Planificador.Services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.planner.Planificador.ClasesEntidades.Lista;
import com.planner.Planificador.ClasesEntidades.Tarea;
import com.planner.Planificador.ClasesEntidades.Usuario;
import com.planner.Planificador.Dtos.Actualizaciones.ActualizarTareaSolicitud;
import com.planner.Planificador.Dtos.Entidades.TareaDto;
import com.planner.Planificador.Dtos.Solicitudes.CrearTareaSolicitud;
import com.planner.Planificador.Repositorys.ListaRepository;
import com.planner.Planificador.Repositorys.TareaRepository;
import com.planner.Planificador.Repositorys.UsuarioRepository;

@Service
public class TareaService {

	@Autowired
	private TareaRepository tareaRepo;
	@Autowired
	private UsuarioRepository usuarioRepo;
	@Autowired
	private ListaRepository listaRepo;

	public List<TareaDto> getTodasTareasDeUnaLista(Integer idLista, Integer idUsuario) {
		Lista lista = listaRepo.findById(idLista).orElseThrow(() -> new RuntimeException("Lista no encontrada"));

		if (!lista.getWorkspace().getUsuarioAsignado().getId_usuario().equals(idUsuario)) {
			throw new RuntimeException("No tienes permiso para ver estas tareas");
		}

		List<Tarea> listaTareas = tareaRepo.findByLista_idLista(idLista);
		return listaTareas.stream()
				.map(tarea -> new TareaDto(tarea.getTitulo(), tarea.getDescripcion(), lista.getNombre_lista(),
						tarea.getFecha_creacion(), tarea.getAsignadaPor().getNombre_usuario()))
				.collect(Collectors.toList());
	}

	public TareaDto addTarea(CrearTareaSolicitud body, Integer usuarioAsignadoId, Integer idLista,
			Integer idUsuarioToken) {
		Lista lista = listaRepo.findById(idLista).orElseThrow(() -> new RuntimeException("Lista no encontrada"));

		if (!lista.getWorkspace().getUsuarioAsignado().getId_usuario().equals(idUsuarioToken)) {
			throw new RuntimeException("No tienes permiso para crear tareas aquí");
		}

		Usuario asignado = usuarioRepo.findById(usuarioAsignadoId).orElseThrow();
		Usuario creador = usuarioRepo.findById(idUsuarioToken).orElseThrow();

		int totalTareas = tareaRepo.countByLista_idLista(idLista);
		Tarea tarea = new Tarea(body.getTitulo(), body.getDescripcion(), totalTareas + 1, body.getFecha_limite(),
				LocalDateTime.now(), lista, asignado, creador);

		tareaRepo.save(tarea);
		return new TareaDto(tarea.getTitulo(), tarea.getDescripcion(), lista.getNombre_lista(), tarea.getFecha_limite(),
				creador.getNombre_usuario());
	}

	public void deleteTarea(Integer id, Integer idUsuario) {
		Tarea tarea = tareaRepo.findById(id).orElseThrow(() -> new RuntimeException("Tarea no encontrada"));

		if (!tarea.getLista().getWorkspace().getUsuarioAsignado().getId_usuario().equals(idUsuario)) {
			throw new RuntimeException("No tienes permiso para borrar esta tarea");
		}
		tareaRepo.delete(tarea);
	}

	public TareaDto moverTareaDeLista(Integer idTarea, Integer idNuevaLista, Integer idUsuario) {
		Tarea tarea = tareaRepo.findById(idTarea).orElseThrow();
		Lista nuevaLista = listaRepo.findById(idNuevaLista).orElseThrow();

		boolean esCreadorTarea = tarea.getLista().getWorkspace().getUsuarioAsignado().getId_usuario().equals(idUsuario);
		boolean esCreadorNuevaLista = nuevaLista.getWorkspace().getUsuarioAsignado().getId_usuario().equals(idUsuario);

		if (!esCreadorTarea || !esCreadorNuevaLista) {
			throw new RuntimeException("No tienes permiso");
		}

		tarea.setLista(nuevaLista);
		tareaRepo.save(tarea);
		return new TareaDto(tarea.getTitulo(), tarea.getDescripcion(), nuevaLista.getNombre_lista(),
				tarea.getFecha_creacion(), tarea.getAsignadaPor().getNombre_usuario());
	}

	public TareaDto updateTarea(Integer idTarea, ActualizarTareaSolicitud solicitud, Integer idUsuario) {
		Tarea tarea = tareaRepo.findById(idTarea).orElseThrow();

		if (!tarea.getLista().getWorkspace().getUsuarioAsignado().getId_usuario().equals(idUsuario)) {
			throw new RuntimeException("No tienes permiso");
		}

		if (solicitud.getTitulo() != null)
			tarea.setTitulo(solicitud.getTitulo());
		if (solicitud.getFecha_limite() != null)
			tarea.setFecha_limite(solicitud.getFecha_limite());

		tareaRepo.save(tarea);
		return new TareaDto(tarea.getTitulo(), tarea.getDescripcion(), tarea.getLista().getNombre_lista(),
				tarea.getFecha_limite(), tarea.getAsignadaPor().getNombre_usuario());
	}
}
