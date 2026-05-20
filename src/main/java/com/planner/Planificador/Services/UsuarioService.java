package com.planner.Planificador.Services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.planner.Planificador.ClasesEntidades.Usuario;
import com.planner.Planificador.Dtos.Entidades.UsuarioDto;
import com.planner.Planificador.Dtos.Solicitudes.CrearWorkSpaceSolicitud;
import com.planner.Planificador.Repositorys.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepo;

	@Autowired
	private WorkSpaceService workSpaceService;

	public List<UsuarioDto> getTodosUsuarios() {
		return usuarioRepo.findAll().stream().map(usuario -> new UsuarioDto(usuario.getId_usuario(),
				usuario.getNombre_usuario(), usuario.getEmail(), usuario.getFoto_usuario(), usuario.getRol()))
				.collect(Collectors.toList());

	}

	public UsuarioDto getUsuarioPorId(Integer id) {

		Usuario usuario = usuarioRepo.findById(id).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
		return new UsuarioDto(usuario.getId_usuario(), usuario.getNombre_usuario(), usuario.getEmail(),
				usuario.getFoto_usuario(), usuario.getRol());
	}

	public UsuarioDto iniciarSesion(String email, String contra) {

		Usuario usuario = usuarioRepo.findByEmail(email).orElseThrow(() -> new RuntimeException("Error en los datos"));

		if (!usuario.getContra().equals(contra)) {
			throw new RuntimeException("Error en los datos");
		}

		return new UsuarioDto(usuario.getId_usuario(), usuario.getNombre_usuario(), usuario.getEmail(),
				usuario.getFoto_usuario(), usuario.getRol());
	}

	@Transactional
	public UsuarioDto registrarUsuario(Usuario nuevoUsuario) {
		nuevoUsuario.setFecha_creacion(LocalDateTime.now());

		Usuario usuarioGuardado = usuarioRepo.save(nuevoUsuario);

		String tituloWorkspace = "Mi Tablero Principal";
		String descripcionWorkspace = "Workspace creado automáticamente" + usuarioGuardado.getNombre_usuario();
		CrearWorkSpaceSolicitud solicitudWorkspace = new CrearWorkSpaceSolicitud(tituloWorkspace, descripcionWorkspace);

		workSpaceService.addWorkSpace(solicitudWorkspace, usuarioGuardado.getId_usuario());

		return new UsuarioDto(usuarioGuardado.getId_usuario(), usuarioGuardado.getNombre_usuario(),
				usuarioGuardado.getEmail(), usuarioGuardado.getFoto_usuario(), usuarioGuardado.getRol());
	}

}
