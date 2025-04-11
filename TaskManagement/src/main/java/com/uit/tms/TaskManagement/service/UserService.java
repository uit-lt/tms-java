package com.uit.tms.TaskManagement.service;

import java.util.List;

import com.uit.tms.TaskManagement.model.AssignRoleToUserRequest;
import com.uit.tms.TaskManagement.model.UserRegisterRequestDTO;
import com.uit.tms.TaskManagement.model.UserResponseDTO;

public interface UserService {

	UserResponseDTO getUserById(Long id) throws Exception;
    List<UserResponseDTO> getAllUsers();
    void deleteUser(Long id) throws Exception;
    UserResponseDTO updateUser(Long id, UserRegisterRequestDTO taskDTO) throws Exception;
    UserResponseDTO assignRoleToUser(Long userId, AssignRoleToUserRequest role) throws Exception;
}
