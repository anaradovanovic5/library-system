package com.library.domain;

import java.util.Objects;

/**
 * Predstavlja rezervaciju knjige u sistemu biblioteke.
 *
 * <p>
 * Rezervacija omogucava korisniku da rezervise knjigu unapred. Status
 * rezervacije moze biti "active" (aktivna) ili "cancelled" (otkazana).
 * </p>
 *
 * @author Ana Radovanovic
 * @version 1.0.0
 * @see Book
 * @see User
 */
public class Reservation {

    /**
     * Jedinstveni identifikator rezervacije.
     */
    private int reservationID;

    /**
     * Identifikator korisnika koji je napravio rezervaciju.
     */
    private int userID;

    /**
     * Identifikator rezervisane knjige.
     */
    private int bookID;

    /**
     * Trenutni status rezervacije. Moguce vrednosti: "active" (aktivna) ili "cancelled" (otkazana).
     */
    private String status;

    /**
     * Podrazumevani konstruktor bez argumenata. Neophodan za deserijalizaciju putem Jackson biblioteke.
     */
    public Reservation() {
    }

    /**
     * Kreira novu rezervaciju sa zadatim podacima. Status se automatski postavlja na "active".
     *
     * @param reservationID jedinstveni identifikator rezervacije
     * @param userID        identifikator korisnika koji rezervise
     * @param bookID        identifikator knjige koja se rezervise
     * @throws IllegalArgumentException ako neki od parametara nije valjan
     */
    public Reservation(int reservationID, int userID, int bookID) {
        setReservationID(reservationID);
        setUserID(userID);
        setBookID(bookID);
        this.status = "active";
    }

    /**
     * Vraca identifikator rezervacije.
     *
     * @return identifikator rezervacije
     */
    public int getReservationID() {
        return reservationID;
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
     * Vraca trenutni status rezervacije.
     *
     * @return status rezervacije ("active" ili "cancelled")
     */
    public String getStatus() {
        return status;
    }

    /**
     * Postavlja identifikator rezervacije.
     *
     * @param reservationID             novi identifikator (mora biti pozitivan broj)
     * @throws IllegalArgumentException ako je reservationID manji ili jednak nuli
     */
    public void setReservationID(int reservationID) {
        if (reservationID <= 0) {
            throw new IllegalArgumentException("ID rezervacije mora biti pozitivan broj.");
        }
        this.reservationID = reservationID;
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
     * Postavlja status rezervacije.
     *
     * @param status                    novi status ("active" ili "cancelled")
     * @throws IllegalArgumentException ako status nije jedna od dozvoljenih vrednosti
     */
    public void setStatus(String status) {
        if (status == null || (!status.equals("active") && !status.equals("cancelled"))) {
            throw new IllegalArgumentException("Status mora biti active ili cancelled.");
        }
        this.status = status;
    }

    /**
     * Poredi ovu rezervaciju sa drugim objektom na osnovu identifikatora
     * rezervacije.
     *
     * @param o objekat sa kojim se poredi
     * @return true ako su identifikatori rezervacija jednaki
     */
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Reservation))
            return false;
        Reservation other = (Reservation) o;
        return reservationID == other.reservationID;
    }

    /**
     * Vraca hash kod rezervacije na osnovu identifikatora.
     *
     * @return hash kod
     */
    @Override
    public int hashCode() {
        return Objects.hash(reservationID);
    }

    /**
     * Vraca tekstualnu reprezentaciju rezervacije.
     *
     * @return string sa identifikatorom, knjigom i statusom
     */
    @Override
    public String toString() {
        return "Reservation{id=" + reservationID + ", bookID=" + bookID + ", status='" + status + "'}";
    }
}