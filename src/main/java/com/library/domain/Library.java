package com.library.domain;

/**
 * Predstavlja biblioteku kao instituciju.
 *
 * <p>
 * Klasa cuva osnovne informacije o biblioteci kao sto su naziv i adresa.
 * Koristi se za identifikaciju institucije u sistemu.
 * </p>
 *
 * @author Ana Radovanovic
 * @version 1.0.0
 */
public class Library {

	/**
	 * Jedinstveni identifikator biblioteke.
	 */
	private int libraryID;

	/**
	 * Naziv biblioteke.
	 */
	private String name;

	/**
	 * Adresa biblioteke.
	 */
	private String address;

	/**
	 * Podrazumevani konstruktor bez argumenata. Neophodan za deserijalizaciju putem
	 * Jackson biblioteke.
	 */
	public Library() {
	}

	/**
	 * Kreira novu biblioteku sa zadatim podacima.
	 *
	 * @param libraryID jedinstveni identifikator biblioteke
	 * @param name      naziv biblioteke
	 * @param address   adresa biblioteke
	 */
	public Library(int libraryID, String name, String address) {
		this.libraryID = libraryID;
		this.name = name;
		this.address = address;
	}

	/**
	 * Vraca identifikator biblioteke.
	 *
	 * @return identifikator biblioteke
	 */
	public int getLibraryID() {
		return libraryID;
	}

	/**
	 * Vraca naziv biblioteke.
	 *
	 * @return naziv biblioteke
	 */
	public String getName() {
		return name;
	}

	/**
	 * Vraca adresu biblioteke.
	 *
	 * @return adresa biblioteke
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Postavlja identifikator biblioteke.
	 *
	 * @param libraryID novi identifikator
	 */
	public void setLibraryID(int libraryID) {
		this.libraryID = libraryID;
	}

	/**
	 * Postavlja naziv biblioteke.
	 *
	 * @param name novi naziv
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Postavlja adresu biblioteke.
	 *
	 * @param address nova adresa
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * Vraca tekstualnu reprezentaciju biblioteke.
	 *
	 * @return string sa identifikatorom, nazivom i adresom
	 */
	@Override
	public String toString() {
		return "Library{id=" + libraryID + ", name='" + name + "', address='" + address + "'}";
	}
}
