package com.planner.Planificador.Repositorys;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.planner.Planificador.ClasesEntidades.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

	Optional<Usuario> findByEmail(String email);

	List<Usuario> findByEquipo_IdEquipo(Integer idEquipo);

}
