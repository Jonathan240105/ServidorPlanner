package com.planner.Planificador.Repositorys;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.planner.Planificador.ClasesEntidades.Subtarea;

public interface SubtareaRepository extends JpaRepository<Subtarea, Integer> {
	List<Subtarea> findByTarea_IdTarea(Integer idTarea);
}
