package com.uit.tms.TaskManagement.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Task {
  private Long id;
  private String title;
  private String description;
  private TaskStatus status;
  private Priority priority;
  private LocalDateTime dueDate;
  private Project project;
  private Platform platform;
  private boolean executionInProgress = false;
  private LocalDateTime lastExecuted;
  private String executionResult;
  private String createdBy;
  private LocalDateTime createdAt;
  private List<Tag> tags = new ArrayList<>();

  public List<Tag> getTags() {
    return tags;
  }

  public void setTags(List<Tag> tags) {
    this.tags = tags;
  }

  public Task() {
  }

  public Task(Long id, String title, String description, TaskStatus status, Priority priority) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.status = status;
    this.priority = priority;
  }

  public Task(Long id, String title, String description, TaskStatus status, Priority priority, LocalDateTime dueDate) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.status = status;
    this.priority = priority;
    this.dueDate = dueDate;
  }

  @Override
  public String toString() {
    return "Task{" +
        "id=" + id +
        ", title='" + title + '\'' +
        ", project=" + project +
        ", platform=" + platform +
        ", status=" + status +
        ", priority=" + priority +

        ", executionResult='" + executionResult + '\'' +
        ", createdBy='" + createdBy + '\'' +

        '}';

  }

  // Getters and Setters
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public TaskStatus getStatus() {
    return status;
  }

  public void setStatus(TaskStatus status) {
    this.status = status;
  }

  public Priority getPriority() {
    return priority;
  }

  public void setPriority(Priority priority) {
    this.priority = priority;
  }

  public LocalDateTime getDueDate() {
    return dueDate;
  }

  public void setDueDate(LocalDateTime dueDate) {
    this.dueDate = dueDate;
  }

  public Project getProject() {
    return project;
  }

  public void setProject(Project project) {
    this.project = project;
  }

  public Platform getPlatform() {
    return platform;
  }

  public void setPlatform(Platform platform) {
    this.platform = platform;
  }

  public boolean isExecutionInProgress() {
    return executionInProgress;
  }

  public void setExecutionInProgress(boolean executionInProgress) {
    this.executionInProgress = executionInProgress;
  }

  public LocalDateTime getLastExecuted() {
    return lastExecuted;
  }

  public void setLastExecuted(LocalDateTime lastExecuted) {
    this.lastExecuted = lastExecuted;
  }

  public String getExecutionResult() {
    return executionResult;
  }

  public void setExecutionResult(String executionResult) {
    this.executionResult = executionResult;
  }

  public String getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }
}