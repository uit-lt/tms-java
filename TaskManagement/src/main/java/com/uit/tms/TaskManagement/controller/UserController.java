package com.uit.tms.TaskManagement.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

import com.uit.tms.TaskManagement.api.UserApi;
import com.uit.tms.TaskManagement.model.AssignRoleToUserRequest;
import com.uit.tms.TaskManagement.model.UserResponseDTO;
import com.uit.tms.TaskManagement.model.UserUpdateRequestDTO;
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
	public ResponseEntity<UserResponseDTO> assignRoleToUser(Long userId,
			@Valid AssignRoleToUserRequest assignRoleToUserRequest) throws Exception {
		return ResponseEntity.ok(service.assignRoleToUser(userId, assignRoleToUserRequest));
	}

	@Override
	public ResponseEntity<Void> deleteUser(Long userId) throws Exception {
		service.deleteUser(userId);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}


}
