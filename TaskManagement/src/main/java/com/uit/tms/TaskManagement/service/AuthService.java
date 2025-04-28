package com.uit.tms.TaskManagement.service;

import com.uit.tms.TaskManagement.model.AuthResponseDTO;
import com.uit.tms.TaskManagement.model.UserLoginRequestDTO;
import com.uit.tms.TaskManagement.model.UserRegisterRequestDTO;

import jakarta.validation.Valid;

public interface AuthService {
	
	AuthResponseDTO authenticate(@Valid UserLoginRequestDTO requestDTO) throws Exception;
	boolean register(@Valid UserRegisterRequestDTO requestDTO) throws Exception;
	
	

}
