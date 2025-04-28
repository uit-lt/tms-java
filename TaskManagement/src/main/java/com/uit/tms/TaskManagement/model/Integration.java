package com.uit.tms.TaskManagement.model;

import java.time.LocalDateTime;

public class Integration {
  private Long id;
  private Platform platform;
  private LocalDateTime connectedOn;
  private boolean syncTasks;
  private boolean syncComments;

  public Integration() {
  }

  public Integration(Long id, Platform platform, LocalDateTime connectedOn, boolean syncTasks, boolean syncComments) {
    this.id = id;
    this.platform = platform;
    this.connectedOn = connectedOn;
    this.syncTasks = syncTasks;
    this.syncComments = syncComments;
  }

  // Getters and Setters
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Platform getPlatform() {
    return platform;
  }

  public void setPlatform(Platform platform) {
    this.platform = platform;
  }

  public LocalDateTime getConnectedOn() {
    return connectedOn;
  }

  public void setConnectedOn(LocalDateTime connectedOn) {
    this.connectedOn = connectedOn;
  }

  public boolean isSyncTasks() {
    return syncTasks;
  }

  public void setSyncTasks(boolean syncTasks) {
    this.syncTasks = syncTasks;
  }

  public boolean isSyncComments() {
    return syncComments;
  }

  public void setSyncComments(boolean syncComments) {
    this.syncComments = syncComments;
  }
}