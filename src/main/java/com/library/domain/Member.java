package com.library.domain;

/**
 * Predstavlja clana biblioteke.
 *
 * <p>
 * Clan nasledjuje klasu {@link User} i dodaje ime i email adresu. Clan moze da
 * pozajmljuje knjige, pravi rezervacije i vraca knjige.
 * </p>
 *
 * @author Ana Radovanovic
 * @version 1.0.0
 * @see User
 */
public class Member extends User {

	/**
	 * Ime i prezime clana biblioteke.
	 */
	private String name;

	/**
	 * Email adresa clana koja se koristi za kontakt.
	 */
	private String email;

	/**
	 * Podrazumevani konstruktor bez argumenata. Neophodan za deserijalizaciju putem
	 * Jackson biblioteke.
	 */
	public Member() {
	}

	/**
	 * Kreira novog clana biblioteke sa svim potrebnim podacima.
	 *
	 * @param userID   jedinstveni identifikator korisnika
	 * @param username korisnicko ime za prijavu
	 * @param password lozinka za prijavu
	 * @param name     ime i prezime clana
	 * @param email    email adresa clana
	 */
	public Member(int userID, String username, String password, String name, String email) {
		super(userID, username, password);
		this.name = name;
		this.email = email;
	}

	/**
	 * Vraca ime i prezime clana.
	 *
	 * @return ime clana
	 */
	public String getName() {
		return name;
	}

	/**
	 * Vraca email adresu clana.
	 *
	 * @return email adresa
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Postavlja ime i prezime clana.
	 *
	 * @param name novo ime clana
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Postavlja email adresu clana.
	 *
	 * @param email nova email adresa
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Vraca tekstualnu reprezentaciju clana.
	 *
	 * @return string sa identifikatorom, imenom i emailom
	 */
	@Override
	public String toString() {
		return "Member{id=" + getUserID() + ", name='" + name + "', email='" + email + "'}";
	}
}
