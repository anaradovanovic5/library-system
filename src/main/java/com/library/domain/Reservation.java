package com.library.domain;

/**
 * Predstavlja rezervaciju knjige u sistemu biblioteke.
 *
 * <p>
 * Rezervacija omogucava korisniku da rezervise knjigu unapred. Status
 * rezervacije moze biti "active" (aktivna) ili "cancelled" (otkazana).
 * </p>
 *
 * @author Ana Radovanovic
 * @version 1.0.0
 * @see Book
 * @see User
 */
public class Reservation {

	/**
	 * Jedinstveni identifikator rezervacije.
	 */
	private int reservationID;

	/**
	 * Identifikator korisnika koji je napravio rezervaciju.
	 */
	private int userID;

	/**
	 * Identifikator rezervisane knjige.
	 */
	private int bookID;

	/**
	 * Trenutni status rezervacije. Moguce vrednosti: "active" (aktivna) ili
	 * "cancelled" (otkazana).
	 */
	private String status;

	/**
	 * Podrazumevani konstruktor bez argumenata. Neophodan za deserijalizaciju putem
	 * Jackson biblioteke.
	 */
	public Reservation() {
	}

	/**
	 * Kreira novu rezervaciju sa zadatim podacima. Status se automatski postavlja
	 * na "active".
	 *
	 * @param reservationID jedinstveni identifikator rezervacije
	 * @param userID        identifikator korisnika koji rezervise
	 * @param bookID        identifikator knjige koja se rezervise
	 */
	public Reservation(int reservationID, int userID, int bookID) {
		this.reservationID = reservationID;
		this.userID = userID;
		this.bookID = bookID;
		this.status = "active";
	}

	/**
	 * Vraca identifikator rezervacije.
	 *
	 * @return identifikator rezervacije
	 */
	public int getReservationID() {
		return reservationID;
	}

	/**
	 * Vraca identifikator korisnika.
	 *
	 * @return identifikator korisnika
	 */
	public int getUserID() {
		return userID;
	}

	/**
	 * Vraca identifikator knjige.
	 *
	 * @return identifikator knjige
	 */
	public int getBookID() {
		return bookID;
	}

	/**
	 * Vraca trenutni status rezervacije.
	 *
	 * @return status rezervacije ("active" ili "cancelled")
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Postavlja identifikator rezervacije.
	 *
	 * @param reservationID novi identifikator
	 */
	public void setReservationID(int reservationID) {
		this.reservationID = reservationID;
	}

	/**
	 * Postavlja identifikator korisnika.
	 *
	 * @param userID novi identifikator korisnika
	 */
	public void setUserID(int userID) {
		this.userID = userID;
	}

	/**
	 * Postavlja identifikator knjige.
	 *
	 * @param bookID novi identifikator knjige
	 */
	public void setBookID(int bookID) {
		this.bookID = bookID;
	}

	/**
	 * Postavlja status rezervacije.
	 *
	 * @param status novi status ("active" ili "cancelled")
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * Vraca tekstualnu reprezentaciju rezervacije.
	 *
	 * @return string sa identifikatorom, knjigom i statusom
	 */
	@Override
	public String toString() {
		return "Reservation{id=" + reservationID + ", bookID=" + bookID + ", status='" + status + "'}";
	}
}
