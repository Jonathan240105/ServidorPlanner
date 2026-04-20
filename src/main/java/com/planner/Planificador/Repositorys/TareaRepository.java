package com.planner.Planificador.Repositorys;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.planner.Planificador.ClasesEntidades.Tarea;

public interface TareaRepository extends JpaRepository<Tarea, Integer> {
	List<Tarea> findByLista_idLista(Integer idLista);

	int countByLista_idLista(Integer idLista);

	List<Tarea> findByLista_idListaAndTituloStartingWith(Integer idLista, String titulo);
}
