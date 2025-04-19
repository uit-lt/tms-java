package com.uit.tms.TaskManagement.model;

import java.time.LocalDate;

public class Project {
  private Long id;
  private String name;
  private String description;
  private LocalDate startDate;
  private LocalDate endDate;
  private Priority priority;
  private String status;

  public Project(Long id, String name) {
    this.id = id;
    this.name = name;
  }

  // Constructors, getters, setters
  public Project(Long id, String name, String description, LocalDate startDate, LocalDate endDate, Priority priority,
      String status) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.startDate = startDate;
    this.endDate = endDate;
    this.priority = priority;
    this.status = status;
  }

  @Override
  public String toString() {
    return "Project{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", description='" + description +
        ", startDate=" + startDate +
        ", endDate=" + endDate +
        ", priority=" + priority +
        ", status='" + status + '\'' +
        '}';

  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public LocalDate getStartDate() {
    return startDate;
  }

  public void setStartDate(LocalDate startDate) {
    this.startDate = startDate;
  }

  public LocalDate getEndDate() {
    return endDate;
  }

  public void setEndDate(LocalDate endDate) {
    this.endDate = endDate;
  }

  public Priority getPriority() {
    return priority;
  }

  public void setPriority(Priority priority) {
    this.priority = priority;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }
}