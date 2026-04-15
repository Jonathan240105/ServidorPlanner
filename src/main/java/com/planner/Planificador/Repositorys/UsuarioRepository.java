package com.planner.Planificador.Repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.planner.Planificador.ClasesEntidades.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

}
