package com.library.domain;

/**
 * Predstavlja pozajmicu knjige u sistemu biblioteke.
 *
 * <p>
 * Pozajmica biljezi koji korisnik je pozajmio koju knjigu, kada je pozajmica
 * napravljena i kada je knjiga vracena. Pozajmica je aktivna sve dok knjiga
 * nije vracena.
 * </p>
 *
 * @author Ana Radovanovic
 * @version 1.0.0
 * @see Book
 * @see User
 */
public class Loan {

	/**
	 * Jedinstveni identifikator pozajmice.
	 */
	private int loanID;

	/**
	 * Identifikator korisnika koji je pozajmio knjigu.
	 */
	private int userID;

	/**
	 * Identifikator pozajmljene knjige.
	 */
	private int bookID;

	/**
	 * Datum kada je pozajmica napravljena, u formatu "YYYY-MM-DD".
	 */
	private String loanDate;

	/**
	 * Datum kada je knjiga vracena, u formatu "YYYY-MM-DD". Vrednost je null dok
	 * knjiga nije vracena.
	 */
	private String returnDate;

	/**
	 * Podrazumevani konstruktor bez argumenata. Neophodan za deserijalizaciju putem
	 * Jackson biblioteke.
	 */
	public Loan() {
	}

	/**
	 * Kreira novu pozajmicu sa zadatim podacima. Datum vracanja se automatski
	 * postavlja na null.
	 *
	 * @param loanID   jedinstveni identifikator pozajmice
	 * @param userID   identifikator korisnika koji pozajmljuje
	 * @param bookID   identifikator knjige koja se pozajmljuje
	 * @param loanDate datum pozajmice u formatu "YYYY-MM-DD"
	 */
	public Loan(int loanID, int userID, int bookID, String loanDate) {
		this.loanID = loanID;
		this.userID = userID;
		this.bookID = bookID;
		this.loanDate = loanDate;
		this.returnDate = null;
	}

	/**
	 * Vraca identifikator pozajmice.
	 *
	 * @return identifikator pozajmice
	 */
	public int getLoanID() {
		return loanID;
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
	 * Vraca datum pozajmice.
	 *
	 * @return datum pozajmice u formatu "YYYY-MM-DD"
	 */
	public String getLoanDate() {
		return loanDate;
	}

	/**
	 * Vraca datum vracanja knjige.
	 *
	 * @return datum vracanja u formatu "YYYY-MM-DD", ili null ako knjiga nije
	 *         vracena
	 */
	public String getReturnDate() {
		return returnDate;
	}

	/**
	 * Postavlja identifikator pozajmice.
	 *
	 * @param loanID novi identifikator
	 */
	public void setLoanID(int loanID) {
		this.loanID = loanID;
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
	 * Postavlja datum pozajmice.
	 *
	 * @param loanDate novi datum u formatu "YYYY-MM-DD"
	 */
	public void setLoanDate(String loanDate) {
		this.loanDate = loanDate;
	}

	/**
	 * Postavlja datum vracanja knjige.
	 *
	 * @param returnDate datum vracanja u formatu "YYYY-MM-DD"
	 */
	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}

	/**
	 * Proverava da li je pozajmica aktivna. Pozajmica je aktivna sve dok knjiga
	 * nije vracena.
	 *
	 * @return true ako knjiga nije vracena, false ako jeste
	 */
	public boolean isActive() {
		return returnDate == null;
	}

	/**
	 * Vraca tekstualnu reprezentaciju pozajmice.
	 *
	 * @return string sa identifikatorom, knjigom, datumom i statusom
	 */
	@Override
	public String toString() {
		return "Loan{id=" + loanID + ", bookID=" + bookID + ", loanDate=" + loanDate + ", returned=" + !isActive()
				+ "}";
	}
}
