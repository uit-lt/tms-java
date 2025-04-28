package com.uit.tms.TaskManagement.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.uit.tms.TaskManagement.entity.RoleEntity;
import com.uit.tms.TaskManagement.mapper.RoleMapper;
import com.uit.tms.TaskManagement.model.RoleRequestDTO;
import com.uit.tms.TaskManagement.model.RoleResponseDTO;
import com.uit.tms.TaskManagement.repository.RoleRepository;
import com.uit.tms.TaskManagement.service.RoleService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Validated
public class RoleServiceImpl implements RoleService {

	private final RoleRepository repository;
	private final RoleMapper mapper;

	@Override
	public RoleResponseDTO createRole(RoleRequestDTO dto) throws Exception {
		RoleEntity entity = mapper.toEntity(dto);
		RoleEntity savedRole = repository.save(entity);
		return mapper.toDto(savedRole);
	}

	@Override
	public RoleResponseDTO getRoleById(Long id) throws Exception {
		return repository.findById(id).map(role -> mapper.toDto(role))
				.orElseThrow(() -> new EntityNotFoundException("Role not found with id: " + id));
	}

	@Override
	public List<RoleResponseDTO> getAllRoles() {
		return repository.findAll().stream()
                .map(mapper::toDto)
                .toList();
	}

	@Override
	public void deleteRole(Long id) throws Exception {
		repository.findById(id)
        .ifPresentOrElse(task -> repository.deleteById(id), 
                () -> {
                    throw new EntityNotFoundException("Role not found with id: " + id);
                });
	}

}
