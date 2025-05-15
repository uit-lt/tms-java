package com.uit.tms.TaskManagement.service;

import com.uit.tms.TaskManagement.entity.ProjectEntity;
import com.uit.tms.TaskManagement.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;

    public List<ProjectEntity> findAll() {
        return projectRepository.findAll();
    }

    public Optional<ProjectEntity> findById(Long id) {
        return projectRepository.findById(id);
    }

    public ProjectEntity save(ProjectEntity project) {
        return projectRepository.save(project);
    }

    public void delete(Long id) {
        projectRepository.deleteById(id);
    }
}
