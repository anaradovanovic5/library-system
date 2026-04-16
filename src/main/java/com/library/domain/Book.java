package com.library.domain;

/**
 * Predstavlja knjigu u sistemu biblioteke.
 *
 * <p>
 * Knjiga ima naslov, ISBN broj, godinu izdanja i status koji pokazuje da li je
 * dostupna za pozajmljivanje. Status moze biti: "available" (dostupna),
 * "loaned" (pozajmljena) ili "reserved" (rezervisana).
 * </p>
 *
 * @author Ana Radovanovic
 * @version 1.0.0
 * @see Author
 * @see Category
 * @see Loan
 * @see Reservation
 */
public class Book {

	/**
	 * Jedinstveni identifikator knjige.
	 */
	private int bookID;

	/**
	 * Naslov knjige.
	 */
	private String title;

	/**
	 * Medjunarodni standardni knjizni broj (ISBN).
	 */
	private String isbn;

	/**
	 * Godina kada je knjiga objavljena.
	 */
	private int publishedYear;

	/**
	 * Trenutni status knjige. Moguce vrednosti: "available", "loaned", "reserved".
	 */
	private String status;

	/**
	 * Identifikator autora knjige.
	 */
	private int authorID;

	/**
	 * Identifikator kategorije kojoj knjiga pripada.
	 */
	private int categoryID;

	/**
	 * Podrazumevani konstruktor bez argumenata. Neophodan za deserijalizaciju putem
	 * Jackson biblioteke.
	 */
	public Book() {
	}

	/**
	 * Kreira novu knjigu sa zadatim podacima. Status knjige se automatski postavlja
	 * na "available".
	 *
	 * @param bookID        jedinstveni identifikator knjige
	 * @param title         naslov knjige
	 * @param isbn          ISBN broj knjige
	 * @param publishedYear godina izdanja
	 * @param authorID      identifikator autora
	 * @param categoryID    identifikator kategorije
	 */
	public Book(int bookID, String title, String isbn, int publishedYear, int authorID, int categoryID) {
		this.bookID = bookID;
		this.title = title;
		this.isbn = isbn;
		this.publishedYear = publishedYear;
		this.status = "available";
		this.authorID = authorID;
		this.categoryID = categoryID;
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
	 * Vraca naslov knjige.
	 *
	 * @return naslov knjige
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Vraca ISBN broj knjige.
	 *
	 * @return ISBN broj
	 */
	public String getIsbn() {
		return isbn;
	}

	/**
	 * Vraca godinu izdanja knjige.
	 *
	 * @return godina izdanja
	 */
	public int getPublishedYear() {
		return publishedYear;
	}

	/**
	 * Vraca trenutni status knjige.
	 *
	 * @return status knjige ("available", "loaned" ili "reserved")
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Vraca identifikator autora knjige.
	 *
	 * @return identifikator autora
	 */
	public int getAuthorID() {
		return authorID;
	}

	/**
	 * Vraca identifikator kategorije knjige.
	 *
	 * @return identifikator kategorije
	 */
	public int getCategoryID() {
		return categoryID;
	}

	/**
	 * Postavlja identifikator knjige.
	 *
	 * @param bookID novi identifikator
	 */
	public void setBookID(int bookID) {
		this.bookID = bookID;
	}

	/**
	 * Postavlja naslov knjige.
	 *
	 * @param title novi naslov
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Postavlja ISBN broj knjige.
	 *
	 * @param isbn novi ISBN broj
	 */
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	/**
	 * Postavlja godinu izdanja knjige.
	 *
	 * @param publishedYear nova godina izdanja
	 */
	public void setPublishedYear(int publishedYear) {
		this.publishedYear = publishedYear;
	}

	/**
	 * Postavlja status knjige.
	 *
	 * @param status novi status ("available", "loaned" ili "reserved")
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * Postavlja identifikator autora.
	 *
	 * @param authorID novi identifikator autora
	 */
	public void setAuthorID(int authorID) {
		this.authorID = authorID;
	}

	/**
	 * Postavlja identifikator kategorije.
	 *
	 * @param categoryID novi identifikator kategorije
	 */
	public void setCategoryID(int categoryID) {
		this.categoryID = categoryID;
	}

	/**
	 * Proverava da li je knjiga dostupna za pozajmljivanje.
	 *
	 * @return true ako je status "available", false u suprotnom
	 */
	public boolean isAvailable() {
		return "available".equals(status);
	}

	/**
	 * Vraca tekstualnu reprezentaciju knjige.
	 *
	 * @return string sa identifikatorom, naslovom i statusom
	 */
	@Override
	public String toString() {
		return "Book{id=" + bookID + ", title='" + title + "', status='" + status + "'}";
	}
}
