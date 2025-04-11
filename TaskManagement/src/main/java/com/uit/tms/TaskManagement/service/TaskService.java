package com.uit.tms.TaskManagement.service;

import java.util.List;

import com.uit.tms.TaskManagement.model.TaskRequestDTO;
import com.uit.tms.TaskManagement.model.TaskResponseDTO;

public interface TaskService {
    TaskResponseDTO createTask(TaskRequestDTO taskDTO) throws Exception;
    TaskResponseDTO getTaskById(Long id) throws Exception;
    List<TaskResponseDTO> getAllTasks();
    void deleteTask(Long id) throws Exception;
    TaskResponseDTO updateTask(Long id, TaskRequestDTO taskDTO) throws Exception;
}
