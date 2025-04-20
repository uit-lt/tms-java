package com.uit.tms.TaskManagement.model;

public class Tag {
  private Long id;
  private String title;

  public Tag() {
  }

  public Tag(Long id, String title) {
    this.id = id;
    this.title = title;
  }

  // Getter và Setter
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

  @Override
  public String toString() {
    return "Tag{id=" + id + ", title='" + title + "'}";
  }
}
