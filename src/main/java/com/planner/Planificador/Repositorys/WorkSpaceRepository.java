package com.planner.Planificador.Repositorys;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.planner.Planificador.ClasesEntidades.WorkSpace;

public interface WorkSpaceRepository extends JpaRepository<WorkSpace, Integer> {
	List<WorkSpace> findByUsuarioAsignado_IdUsuario(Integer idUsuario);
}
