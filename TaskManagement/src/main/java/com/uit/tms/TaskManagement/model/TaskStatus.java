package com.uit.tms.TaskManagement.model;

public enum TaskStatus {
    OPEN("Open", "primary"),
    IN_PROGRESS("In Progress", "info"),
    COMPLETED("Completed", "success"),
    BLOCKED("Blocked", "danger"),
    TO_DO("To Do", "warning"),
    DONE("Done", "success"),
    CANCELLED("Cancelled", "secondary");

    private final String displayName;
    private final String color;

    TaskStatus(String displayName, String color) {
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