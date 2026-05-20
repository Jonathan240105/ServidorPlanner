package com.planner.Planificador.Services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import com.planner.Planificador.ClasesEntidades.Lista;
import com.planner.Planificador.ClasesEntidades.Usuario;
import com.planner.Planificador.ClasesEntidades.WorkSpace;
import com.planner.Planificador.Dtos.UsuarioToken;
import com.planner.Planificador.Dtos.Actualizaciones.ActualizarListaSolicitud;
import com.planner.Planificador.Dtos.Entidades.ListaConTareaDto;
import com.planner.Planificador.Dtos.Entidades.ListaDto;
import com.planner.Planificador.Dtos.Entidades.TareaCortaDto;
import com.planner.Planificador.Dtos.Entidades.WorkSpaceDto;
import com.planner.Planificador.Repositorys.ListaRepository;
import com.planner.Planificador.Repositorys.TareaRepository;
import com.planner.Planificador.Repositorys.UsuarioRepository;
import com.planner.Planificador.Repositorys.WorkSpaceRepository;
import com.planner.Planificador.Variables.Endpoints.Workspace;

@Service
public class ListaServices {

	@Autowired
	private ListaRepository listaRepo;

	@Autowired
	private WorkSpaceRepository workSpaceRepo;

	@Autowired
	private TareaRepository tareaRepo;

	@Autowired
	private UsuarioRepository usuarioRepo;

	public List<ListaDto> getTodasListasDeUnWorkSpace(Integer idWorkSpace, Integer idUsuario) {

		WorkSpace workSpaceAsignado = workSpaceRepo.findById(idWorkSpace)
				.orElseThrow(() -> new RuntimeException("No se ha encontrado ningún workSpace"));

		if (!workSpaceAsignado.getUsuarioAsignado().getId_usuario().equals(idUsuario)) {
			throw new RuntimeException("No tienes permiso para ver este WorkSpace");
		}

		List<Lista> listaDeListas = listaRepo.findByWorkSpace_idWorkspace(idWorkSpace);

		if (listaDeListas.isEmpty()) {
			throw new RuntimeException("No se han encontrado listas");
		}
		return listaDeListas.stream().map(lista -> new ListaDto(lista.getNombre_lista(), workSpaceAsignado.getNombre()))
				.collect(Collectors.toList());
	}

	public ListaDto addLista(String nombre, Integer idUsuario) {

		List<WorkSpace> listaWorkSpaces = workSpaceRepo.findByUsuarioAsignado_IdUsuario(idUsuario);

		if (listaWorkSpaces.isEmpty()) {
			throw new RuntimeException("No tienes workspace");
		}

		WorkSpace workSpacePrincipal = listaWorkSpaces.get(0);

		Integer totalListas = listaRepo.countByWorkSpace_IdWorkspace(idUsuario);
		Lista listaNueva = new Lista(nombre, totalListas + 1, workSpacePrincipal);
		listaRepo.save(listaNueva);
		return new ListaDto(listaNueva.getNombre_lista(), workSpacePrincipal.getNombre());
	}

	public void deleteLista(Integer id, Integer idUsuario) {

		Lista lista = listaRepo.findById(id).orElseThrow(() -> new RuntimeException("Lista no encontrada"));

		if (!lista.getWorkspace().getUsuarioAsignado().getId_usuario().equals(idUsuario)) {
			throw new RuntimeException("No tienes permiso para eliminar esta lista");
		}

		listaRepo.delete(lista);
	}

	public ListaDto updateNombreLista(Integer idLista, ActualizarListaSolicitud solicitud, Integer idUsuario) {

		Lista lista = listaRepo.findById(idLista).orElseThrow(() -> new RuntimeException("Lista no encontrada"));

		if (!lista.getWorkspace().getUsuarioAsignado().getId_usuario().equals(idUsuario)) {
			throw new RuntimeException("No tienes permiso para modificar esta lista");
		}

		if (solicitud.getNombre() != null) {
			lista.setNombre_lista(solicitud.getNombre());
		}

		listaRepo.save(lista);

		return new ListaDto(lista.getNombre_lista(), lista.getWorkspace().getNombre());
	}

	public List<ListaConTareaDto> getListadoConNombresTareas(Integer idUsuario) {
		List<Lista> listasDelUsuario = listaRepo.findByWorkSpace_UsuarioAsignado_IdUsuario(idUsuario);

		if (listasDelUsuario.isEmpty()) {
			throw new RuntimeException("No se encontraron listas para este usuario");
		}

		return listasDelUsuario.stream().map(lista -> {
			List<TareaCortaDto> tareasCortas = tareaRepo.findByLista_idLista(lista.getIdLista()).stream()
					.map(tarea -> new TareaCortaDto(tarea.getIdTarea(), tarea.getTitulo()))
					.collect(Collectors.toList());

			return new ListaConTareaDto(lista.getIdLista(), lista.getNombre_lista(), tareasCortas, lista.getPosicion());
		}).collect(Collectors.toList());
	}

	public List<ListaConTareaDto> getListadoEquipoConNombresTareas(Integer idUsuario) {
		Usuario usuario = usuarioRepo.findById(idUsuario)
				.orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

		if (usuario.getEquipo() == null) {
			throw new RuntimeException("No perteneces a ningún equipo");
		}

		List<Lista> listasDelEquipo = listaRepo
				.findByWorkSpace_UsuarioAsignado_Equipo_IdEquipo(usuario.getEquipo().getIdEquipo());

		if (listasDelEquipo.isEmpty()) {
			throw new RuntimeException("No se encontraron listas para este equipo");
		}

		return listasDelEquipo.stream().map(lista -> {
			List<TareaCortaDto> tareasCortas = tareaRepo.findByLista_idLista(lista.getIdLista()).stream()
					.map(tarea -> new TareaCortaDto(tarea.getIdTarea(), tarea.getTitulo()))
					.collect(Collectors.toList());

			return new ListaConTareaDto(lista.getIdLista(), lista.getNombre_lista(), tareasCortas, lista.getPosicion());
		}).collect(Collectors.toList());
	}
}
