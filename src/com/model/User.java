package com.model;

public class User {
    private int userId;
    private String username;
    private String password;
    private String role; // "Admin" or "User"

    // Constructors
    public User() {
    }

    public User(int userId, String username, String password, String role) {
        this.setUserId(userId);
        this.setUsername(username);
        this.setPassword(password);
        this.setRole(role);
    }
    
    // Getters and Setters


	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}


}
