package com.uit.tms.TaskManagement.service;

import java.util.List;

import com.uit.tms.TaskManagement.model.RoleRequestDTO;
import com.uit.tms.TaskManagement.model.RoleResponseDTO;

public interface RoleService {
	RoleResponseDTO createRole(RoleRequestDTO dto) throws Exception;
	RoleResponseDTO getRoleById(Long id) throws Exception;
    List<RoleResponseDTO> getAllRoles();
    void deleteRole(Long id) throws Exception;
}
