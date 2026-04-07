package com.library.domain;

public class Library {
	private int libraryID;
	private String name;
	private String address;

	public Library() {
	}

	public Library(int libraryID, String name, String address) {
		this.libraryID = libraryID;
		this.name = name;
		this.address = address;
	}

	public int getLibraryID() {
		return libraryID;
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	public void setLibraryID(int libraryID) {
		this.libraryID = libraryID;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Library{id=" + libraryID + ", name='" + name + "', address='" + address + "'}";
	}
}
