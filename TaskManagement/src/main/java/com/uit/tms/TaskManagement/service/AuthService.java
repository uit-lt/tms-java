package com.uit.tms.TaskManagement.service;

import com.uit.tms.TaskManagement.model.AuthLoginRequestDTO;
import com.uit.tms.TaskManagement.model.AuthRequestDTO;
import com.uit.tms.TaskManagement.model.AuthResponseDTO;

import jakarta.validation.Valid;

public interface AuthService {
	
	AuthResponseDTO authenticate(@Valid AuthLoginRequestDTO requestDTO) throws Exception;
	
	boolean register(@Valid AuthRequestDTO requestDTO) throws Exception;

}
