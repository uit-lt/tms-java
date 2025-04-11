package com.uit.tms.TaskManagement.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.uit.tms.TaskManagement.entity.RoleEntity;
import com.uit.tms.TaskManagement.entity.UserEntity;
import com.uit.tms.TaskManagement.mapper.UserMapper;
import com.uit.tms.TaskManagement.model.AssignRoleToUserRequest;
import com.uit.tms.TaskManagement.model.UserResponseDTO;
import com.uit.tms.TaskManagement.model.UserUpdateRequestDTO;
import com.uit.tms.TaskManagement.repository.RoleRepository;
import com.uit.tms.TaskManagement.repository.UserRepository;
import com.uit.tms.TaskManagement.service.UserService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Validated
public class UserServiceImpl implements UserService {
	
	private final UserRepository userRepository;
	
	private final UserMapper mapper;
	
	private final RoleRepository roleRepository;
	
	private final PasswordEncoder passwordEncoder;

	@Override
	public UserResponseDTO getUserById(Long id) throws Exception {
		return userRepository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
	}

	@Override
	public List<UserResponseDTO> getAllUsers() {
		return userRepository.findAll().stream()
                .map(mapper::toDto)
                .toList();
	}

	@Override
	public void deleteUser(Long id) throws Exception {
		userRepository.findById(id)
        .ifPresentOrElse(task -> userRepository.deleteById(id), 
                () -> {
                    throw new EntityNotFoundException("User not found with id: " + id);
                });
		
	}

	@Override
	public UserResponseDTO updateUser(Long id, UserUpdateRequestDTO dto) throws Exception {
		UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
		if (!Objects.isNull(dto.getEmail())) user.setEmail(dto.getEmail());
		if (!Objects.isNull(dto.getUsername())) user.setUsername(dto.getUsername());
		if (!Objects.isNull(dto.getPassword())) user.setPassword(passwordEncoder.encode(dto.getPassword()));
		Set<RoleEntity> roles = roleRepository.findByNameIn(user.getRoles().stream().map(r -> r.getName()).collect(Collectors.toSet()));
		user.setRoles(roles);
        UserEntity updatedTask = userRepository.save(user);
        return mapper.toDto(updatedTask);
	}

	@Override
	public UserResponseDTO assignRoleToUser(Long userId, AssignRoleToUserRequest roleRequest) throws Exception {
		UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));
		RoleEntity role = roleRepository.findByName(roleRequest.getRoleName())
				.orElseThrow(() -> new EntityNotFoundException("Role not found with name: " + roleRequest.getRoleName()));
		Set<RoleEntity> roles = user.getRoles();
		roles.add(role);
		UserEntity savedUser = userRepository.save(user);
		return mapper.toDto(savedUser);
	}

}
