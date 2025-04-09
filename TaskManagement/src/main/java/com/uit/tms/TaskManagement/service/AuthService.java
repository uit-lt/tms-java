package com.uit.tms.TaskManagement.service;

import com.uit.tms.TaskManagement.model.AuthRequestDTO;
import com.uit.tms.TaskManagement.model.AuthResponseDTO;

public interface AuthService {
	
	AuthResponseDTO authenticate(AuthRequestDTO requestDTO) throws Exception;
	
	boolean register(AuthRequestDTO requestDTO) throws Exception;

}
