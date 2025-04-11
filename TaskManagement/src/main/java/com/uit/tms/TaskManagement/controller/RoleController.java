package com.uit.tms.TaskManagement.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

import com.uit.tms.TaskManagement.api.RoleApi;
import com.uit.tms.TaskManagement.model.RoleRequestDTO;
import com.uit.tms.TaskManagement.model.RoleResponseDTO;
import com.uit.tms.TaskManagement.service.RoleService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class RoleController implements RoleApi {
	private final RoleService service;

	@Override
	public ResponseEntity<RoleResponseDTO> createRole(@Valid RoleRequestDTO roleRequestDTO) throws Exception {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.createRole(roleRequestDTO));
	}

	@Override
	public ResponseEntity<Void> deleteRole(Long roleId) throws Exception {
		service.deleteRole(roleId);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	@Override
	public ResponseEntity<RoleResponseDTO> getRoleById(Long roleId) throws Exception {
		return ResponseEntity.ok(service.getRoleById(roleId));
	}

	@Override
	public ResponseEntity<List<RoleResponseDTO>> getRoles() throws Exception {
		return ResponseEntity.ok(service.getAllRoles());
	}
	
}
