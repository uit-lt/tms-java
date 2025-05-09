package com.uit.tms.TaskManagement.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.uit.tms.TaskManagement.entity.TaskEntity;
import com.uit.tms.TaskManagement.mapper.TaskMapper;
import com.uit.tms.TaskManagement.model.TaskRequestDTO;
import com.uit.tms.TaskManagement.model.TaskResponseDTO;
import com.uit.tms.TaskManagement.repository.TaskRepository;
import com.uit.tms.TaskManagement.service.TaskService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Validated
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;


    @Override
    public TaskResponseDTO createTask(TaskRequestDTO taskDTO) throws Exception {
        TaskEntity task = taskMapper.toEntity(taskDTO);
        TaskEntity savedTask = taskRepository.save(task);
        return taskMapper.toDto(savedTask);
    }


    @Override
    public TaskResponseDTO getTaskById(Long id) throws Exception {
        return taskRepository.findById(id)
                .map(taskMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("Task not found with id: " + id));
    }


    @Override
    public List<TaskResponseDTO> getAllTasks() {
        return taskRepository.findAll().stream()
                .map(taskMapper::toDto)
                .toList();
    }


    @Override
    public void deleteTask(Long id) {
        taskRepository.findById(id)
                .ifPresentOrElse(task -> taskRepository.deleteById(id), 
                        () -> {
                            throw new EntityNotFoundException("Task not found with id: " + id);
                        });
    }


    @Override
    public TaskResponseDTO updateTask(Long id, TaskRequestDTO taskDTO) throws Exception {
        TaskEntity task = taskRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Task not found with id: " + id));
        taskMapper.updateEntityFromDto(taskDTO, task);
        TaskEntity updatedTask = taskRepository.save(task);
        return taskMapper.toDto(updatedTask);
    }

}
