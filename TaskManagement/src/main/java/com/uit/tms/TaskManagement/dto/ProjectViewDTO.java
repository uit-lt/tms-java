package com.uit.tms.TaskManagement.dto;

import com.uit.tms.TaskManagement.entity.ProjectEntity;

public record ProjectViewDTO(
        ProjectEntity project,
        int totalTasks,
        int doneTasks,
        int progressPercent,
        String userEmail
) {}
