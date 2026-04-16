package com.library.domain;

/**
 * Predstavlja autora knjige u sistemu biblioteke.
 *
 * <p>
 * Autor je povezan sa knjigama putem identifikatora. Jedna knjiga ima tacno
 * jednog autora.
 * </p>
 *
 * @author Ana Radovanovic
 * @version 1.0.0
 * @see Book
 */
public class Author {

	/**
	 * Jedinstveni identifikator autora.
	 */
	private int authorID;

	/**
	 * Ime i prezime autora.
	 */
	private String name;

	/**
	 * Podrazumevani konstruktor bez argumenata. Neophodan za deserijalizaciju putem
	 * Jackson biblioteke.
	 */
	public Author() {
	}

	/**
	 * Kreira novog autora sa zadatim podacima.
	 *
	 * @param authorID jedinstveni identifikator autora
	 * @param name     ime i prezime autora
	 */
	public Author(int authorID, String name) {
		this.authorID = authorID;
		this.name = name;
	}

	/**
	 * Vraca jedinstveni identifikator autora.
	 *
	 * @return identifikator autora
	 */
	public int getAuthorID() {
		return authorID;
	}

	/**
	 * Vraca ime autora.
	 *
	 * @return ime autora
	 */
	public String getName() {
		return name;
	}

	/**
	 * Postavlja identifikator autora.
	 *
	 * @param authorID novi identifikator
	 */
	public void setAuthorID(int authorID) {
		this.authorID = authorID;
	}

	/**
	 * Postavlja ime autora.
	 *
	 * @param name novo ime autora
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Vraca tekstualnu reprezentaciju autora.
	 *
	 * @return string sa identifikatorom i imenom autora
	 */
	@Override
	public String toString() {
		return "Author{id=" + authorID + ", name='" + name + "'}";
	}
}
