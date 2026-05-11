package com.planner.Planificador.Services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.planner.Planificador.ClasesEntidades.Comentario;
import com.planner.Planificador.ClasesEntidades.Tarea;
import com.planner.Planificador.ClasesEntidades.Usuario;
import com.planner.Planificador.Dtos.Entidades.ComentarioDto;
import com.planner.Planificador.Repositorys.ComentarioRepository;
import com.planner.Planificador.Repositorys.TareaRepository;
import com.planner.Planificador.Repositorys.UsuarioRepository;

@Service
public class ComentarioService {

	@Autowired
	private ComentarioRepository comentariorepo;

	@Autowired
	private UsuarioRepository usuariorepo;

	@Autowired
	private TareaRepository tareaRepo;

	public List<ComentarioDto> getTodosComentariosDeUnaTarea(Integer idTarea, Integer idUsuarioToken) {
		Tarea tarea = tareaRepo.findById(idTarea).orElseThrow(() -> new RuntimeException("Tarea no encontrada"));

		Integer idDuenio = tarea.getLista().getWorkspace().getUsuarioAsignado().getId_usuario();
		if (!idDuenio.equals(idUsuarioToken)) {
			throw new RuntimeException("No tienes permiso para acceder a estos comentarios");
		}

		List<Comentario> listaComentarios = comentariorepo.findByTarea_idTarea(idTarea);

		return listaComentarios.stream()
				.map(comentario -> new ComentarioDto(comentario.getContenido(), comentario.getTarea().getTitulo()))
				.collect(Collectors.toList());
	}

	public ComentarioDto addComentario(String contenido, Integer idUsuarioToken, Integer idTarea) {
		Usuario usuario = usuariorepo.findById(idUsuarioToken)
				.orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

		Tarea tareaEncontrada = tareaRepo.findById(idTarea)
				.orElseThrow(() -> new RuntimeException("Tarea no encontrada"));

		Integer idCreador = tareaEncontrada.getLista().getWorkspace().getUsuarioAsignado().getId_usuario();
		if (!idCreador.equals(idUsuarioToken)) {
			throw new RuntimeException("No tienes permiso para acceder a estos comentarios");
		}

		Comentario comentario = new Comentario(contenido, LocalDateTime.now(), tareaEncontrada, usuario);
		comentariorepo.save(comentario);

		return new ComentarioDto(comentario.getContenido(), comentario.getTarea().getTitulo());
	}

	public void deleteComentario(Integer idComentario, Integer idUsuarioToken) {
		Comentario comentario = comentariorepo.findById(idComentario)
				.orElseThrow(() -> new RuntimeException("Comentario no encontrado"));

		Integer idCreador = comentario.getTarea().getLista().getWorkspace().getUsuarioAsignado().getId_usuario();
		if (!idCreador.equals(idUsuarioToken)) {
			throw new RuntimeException("No tienes permiso para acceder a estos comentarios");
		}

		comentariorepo.deleteById(idComentario);
	}

}
