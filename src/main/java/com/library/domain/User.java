package com.library.domain;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Bazna klasa koja predstavlja korisnika sistema biblioteke.
 *
 * <p>
 * Svaki korisnik ima jedinstveni identifikator, korisnicko ime i lozinku. Klasa
 * se nasledjuje od strane {@link Member} i {@link Librarian}.
 * </p>
 *
 * @author Ana Radovanovic
 * @version 1.0.0
 * @see Member
 * @see Librarian
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({ @JsonSubTypes.Type(value = Member.class, name = "member"),
		@JsonSubTypes.Type(value = Librarian.class, name = "librarian") })
public class User {

	/**
	 * Jedinstveni identifikator korisnika.
	 */
	private int userID;

	/**
	 * Korisnicko ime koje se koristi za prijavu u sistem.
	 */
	private String username;

	/**
	 * Lozinka korisnika koja se koristi za autentifikaciju.
	 */
	private String password;

	/**
	 * Podrazumevani konstruktor bez argumenata. Neophodan za deserijalizaciju putem
	 * Jackson biblioteke.
	 */
	public User() {
	}

	/**
	 * Kreira novog korisnika sa zadatim podacima.
	 *
	 * @param userID   jedinstveni identifikator korisnika
	 * @param username korisnicko ime za prijavu u sistem
	 * @param password lozinka za autentifikaciju
	 */
	public User(int userID, String username, String password) {
		this.userID = userID;
		this.username = username;
		this.password = password;
	}

	/**
	 * Vraca jedinstveni identifikator korisnika.
	 *
	 * @return identifikator korisnika
	 */
	public int getUserID() {
		return userID;
	}

	/**
	 * Vraca korisnicko ime.
	 *
	 * @return korisnicko ime
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Vraca lozinku korisnika.
	 *
	 * @return lozinka
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Postavlja identifikator korisnika.
	 *
	 * @param userID novi identifikator
	 */
	public void setUserID(int userID) {
		this.userID = userID;
	}

	/**
	 * Postavlja korisnicko ime.
	 *
	 * @param username novo korisnicko ime
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Postavlja lozinku korisnika.
	 *
	 * @param password nova lozinka
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Vraca tekstualnu reprezentaciju korisnika.
	 *
	 * @return string sa identifikatorom i korisnickim imenom
	 */
	@Override
	public String toString() {
		return "User{id=" + userID + ", username='" + username + "'}";
	}
}
