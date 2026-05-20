package com.planner.Planificador.Repositorys;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.planner.Planificador.ClasesEntidades.Lista;

public interface ListaRepository extends JpaRepository<Lista, Integer> {

	List<Lista> findByWorkSpace_idWorkspace(Integer idWorkSpace);

	int countByWorkSpace_IdWorkspace(Integer idWorkSpace);

	List<Lista> findByWorkSpace_UsuarioAsignado_IdUsuario(Integer idUsuario);

	List<Lista> findByWorkSpace_UsuarioAsignado_Equipo_IdEquipo(Integer idEquipo);

}
