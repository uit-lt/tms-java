package com.uit.tms.TaskManagement.model;

public class Platform {
  private Long id;
  private String name;
  private String color;

  public Platform() {
  }

  public Platform(Long id, String name, String color) {
    this.id = id;
    this.name = name;
    this.color = color;
  }

  @Override
  public String toString() {
    return "Platform{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", color='" + color + '\'' +
        '}';
  }

  // Add these getters
  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getColor() {
    return color;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setColor(String color) {
    this.color = color;
  }
}