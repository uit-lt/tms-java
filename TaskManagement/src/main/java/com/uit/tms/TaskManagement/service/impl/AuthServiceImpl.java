package com.uit.tms.TaskManagement.service.impl;

import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.uit.tms.TaskManagement.entity.RoleEntity;
import com.uit.tms.TaskManagement.entity.UserEntity;
import com.uit.tms.TaskManagement.mapper.UserMapper;
import com.uit.tms.TaskManagement.model.AuthResponseDTO;
import com.uit.tms.TaskManagement.model.UserLoginRequestDTO;
import com.uit.tms.TaskManagement.model.UserRegisterRequestDTO;
import com.uit.tms.TaskManagement.repository.RoleRepository;
import com.uit.tms.TaskManagement.repository.UserRepository;
import com.uit.tms.TaskManagement.service.AuthService;
import com.uit.tms.TaskManagement.util.JwtUtil;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Validated
public class AuthServiceImpl implements AuthService {
	
	private static final String ROLE_USER = "USER";

	private final AuthenticationManager authManager;

	private final UserRepository userRepository;
	
	private final RoleRepository roleRepository;

	private final PasswordEncoder passwordEncoder;

	private final JwtUtil jwtUtil;

	private final UserMapper mapper;

	@Override
	public AuthResponseDTO authenticate(@Valid UserLoginRequestDTO request) throws Exception {
		AuthResponseDTO response = new AuthResponseDTO();
		Optional<UserEntity> userEntity = userRepository.findByUsername(request.getUsername());
		userEntity.ifPresentOrElse(user -> {
			authManager
					.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), request.getPassword()));
			response.email(user.getEmail());
			response.username(user.getUsername());
		}, () -> {
			throw new RuntimeException("User not found " + request.getUsername());
		});
		String token = jwtUtil.generateToken(request.getUsername());
		return response.token(token);
	}

	@Override
	public boolean register(@Valid UserRegisterRequestDTO request) throws Exception {
		if (userRepository.findByUsername(request.getUsername()).isPresent()) {
			throw new RuntimeException("User already exists");
		}
		UserEntity user = new UserEntity();
		mapper.updateEntityFromRegisterDto(request, user);
		RoleEntity userRole = roleRepository.findByName(ROLE_USER).orElseGet(() -> {
			return roleRepository.save(new RoleEntity(null, ROLE_USER, null));
		});
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		user.getRoles().add(userRole);
		userRepository.save(user);
		return true;
	}

}