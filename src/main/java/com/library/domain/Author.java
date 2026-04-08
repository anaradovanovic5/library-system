package com.library.domain;

public class Author {
	private int authorID;
	private String name;

	public Author() {
	}

	public Author(int authorID, String name) {
		this.authorID = authorID;
		this.name = name;
	}

	public int getAuthorID() {
		return authorID;
	}

	public String getName() {
		return name;
	}

	public void setAuthorID(int authorID) {
		this.authorID = authorID;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Author{id=" + authorID + ", name='" + name + "'}";
	}
}
