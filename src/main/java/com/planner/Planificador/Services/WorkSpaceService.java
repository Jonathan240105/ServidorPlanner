package com.planner.Planificador.Services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.planner.Planificador.ClasesEntidades.Usuario;
import com.planner.Planificador.ClasesEntidades.WorkSpace;
import com.planner.Planificador.Dtos.ActualizarWorkSpaceSolicitud;
import com.planner.Planificador.Dtos.CrearWorkSpaceSolicitud;
import com.planner.Planificador.Dtos.WorkSpaceDto;
import com.planner.Planificador.Repositorys.UsuarioRepository;
import com.planner.Planificador.Repositorys.WorkSpaceRepository;

@Service
public class WorkSpaceService {

	@Autowired
	private WorkSpaceRepository workSpaceRepo;

	@Autowired
	private UsuarioRepository usuarioRepo;

	public WorkSpaceDto crearWorkSpace(CrearWorkSpaceSolicitud body, Integer usuarioAsignado) {

		Usuario usuario = usuarioRepo.findById(usuarioAsignado)
				.orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

		WorkSpace workSpace = new WorkSpace(body.getTitulo(), body.getDescripcion(), usuario, LocalDateTime.now());

		workSpaceRepo.save(workSpace);

		return new WorkSpaceDto(workSpace.getNombre(), workSpace.getDescripcion(), workSpace.getUsuarioAsignado());
	}

	public List<WorkSpaceDto> getTodosWorkSpaceDeUnUsuario(Integer usuario) {
		List<WorkSpace> listaWorkSpaces = workSpaceRepo.findByUsuarioAsignado_IdUsuario(usuario);

		if (listaWorkSpaces.isEmpty()) {
			throw new RuntimeException("No se encontraron WorkSpaces");
		}

		return listaWorkSpaces.stream()
				.map(w -> new WorkSpaceDto(w.getNombre(), w.getDescripcion(), w.getUsuarioAsignado()))
				.collect(Collectors.toList());
	}

	public void eliminarWorkSpace(Integer id) {
		if (!workSpaceRepo.existsById(id)) {
			throw new RuntimeException("WorkSpace no encontrado");
		}

		workSpaceRepo.deleteById(id);
	}

	public List<WorkSpaceDto> getTodosWorkSpaceDeUnUsuarioPorTitulo(Integer idUsuario, String nombre) {
		List<WorkSpace> listaWorkSpaces = workSpaceRepo.findByUsuarioAsignado_IdUsuarioAndNombreStartingWith(idUsuario,
				nombre);

		if (listaWorkSpaces.isEmpty()) {
			throw new RuntimeException("No se encontraron workSpaces con ese nombre");
		}

		return listaWorkSpaces.stream().map(workSpace -> new WorkSpaceDto(workSpace.getNombre(),
				workSpace.getDescripcion(), workSpace.getUsuarioAsignado())).collect(Collectors.toList());
	}

	public WorkSpaceDto actualizarNombreWorkSpace(Integer idWorkSpace, ActualizarWorkSpaceSolicitud solicitud) {

		WorkSpace workSpace = workSpaceRepo.findById(idWorkSpace)
				.orElseThrow(() -> new RuntimeException("WorkSpace no encontrado"));

		if (solicitud.getNombre() != null) {
			workSpace.setNombre(solicitud.getNombre());
		}

		workSpaceRepo.save(workSpace);

		return new WorkSpaceDto(workSpace.getNombre(), workSpace.getDescripcion(), workSpace.getUsuarioAsignado());
	}

}
