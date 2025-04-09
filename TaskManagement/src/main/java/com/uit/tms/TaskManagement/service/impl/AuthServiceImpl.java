package com.uit.tms.TaskManagement.service.impl;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.uit.tms.TaskManagement.entity.UserEntity;
import com.uit.tms.TaskManagement.mapper.UserMapper;
import com.uit.tms.TaskManagement.model.AuthLoginRequestDTO;
import com.uit.tms.TaskManagement.model.AuthRequestDTO;
import com.uit.tms.TaskManagement.model.AuthResponseDTO;
import com.uit.tms.TaskManagement.repository.UserRepository;
import com.uit.tms.TaskManagement.service.AuthService;
import com.uit.tms.TaskManagement.util.JwtUtil;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Validated
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authManager;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtUtil jwtUtil;
    
    private final UserMapper mapper;

    @Override
    public AuthResponseDTO authenticate(@Valid AuthLoginRequestDTO request) throws Exception {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        String token = jwtUtil.generateToken(request.getUsername());
        return new AuthResponseDTO().token(token);
    }

    @Override
    public boolean register(@Valid AuthRequestDTO request) throws Exception {
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new RuntimeException("User already exists");
        }
        UserEntity user = new UserEntity();
        mapper.updateEntityFromDto(request, user);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);
        return true;
    }

}