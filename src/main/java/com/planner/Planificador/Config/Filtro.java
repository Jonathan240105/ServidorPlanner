package com.planner.Planificador.Config;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.planner.Planificador.Dtos.UsuarioToken;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class Filtro extends OncePerRequestFilter {

	@Autowired
	private TokenUtil tokenUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String headerAutorizacion = request.getHeader("Authorization");

		if (headerAutorizacion != null && headerAutorizacion.startsWith("Bearer ")) {
			String token = headerAutorizacion.substring(7);

			if (tokenUtil.validarToken(token) && SecurityContextHolder.getContext().getAuthentication() == null) {

				String nombreUsuario = tokenUtil.extraerNombreUsuario(token);
				String rol = tokenUtil.extraerRol(token);
				Integer idUsuario = tokenUtil.extraerId(token);

				UsuarioToken principal = new UsuarioToken(idUsuario, nombreUsuario, rol);
				UsernamePasswordAuthenticationToken credenciales = new UsernamePasswordAuthenticationToken(principal,
						null, new ArrayList<>());

				SecurityContextHolder.getContext().setAuthentication(credenciales);
			}
		}

		filterChain.doFilter(request, response);
	}
}
