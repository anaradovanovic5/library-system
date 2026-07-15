package com.library.domain;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Objects;

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
     * Datum kada je knjiga vracena, u formatu "YYYY-MM-DD". Vrednost je null dok knjiga nije vracena.
     */
    private String returnDate;

    /**
     * Podrazumevani konstruktor bez argumenata. Neophodan za deserijalizaciju putem Jackson biblioteke.
     */
    public Loan() {
    }

    /**
     * Kreira novu pozajmicu sa zadatim podacima. Datum vracanja se automatski
     * postavlja na null.
     *
     * @param loanID                    jedinstveni identifikator pozajmice
     * @param userID                    identifikator korisnika koji pozajmljuje
     * @param bookID                    identifikator knjige koja se pozajmljuje
     * @param loanDate                  datum pozajmice u formatu "YYYY-MM-DD"
     * @throws IllegalArgumentException ako neki od parametara nije valjan
     */
    public Loan(int loanID, int userID, int bookID, String loanDate) {
        setLoanID(loanID);
        setUserID(userID);
        setBookID(bookID);
        setLoanDate(loanDate);
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
     * @return datum vracanja u formatu "YYYY-MM-DD", ili null ako knjiga nije vracena
     */
    public String getReturnDate() {
        return returnDate;
    }

    /**
     * Postavlja identifikator pozajmice.
     *
     * @param loanID                    novi identifikator (ora biti pozitivan broj)
     * @throws IllegalArgumentException ako je loanID manji ili jednak nuli
     */
    public void setLoanID(int loanID) {
        if (loanID <= 0) {
            throw new IllegalArgumentException("ID pozajmice mora biti pozitivan broj.");
        }
        this.loanID = loanID;
    }

    /**
     * Postavlja identifikator korisnika.
     *
     * @param userID                    novi identifikator korisnika (mora biti pozitivan broj)
     * @throws IllegalArgumentException ako je userID manji ili jednak nuli
     */
    public void setUserID(int userID) {
        if (userID <= 0) {
            throw new IllegalArgumentException("ID korisnika mora biti pozitivan broj.");
        }
        this.userID = userID;
    }

    /**
     * Postavlja identifikator knjige.
     *
     * @param bookID                    novi identifikator knjige (mora biti pozitivan broj)
     * @throws IllegalArgumentException ako je bookID manji ili jednak nuli
     */
    public void setBookID(int bookID) {
        if (bookID <= 0) {
            throw new IllegalArgumentException("ID knjige mora biti pozitivan broj.");
        }
        this.bookID = bookID;
    }

    /**
     * Postavlja datum pozajmice.
     *
     * @param loanDate                  novi datum u formatu "YYYY-MM-DD" (ne moze biti null ili nevalidnog formata)
     * @throws IllegalArgumentException ako je loanDate null ili nije validan datum
     */
    public void setLoanDate(String loanDate) {
        if (loanDate == null) {
            throw new IllegalArgumentException("Datum pozajmice ne moze biti null.");
        }
        try {
            LocalDate.parse(loanDate);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Datum pozajmice nije validnog formata (YYYY-MM-DD): " + loanDate);
        }
        this.loanDate = loanDate;
    }

    /**
     * Postavlja datum vracanja knjige.
     *
     * @param returnDate                datum vracanja u formatu "YYYY-MM-DD", ili null ako knjiga nije vracena
     * @throws IllegalArgumentException ako returnDate nije null i nije validnog formata
     */
    public void setReturnDate(String returnDate) {
        if (returnDate != null) {
            try {
                LocalDate.parse(returnDate);
            } catch (DateTimeParseException e) {
                throw new IllegalArgumentException(
                        "Datum vracanja nije validnog formata (YYYY-MM-DD): " + returnDate);
            }
        }
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
     * Poredi ovu pozajmicu sa drugim objektom na osnovu identifikatora pozajmice.
     *
     * @param o objekat sa kojim se poredi
     * @return true ako su identifikatori pozajmica jednaki
     */
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Loan))
            return false;
        Loan other = (Loan) o;
        return loanID == other.loanID;
    }

    /**
     * Vraca hash kod pozajmice na osnovu identifikatora.
     *
     * @return hash kod
     */
    @Override
    public int hashCode() {
        return Objects.hash(loanID);
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