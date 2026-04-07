package com.library.domain;

public class Librarian extends User {
	private int librarianID;
	private int employeeID;

	public Librarian() {
	}

	public Librarian(int userID, String username, String password, int librarianID, int employeeID) {
		super(userID, username, password);
		this.librarianID = librarianID;
		this.employeeID = employeeID;
	}

	public int getLibrarianID() {
		return librarianID;
	}

	public int getEmployeeID() {
		return employeeID;
	}

	public void setLibrarianID(int librarianID) {
		this.librarianID = librarianID;
	}

	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}

	@Override
	public String toString() {
		return "Librarian{id=" + getUserID() + ", employeeID=" + employeeID + "}";
	}
}
