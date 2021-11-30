package com.taller.proyecto.security;

import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {

	private static final long serialVersionUID = -7858869558953243875L;

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException {

		final Map<String, Object> mapException = new HashMap<>();

		mapException.put("error", "401");
		mapException.put("message", "No estas autorizado para acceder a este recurso");
		mapException.put("exception", "No autorizado");
		mapException.put("path", request.getServletPath());
		//mapException.put("timestamp", LocalDateTime.now());

		response.setContentType("application/json");
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

		final ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(response.getOutputStream(), mapException);
		
		//response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
	}
}