package com.planner.Planificador.Services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.planner.Planificador.ClasesEntidades.Lista;
import com.planner.Planificador.ClasesEntidades.Usuario;
import com.planner.Planificador.ClasesEntidades.WorkSpace;
import com.planner.Planificador.Dtos.Actualizaciones.ActualizarWorkSpaceSolicitud;
import com.planner.Planificador.Dtos.Entidades.WorkSpaceDto;
import com.planner.Planificador.Dtos.Solicitudes.CrearWorkSpaceSolicitud;
import com.planner.Planificador.Repositorys.ListaRepository;
import com.planner.Planificador.Repositorys.UsuarioRepository;
import com.planner.Planificador.Repositorys.WorkSpaceRepository;

@Service
public class WorkSpaceService {

	@Autowired
	private WorkSpaceRepository workSpaceRepo;

	@Autowired
	private UsuarioRepository usuarioRepo;

	@Autowired
	private ListaRepository listaRepo;

	public WorkSpaceDto addWorkSpace(CrearWorkSpaceSolicitud body, Integer usuarioAsignado) {

		Usuario usuario = usuarioRepo.findById(usuarioAsignado)
				.orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

		WorkSpace workSpace = new WorkSpace(body.getTitulo(), body.getDescripcion(), usuario, LocalDateTime.now());

		workSpaceRepo.save(workSpace);

		List<String> listasPorDefecto = List.of("To do", "Doing", "Done");

		int contador = 1;
		for (String nombre : listasPorDefecto) {
			Lista listaNueva = new Lista(nombre, contador, workSpace);
			listaRepo.save(listaNueva);
			contador++;
		}

		return new WorkSpaceDto(workSpace.getNombre(), workSpace.getDescripcion(), usuario.getNombre_usuario());
	}

	public List<WorkSpaceDto> getWorkSpaceDeUnUsuario(Integer usuario) {
		List<WorkSpace> listaWorkSpaces = workSpaceRepo.findByUsuarioAsignado_IdUsuario(usuario);

		Usuario usuarioAsignado = usuarioRepo.findById(usuario)
				.orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

		if (listaWorkSpaces.isEmpty()) {
			throw new RuntimeException("No se encontraron WorkSpaces");
		}

		return listaWorkSpaces.stream()
				.map(w -> new WorkSpaceDto(w.getNombre(), w.getDescripcion(), usuarioAsignado.getNombre_usuario()))
				.collect(Collectors.toList());
	}

	public void deleteWorkSpace(Integer id) {
		if (!workSpaceRepo.existsById(id)) {
			throw new RuntimeException("WorkSpace no encontrado");
		}

		workSpaceRepo.deleteById(id);
	}

	public List<WorkSpaceDto> getTodosWorkSpaceDeUnUsuarioPorTitulo(Integer idUsuario, String nombre) {
		List<WorkSpace> listaWorkSpaces = workSpaceRepo.findByUsuarioAsignado_IdUsuarioAndNombreStartingWith(idUsuario,
				nombre);
		Usuario usuarioAsignado = usuarioRepo.findById(idUsuario)
				.orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

		if (listaWorkSpaces.isEmpty()) {
			throw new RuntimeException("No se encontraron workSpaces con ese nombre");
		}

		return listaWorkSpaces.stream().map(workSpace -> new WorkSpaceDto(workSpace.getNombre(),
				workSpace.getDescripcion(), usuarioAsignado.getNombre_usuario())).collect(Collectors.toList());
	}

	public WorkSpaceDto updateWorkSpace(Integer idWorkSpace, ActualizarWorkSpaceSolicitud solicitud) {

		WorkSpace workSpace = workSpaceRepo.findById(idWorkSpace)
				.orElseThrow(() -> new RuntimeException("WorkSpace no encontrado"));

		Usuario usuarioAsignado = usuarioRepo.findById(workSpace.getUsuarioAsignado().getId_usuario())
				.orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

		if (solicitud.getNombre() != null) {
			workSpace.setNombre(solicitud.getNombre());
		}

		workSpaceRepo.save(workSpace);

		return new WorkSpaceDto(workSpace.getNombre(), workSpace.getDescripcion(), usuarioAsignado.getNombre_usuario());
	}

}
