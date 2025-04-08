package com.uit.tms.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.uit.tms.api.TaskApi;
import com.uit.tms.model.TaskRequestDTO;
import com.uit.tms.model.TaskResponseDTO;
import com.uit.tms.service.TaskService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class TaskController implements TaskApi {

    private final TaskService taskService;

    @Override
    public ResponseEntity<TaskResponseDTO> createTask(@Valid TaskRequestDTO taskDTO) throws Exception {
        TaskResponseDTO task = taskService.createTask(taskDTO);
        return ResponseEntity.ok(task);
    }

    @Override
    public ResponseEntity<List<TaskResponseDTO>> getTasks()  throws Exception {
        return ResponseEntity.ok(taskService.getAllTasks());
    }

    @Override
    public ResponseEntity<Void> deleteTask(Integer taskId) throws Exception {
        throw new UnsupportedOperationException("Unimplemented method 'deleteTask'");
    }

    @Override
    public ResponseEntity<TaskResponseDTO> updateTask(Integer taskId, @Valid TaskRequestDTO taskRequestDTO)
            throws Exception {
        throw new UnsupportedOperationException("Unimplemented method 'updateTask'");
    }

}
