package com.company.data;

public class UserStruct {
    // Private variables
    private int userId;
    private String username;
    private String salt;
    private String createdAt;

    // Constructor
    public UserStruct(int userId, String username, String salt, String createdAt) {
        this.userId = userId;
        this.username = username;
        this.salt = salt;
        this.createdAt = createdAt;
    }

    // Getter and Setter for userId
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    // Getter and Setter for username
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // Getter and Setter for salt
    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    // Getter and Setter for createdAt
    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}