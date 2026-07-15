package com.library.domain;

import java.util.Objects;

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
     * Podrazumevani konstruktor bez argumenata. Neophodan za deserijalizaciju putem Jackson biblioteke.
     */
    public Book() {
    }

    /**
     * Kreira novu knjigu sa zadatim podacima. Status knjige se automatski postavlja
     * na "available".
     *
     * @param bookID                    jedinstveni identifikator knjige
     * @param title                     naslov knjige
     * @param isbn                      ISBN broj knjige
     * @param publishedYear             godina izdanja
     * @param authorID                  identifikator autora
     * @param categoryID                identifikator kategorije
     * @throws IllegalArgumentException ako neki od parametara nije valjan (vidi odgovarajuce setere)
     */
    public Book(int bookID, String title, String isbn, int publishedYear, int authorID, int categoryID) {
        setBookID(bookID);
        setTitle(title);
        setIsbn(isbn);
        setPublishedYear(publishedYear);
        setAuthorID(authorID);
        setCategoryID(categoryID);
        this.status = "available";
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
     * @param bookID                    novi identifikator, mora biti pozitivan broj
     * @throws IllegalArgumentException ako je bookID manji ili jednak nuli
     */
    public void setBookID(int bookID) {
        if (bookID <= 0) {
            throw new IllegalArgumentException("ID knjige mora biti pozitivan broj.");
        }
        this.bookID = bookID;
    }

    /**
     * Postavlja naslov knjige.
     *
     * @param title                     novi naslov, ne moze biti null ili praznog sadrzaja
     * @throws IllegalArgumentException ako je title null ili prazan string
     */
    public void setTitle(String title) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Naslov knjige ne moze biti praznog sadrzaja.");
        }
        this.title = title;
    }

    /**
     * Postavlja ISBN broj knjige.
     *
     * @param isbn                      novi ISBN broj, ne moze biti null ili praznog sadrzaja
     * @throws IllegalArgumentException ako je isbn null ili prazan string
     */
    public void setIsbn(String isbn) {
        if (isbn == null || isbn.isBlank()) {
            throw new IllegalArgumentException("ISBN ne moze biti praznog sadrzaja.");
        }
        this.isbn = isbn;
    }

    /**
     * Postavlja godinu izdanja knjige.
     *
     * @param publishedYear             nova godina izdanja, mora biti veca od 0 i ne veca od 2026
     * @throws IllegalArgumentException ako je publishedYear manji ili jednak nuli ili veci od 2026
     */
    public void setPublishedYear(int publishedYear) {
        if (publishedYear <= 0 || publishedYear > 2026) {
            throw new IllegalArgumentException("Godina izdanja nije valjana: " + publishedYear);
        }
        this.publishedYear = publishedYear;
    }

    /**
     * Postavlja status knjige.
     *
     * @param status                    novi status, mora biti jedna od vrednosti "available", "loaned" ili "reserved"
     * @throws IllegalArgumentException ako status nije jedna od dozvoljenih vrednosti
     */
    public void setStatus(String status) {
        if (status == null || (!status.equals("available") && !status.equals("loaned") && !status.equals("reserved"))) {
            throw new IllegalArgumentException("Status mora biti available, loaned ili reserved.");
        }
        this.status = status;
    }

    /**
     * Postavlja identifikator autora.
     *
     * @param authorID                  novi identifikator autora, mora biti pozitivan broj
     * @throws IllegalArgumentException ako je authorID manji ili jednak nuli
     */
    public void setAuthorID(int authorID) {
        if (authorID <= 0) {
            throw new IllegalArgumentException("ID autora mora biti pozitivan broj.");
        }
        this.authorID = authorID;
    }

    /**
     * Postavlja identifikator kategorije.
     *
     * @param categoryID                novi identifikator kategorije, mora biti pozitivan broj
     * @throws IllegalArgumentException ako je categoryID manji ili jednak nuli
     */
    public void setCategoryID(int categoryID) {
        if (categoryID <= 0) {
            throw new IllegalArgumentException("ID kategorije mora biti pozitivan broj.");
        }
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
     * Poredi ovu knjigu sa drugim objektom na osnovu identifikatora knjige.
     *
     * @param o objekat sa kojim se poredi
     * @return true ako su identifikatori knjiga jednaki
     */
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Book))
            return false;
        Book other = (Book) o;
        return bookID == other.bookID;
    }

    /**
     * Vraca hash kod knjige na osnovu identifikatora.
     *
     * @return hash kod
     */
    @Override
    public int hashCode() {
        return Objects.hash(bookID);
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