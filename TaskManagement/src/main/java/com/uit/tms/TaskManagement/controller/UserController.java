package com.uit.tms.TaskManagement.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

import com.uit.tms.TaskManagement.api.UserApi;
import com.uit.tms.TaskManagement.model.AssignRoleToUserRequest;
import com.uit.tms.TaskManagement.model.UserRegisterRequestDTO;
import com.uit.tms.TaskManagement.model.UserResponseDTO;
import com.uit.tms.TaskManagement.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class UserController implements UserApi {
	
	private final UserService service;
	

	@Override
	public ResponseEntity<UserResponseDTO> getUserById(Long userId) throws Exception {
		return ResponseEntity.ok(service.getUserById(userId));
	}

	@Override
	public ResponseEntity<List<UserResponseDTO>> getUsers() throws Exception {
		return ResponseEntity.ok(service.getAllUsers());
	}

	@Override
	public ResponseEntity<UserResponseDTO> updateUser(Long userId, @Valid UserRegisterRequestDTO userRegisterRequestDTO)
			throws Exception {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.updateUser(userId, userRegisterRequestDTO));
	}

	@Override
	public ResponseEntity<UserResponseDTO> assignRoleToUser(Long userId,
			@Valid AssignRoleToUserRequest assignRoleToUserRequest) throws Exception {
		return ResponseEntity.ok(service.assignRoleToUser(userId, assignRoleToUserRequest));
	}

}
