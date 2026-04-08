package com.library.domain;

import com.fasterxml.jackson.annotation.*;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({ @JsonSubTypes.Type(value = Member.class, name = "member"),
		@JsonSubTypes.Type(value = Librarian.class, name = "librarian") })

public class User {
	private int userID;
	private String username;
	private String password;

	public User() {
	}

	public User(int userID, String username, String password) {
		this.userID = userID;
		this.username = username;
		this.password = password;
	}

	public int getUserID() {
		return userID;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User{id=" + userID + ", username='" + username + "'}";
	}
}
