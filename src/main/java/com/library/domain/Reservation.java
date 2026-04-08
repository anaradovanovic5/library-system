package com.library.domain;

public class Reservation {
	private int reservationID;
	private int userID;
	private int bookID;
	private String status;

	public Reservation() {
	}

	public Reservation(int reservationID, int userID, int bookID) {
		this.reservationID = reservationID;
		this.userID = userID;
		this.bookID = bookID;
		this.status = "active";
	}

	public int getReservationID() {
		return reservationID;
	}

	public int getUserID() {
		return userID;
	}

	public int getBookID() {
		return bookID;
	}

	public String getStatus() {
		return status;
	}

	public void setReservationID(int reservationID) {
		this.reservationID = reservationID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public void setBookID(int bookID) {
		this.bookID = bookID;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Reservation{id=" + reservationID + ", bookID=" + bookID + ", status='" + status + "'}";
	}
}
