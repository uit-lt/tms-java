package com.uit.tms.TaskManagement.security;
import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.uit.tms.TaskManagement.model.ErrorResponseDTO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class ApplicationAuthenticationEntrypoint implements AuthenticationEntryPoint {
	
	private final ObjectMapper objectMapper = new ObjectMapper();
	
	@Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
		response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        ErrorResponseDTO body = new ErrorResponseDTO();
        body.code(HttpStatus.UNAUTHORIZED.value());
        body.message(HttpStatus.UNAUTHORIZED.getReasonPhrase());

        objectMapper.writeValue(response.getOutputStream(), body);
        System.out.println("Unauthorized request: " + authException.getMessage());
    }
}
