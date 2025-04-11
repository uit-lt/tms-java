package com.uit.tms.TaskManagement.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

import com.uit.tms.TaskManagement.api.TaskApi;
import com.uit.tms.TaskManagement.model.TaskRequestDTO;
import com.uit.tms.TaskManagement.model.TaskResponseDTO;
import com.uit.tms.TaskManagement.service.TaskService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
public class TaskController implements TaskApi {

    private final TaskService taskService;

    @Override
    public ResponseEntity<TaskResponseDTO> createTask(@Valid TaskRequestDTO taskDTO) throws Exception {
        TaskResponseDTO task = taskService.createTask(taskDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(task);
    }

    @Override
    public ResponseEntity<List<TaskResponseDTO>> getTasks()  throws Exception {
        return ResponseEntity.ok(taskService.getAllTasks());
    }

    @Override
    public ResponseEntity<Void> deleteTask(Long taskId) throws Exception {
    	taskService.deleteTask(taskId);
    	return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Override
    public ResponseEntity<TaskResponseDTO> updateTask(Long taskId, @Valid TaskRequestDTO taskRequestDTO)
            throws Exception {
    	return ResponseEntity.ok(taskService.updateTask(taskId, taskRequestDTO));
    }

	@Override
	public ResponseEntity<TaskResponseDTO> getTaskById(Long taskId) throws Exception {
		return ResponseEntity.ok(taskService.getTaskById(taskId));
	}

}
