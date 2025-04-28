package com.uit.tms.TaskManagement.model;

public enum Priority {
  LOW("Low", "secondary"),
  MEDIUM("Medium", "warning"),
  HIGH("High", "danger");

  private final String displayName;
  private final String color;

  Priority(String displayName, String color) {
    this.displayName = displayName;
    this.color = color;
  }

  public String getDisplayName() {
    return displayName;
  }

  public String getColor() {
    return color;
  }
}