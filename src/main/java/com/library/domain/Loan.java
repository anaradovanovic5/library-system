package com.library.domain;

public class Loan {
	private int loanID;
	private int userID;
	private int bookID;
	private String loanDate;
	private String returnDate;

	public Loan() {
	}

	public Loan(int loanID, int userID, int bookID, String loanDate) {
		this.loanID = loanID;
		this.userID = userID;
		this.bookID = bookID;
		this.loanDate = loanDate;
		this.returnDate = null;
	}

	public int getLoanID() {
		return loanID;
	}

	public int getUserID() {
		return userID;
	}

	public int getBookID() {
		return bookID;
	}

	public String getLoanDate() {
		return loanDate;
	}

	public String getReturnDate() {
		return returnDate;
	}

	public void setLoanID(int loanID) {
		this.loanID = loanID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public void setBookID(int bookID) {
		this.bookID = bookID;
	}

	public void setLoanDate(String loanDate) {
		this.loanDate = loanDate;
	}

	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}

	public boolean isActive() {
		return returnDate == null;
	}

	@Override
	public String toString() {
		return "Loan{id=" + loanID + ", bookID=" + bookID + ", loanDate=" + loanDate + ", returned=" + !isActive()
				+ "}";
	}
}
