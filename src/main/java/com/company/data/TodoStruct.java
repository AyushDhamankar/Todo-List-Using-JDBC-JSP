package com.company.data;

public class TodoStruct {
    // Private variables
    private int taskId;
    private int userId;
    private String task;
    private String status; // Values like 'pending', 'completed'
    private String createdDate; // Using String for simplicity
    private String dueDate;
    private String completionDate;

    // Constructor
    public TodoStruct(int taskId, int userId, String task, String status, String createdDate, String dueDate, String completionDate) {
        this.taskId = taskId;
        this.userId = userId;
        this.task = task;
        this.status = status;
        this.createdDate = createdDate;
        this.dueDate = dueDate;
        this.completionDate = completionDate;
    }

    // Getter and Setter for taskId
    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    // Getter and Setter for userId
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    // Getter and Setter for task
    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    // Getter and Setter for status
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Getter and Setter for createdDate
    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    // Getter and Setter for dueDate
    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    // Getter and Setter for completionDate
    public String getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(String completionDate) {
        this.completionDate = completionDate;
    }
}