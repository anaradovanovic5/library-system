package com.library.domain;

/**
 * Predstavlja bibliotekara u sistemu biblioteke.
 *
 * <p>
 * Bibliotekar nasledjuje klasu {@link User} i ima administrativne privilegije.
 * Moze da dodaje i azurira knjige, kreira pozajmice i upravlja rezervacijama.
 * </p>
 *
 * @author Ana Radovanovic
 * @version 1.0.0
 * @see User
 */
public class Librarian extends User {

	/**
	 * Jedinstveni identifikator bibliotekara u sistemu.
	 */
	private int librarianID;

	/**
	 * Broj zaposlenog bibliotekara u instituciji.
	 */
	private int employeeID;

	/**
	 * Podrazumevani konstruktor bez argumenata. Neophodan za deserijalizaciju putem
	 * Jackson biblioteke.
	 */
	public Librarian() {
	}

	/**
	 * Kreira novog bibliotekara sa svim potrebnim podacima.
	 *
	 * @param userID      jedinstveni identifikator korisnika
	 * @param username    korisnicko ime za prijavu
	 * @param password    lozinka za prijavu
	 * @param librarianID identifikator bibliotekara
	 * @param employeeID  broj zaposlenog
	 */
	public Librarian(int userID, String username, String password, int librarianID, int employeeID) {
		super(userID, username, password);
		this.librarianID = librarianID;
		this.employeeID = employeeID;
	}

	/**
	 * Vraca identifikator bibliotekara.
	 *
	 * @return identifikator bibliotekara
	 */
	public int getLibrarianID() {
		return librarianID;
	}

	/**
	 * Vraca broj zaposlenog.
	 *
	 * @return broj zaposlenog
	 */
	public int getEmployeeID() {
		return employeeID;
	}

	/**
	 * Postavlja identifikator bibliotekara.
	 *
	 * @param librarianID novi identifikator
	 */
	public void setLibrarianID(int librarianID) {
		this.librarianID = librarianID;
	}

	/**
	 * Postavlja broj zaposlenog.
	 *
	 * @param employeeID novi broj zaposlenog
	 */
	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}

	/**
	 * Vraca tekstualnu reprezentaciju bibliotekara.
	 *
	 * @return string sa identifikatorom i brojem zaposlenog
	 */
	@Override
	public String toString() {
		return "Librarian{id=" + getUserID() + ", employeeID=" + employeeID + "}";
	}
}
