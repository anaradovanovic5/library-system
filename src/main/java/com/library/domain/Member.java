package com.library.domain;

public class Member extends User {
	private String name;
	private String email;

	public Member() {
	}

	public Member(int userID, String username, String password, String name, String email) {
		super(userID, username, password);
		this.name = name;
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Member{id=" + getUserID() + ", name='" + name + "', email='" + email + "'}";
	}
}
