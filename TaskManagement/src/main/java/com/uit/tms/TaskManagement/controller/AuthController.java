package com.uit.tms.TaskManagement.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.uit.tms.TaskManagement.api.AuthApi;
import com.uit.tms.TaskManagement.model.AuthResponseDTO;
import com.uit.tms.TaskManagement.model.UserLoginRequestDTO;
import com.uit.tms.TaskManagement.model.UserRegisterRequestDTO;
import com.uit.tms.TaskManagement.service.AuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AuthController implements AuthApi {

    private final AuthService authService;

	@Override
	public ResponseEntity<AuthResponseDTO> login(@Valid UserLoginRequestDTO authRequestDTO) throws Exception {
		return ResponseEntity.ok(authService.authenticate(authRequestDTO));
	}

	@Override
	public ResponseEntity<Void> register(@Valid UserRegisterRequestDTO authRequestDTO) throws Exception {
		boolean registerSuccess = authService.register(authRequestDTO);
		return registerSuccess ? 
				ResponseEntity.status(HttpStatus.CREATED).build() : 
				ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}

}