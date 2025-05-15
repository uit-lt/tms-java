package com.uit.tms.TaskManagement.controller;

import com.uit.tms.TaskManagement.dto.ProjectViewDTO;
import com.uit.tms.TaskManagement.entity.ProjectEntity;
import com.uit.tms.TaskManagement.entity.TaskEntity;
import com.uit.tms.TaskManagement.enums.TaskStatus;
import com.uit.tms.TaskManagement.service.ProjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Stream;

@Controller
public class ProjectController {
    private static final Logger logger = LoggerFactory.getLogger(ProjectController.class);

    private final ProjectService projectService;


    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/projects")
    public String projectList(
            @RequestParam(required = false) String search,
            @RequestParam(required = false) String status,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            Model model) {

        List<ProjectEntity> allProjects = projectService.findAll();

        List<ProjectViewDTO> allProjectDTOs = allProjects.stream().map(project -> {
            List<TaskEntity> tasks = project.getTasks();
            int totalTasks = tasks != null ? tasks.size() : 0;
            int doneTasks = tasks != null ? (int) tasks.stream()
                    .filter(task -> task.getStatus() == TaskStatus.DONE || task.getStatus() == TaskStatus.COMPLETED)
                    .count() : 0;
            int percent = totalTasks > 0 ? (int) ((double) doneTasks / totalTasks * 100) : 0;
            String email = project.getUser() != null ? project.getUser().getEmail() : null;
            return new ProjectViewDTO(project, totalTasks, doneTasks, percent, email);
        }).toList();
        
        // Search & filter
        Stream<ProjectViewDTO> stream = allProjectDTOs.stream();
        if (search != null && !search.isBlank()) {
            stream = stream.filter(dto -> dto.project().getName().toLowerCase().contains(search.toLowerCase()));
        }
        if (status != null && !status.isBlank()) {
            stream = stream.filter(dto -> dto.project().getStatus() != null &&
                    dto.project().getStatus().equalsIgnoreCase(status));
        }

        List<ProjectViewDTO> filtered = stream.toList();

        // Pagination
        int total = filtered.size();
        int totalPages = (int) Math.ceil((double) total / size);
        int from = Math.min((page - 1) * size, total);
        int to = Math.min(from + size, total);
        List<ProjectViewDTO> pageContent = filtered.subList(from, to);

        model.addAttribute("projects", pageContent);
        model.addAttribute("search", search);
        model.addAttribute("status", status);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("pageSize", size);
        model.addAttribute("totalItems", total);
        return "projects/list";
    }

    @GetMapping("/projects/new")
    public String newProjectForm() {
        return "projects/form";
    }

    @GetMapping("/projects/{id}")
    public String projectDetail(@PathVariable Long id) {
        return "projects/detail";
    }

    @GetMapping("/projects/{id}/edit")
    public String editProjectForm(@PathVariable Long id) {
        return "projects/form";
    }
}
