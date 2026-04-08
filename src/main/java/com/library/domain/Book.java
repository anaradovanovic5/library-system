package com.library.domain;

public class Book {
	private int bookID;
	private String title;
	private String isbn;
	private int publishedYear;
	private String status;
	private int authorID;
	private int categoryID;

	public Book() {
	}

	public Book(int bookID, String title, String isbn, int publishedYear, int authorID, int categoryID) {
		this.bookID = bookID;
		this.title = title;
		this.isbn = isbn;
		this.publishedYear = publishedYear;
		this.status = "available";
		this.authorID = authorID;
		this.categoryID = categoryID;
	}

	public int getBookID() {
		return bookID;
	}

	public String getTitle() {
		return title;
	}

	public String getIsbn() {
		return isbn;
	}

	public int getPublishedYear() {
		return publishedYear;
	}

	public String getStatus() {
		return status;
	}

	public int getAuthorID() {
		return authorID;
	}

	public int getCategoryID() {
		return categoryID;
	}

	public void setBookID(int bookID) {
		this.bookID = bookID;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public void setPublishedYear(int publishedYear) {
		this.publishedYear = publishedYear;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setAuthorID(int authorID) {
		this.authorID = authorID;
	}

	public void setCategoryID(int categoryID) {
		this.categoryID = categoryID;
	}

	public boolean isAvailable() {
		return "available".equals(status);
	}

	@Override
	public String toString() {
		return "Book{id=" + bookID + ", title='" + title + "', status='" + status + "'}";
	}
}
