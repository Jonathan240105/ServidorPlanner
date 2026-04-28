package com.planner.Planificador.Config;

import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class TokenUtil {

	private final Key clave = Keys.secretKeyFor(SignatureAlgorithm.HS256);

	private final long tiempoExpiracion = 36000000;

	public String generateToken(TokenPayload payload) {
		return Jwts.builder()
				.setSubject(payload.getNombreUsuario())
				.claim("rol", payload.getRol())
				.claim("idUsuario", payload.getIdUsuario())
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + tiempoExpiracion))
				.signWith(clave).compact();
	}

	private Claims leerToken(String token) {
		return Jwts.parserBuilder().setSigningKey(clave).build().parseClaimsJws(token).getBody();
	}

	public String extractUsername(String token) {
		return leerToken(token).getSubject();
	}

	public Boolean validarToken(String token) {
		try {
			leerToken(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
