package com.uit.tms.TaskManagement.model;

public class Project {
  private Long id;
  private String name;

  // Constructors, getters, setters
  public Project(Long id, String name) {
    this.id = id;
    this.name = name;
  }

  @Override
  public String toString() {
    return "Project{" +
        "id=" + id +
        ", name='" + name + '\'' +
        '}';
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

}