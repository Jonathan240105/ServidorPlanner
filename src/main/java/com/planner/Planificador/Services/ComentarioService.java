package com.planner.Planificador.Services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.planner.Planificador.ClasesEntidades.Comentario;
import com.planner.Planificador.ClasesEntidades.Tarea;
import com.planner.Planificador.ClasesEntidades.Usuario;
import com.planner.Planificador.Dtos.ComentarioDto;
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

	public List<ComentarioDto> getTodosComentariosDeUnaTarea(Integer idTarea) {

		List<Comentario> listaComentarios = comentariorepo.findByTarea_idTarea(idTarea);

		if (listaComentarios.isEmpty()) {
			throw new RuntimeException("No se han encontrado comentarios");
		}

		return listaComentarios.stream().map(comentario -> new ComentarioDto(comentario.getContenido()))
				.collect(Collectors.toList());
	}

	public ComentarioDto añadirComentario(String contenido, Integer usuarioCreador, Integer tarea) {

		Usuario usuario = usuariorepo.findById(usuarioCreador).orElseThrow(() -> new RuntimeException("No se ha encontrado ningun usuario"));
		
		Tarea tareaEncontrada = tareaRepo.findById(tarea).orElseThrow(() -> new RuntimeException("No se ha encontrad ninguna tarea"));
		
		Comentario comentario = new Comentario(contenido,LocalDateTime.now(),tareaEncontrada,usuario);
		
		return new ComentarioDto(comentario.getContenido());
	}
	
	public void eliminarComentario() {
		
	}
}
