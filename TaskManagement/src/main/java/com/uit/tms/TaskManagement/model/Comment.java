package com.uit.tms.TaskManagement.model;

import java.time.LocalDateTime;

public class Comment {
  private String content;
  private LocalDateTime createdAt;
  private String author;

  // Constructors, getters, setters
  public Comment(String content, LocalDateTime createdAt, String author) {
    this.content = content;
    this.createdAt = createdAt;
    this.author = author;
  }

  // Add getters and setters
}