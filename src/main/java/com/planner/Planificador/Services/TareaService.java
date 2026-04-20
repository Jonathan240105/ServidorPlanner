package com.planner.Planificador.Services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.planner.Planificador.ClasesEntidades.Lista;
import com.planner.Planificador.ClasesEntidades.Tarea;
import com.planner.Planificador.ClasesEntidades.Usuario;
import com.planner.Planificador.Dtos.ActualizarTareaSolicitud;
import com.planner.Planificador.Dtos.CrearTareaSolicitud;
import com.planner.Planificador.Dtos.TareaDto;
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

	public List<TareaDto> getTodasTareasDeUnaLista(Integer idLista) {
		List<Tarea> listaTareas = tareaRepo.findByLista_idLista(idLista);

		if (listaTareas.isEmpty()) {
			throw new RuntimeException("No se han encontrado tareas");
		}

		return listaTareas.stream()
				.map(tarea -> new TareaDto(tarea.getTitulo(), tarea.getDescripcion(), tarea.getFecha_creacion()))
				.collect(Collectors.toList());
	}

	public TareaDto addTarea(CrearTareaSolicitud body, Integer usuarioAsignado, Integer idLista) {
		Usuario usuario = usuarioRepo.findById(usuarioAsignado)
				.orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

		Lista lista = listaRepo.findById(idLista).orElseThrow(() -> new RuntimeException("Lista no encontrada"));

		int totalTareas = tareaRepo.countByLista_idLista(usuarioAsignado);

		Tarea tarea = new Tarea(body.getTitulo(), body.getDescripcion(), totalTareas + 1, body.getFecha_limite(),
				LocalDateTime.now(), lista, usuario);

		tareaRepo.save(tarea);
		return new TareaDto(tarea.getTitulo(), tarea.getDescripcion(), tarea.getFecha_limite());
	}

	public void deleteTarea(Integer id) {
		if (!tareaRepo.existsById(id)) {
			throw new RuntimeException("Tarea no encontrada");
		}
		tareaRepo.deleteById(id);
	}

	public List<TareaDto> getTareasPorTitulo(Integer idLista, String titulo) {
		List<Tarea> listaTareas = tareaRepo.findByLista_idListaAndTituloStartingWith(idLista, titulo);

		return listaTareas.stream()
				.map(tarea -> new TareaDto(tarea.getTitulo(), tarea.getDescripcion(), tarea.getFecha_limite()))
				.collect(Collectors.toList());
	}

	public TareaDto moverTareaDeLista(Integer idTarea, Integer idNuevaLista) {

		Tarea tarea = tareaRepo.findById(idTarea)
				.orElseThrow(() -> new RuntimeException("No se han encontrado tareas"));

		Lista lista = listaRepo.findById(idNuevaLista)
				.orElseThrow(() -> new RuntimeException("No se han encontrado listas"));

		tarea.setLista(lista);

		tareaRepo.save(tarea);

		return new TareaDto(tarea.getTitulo(), tarea.getDescripcion(), tarea.getFecha_creacion());
	}

	public TareaDto updateTarea(Integer idTarea, ActualizarTareaSolicitud solicitud) {
		Tarea tarea = tareaRepo.findById(idTarea).orElseThrow(() -> new RuntimeException("Tarea no encontrada"));

		if (solicitud.getTitulo() != null) {
			tarea.setTitulo(solicitud.getTitulo());
		}

		if (solicitud.getFecha_limite() != null) {
			tarea.setFecha_limite(solicitud.getFecha_limite());
		}

		tareaRepo.save(tarea);

		return new TareaDto(tarea.getTitulo(), tarea.getDescripcion(), tarea.getFecha_limite());
	}
}
