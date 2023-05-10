package com.fatfa.exceptions;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import com.fasterxml.jackson.databind.ObjectMapper;

public class AppAuthorizationException implements AuthenticationEntryPoint{

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException arg2)
			throws IOException, ServletException {

		final Map<String, Object> mapException = new HashMap<>();
		mapException.put("error", "401");
		mapException.put("mensaje", "FATFA esta protegido contra accesos no autorizados al servidor de peticiones");
		mapException.put("exception", "No autorizado");
		mapException.put("ruta", request.getServletPath());
		mapException.put("fecha", new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
		response.setContentType("application/json");
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		final ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(response.getOutputStream(), mapException);
	}
}
