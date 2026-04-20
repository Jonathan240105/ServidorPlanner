package com.planner.Planificador.Repositorys;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.planner.Planificador.ClasesEntidades.Comentario;

public interface ComentarioRepository extends JpaRepository<Comentario, Integer> {

	List<Comentario> findByTarea_idTarea(Integer idTarea); 
}
