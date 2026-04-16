package com.library.domain;

/**
 * Predstavlja kategoriju (zanr) knjige u sistemu biblioteke.
 *
 * <p>
 * Kategorija se koristi za klasifikaciju knjiga. Primeri kategorija su:
 * fikcija, nauka, istorija i slicno.
 * </p>
 *
 * @author Ana Radovanovic
 * @version 1.0.0
 * @see Book
 */
public class Category {

	/**
	 * Jedinstveni identifikator kategorije.
	 */
	private int categoryID;

	/**
	 * Naziv kategorije (npr. "Fikcija", "Nauka", "Istorija").
	 */
	private String categoryName;

	/**
	 * Podrazumevani konstruktor bez argumenata. Neophodan za deserijalizaciju putem
	 * Jackson biblioteke.
	 */
	public Category() {
	}

	/**
	 * Kreira novu kategoriju sa zadatim podacima.
	 *
	 * @param categoryID   jedinstveni identifikator kategorije
	 * @param categoryName naziv kategorije
	 */
	public Category(int categoryID, String categoryName) {
		this.categoryID = categoryID;
		this.categoryName = categoryName;
	}

	/**
	 * Vraca jedinstveni identifikator kategorije.
	 *
	 * @return identifikator kategorije
	 */
	public int getCategoryID() {
		return categoryID;
	}

	/**
	 * Vraca naziv kategorije.
	 *
	 * @return naziv kategorije
	 */
	public String getCategoryName() {
		return categoryName;
	}

	/**
	 * Postavlja identifikator kategorije.
	 *
	 * @param categoryID novi identifikator
	 */
	public void setCategoryID(int categoryID) {
		this.categoryID = categoryID;
	}

	/**
	 * Postavlja naziv kategorije.
	 *
	 * @param categoryName novi naziv kategorije
	 */
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	/**
	 * Vraca tekstualnu reprezentaciju kategorije.
	 *
	 * @return string sa identifikatorom i nazivom kategorije
	 */
	@Override
	public String toString() {
		return "Category{id=" + categoryID + ", name='" + categoryName + "'}";
	}
}
