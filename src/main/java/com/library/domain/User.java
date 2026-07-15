package com.library.domain;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import java.util.Objects;

/**
 * Bazna klasa koja predstavlja korisnika sistema biblioteke.
 *
 * <p>
 * Svaki korisnik ima jedinstveni identifikator, korisnicko ime i lozinku. Klasa
 * se nasledjuje od strane {@link Member} i {@link Librarian}.
 * </p>
 *
 * @author Ana Radovanovic
 * @version 1.0.0
 * @see Member
 * @see Librarian
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({ @JsonSubTypes.Type(value = Member.class, name = "member"),
        @JsonSubTypes.Type(value = Librarian.class, name = "librarian") })
public class User {

    /**
     * Jedinstveni identifikator korisnika.
     */
    private int userID;

    /**
     * Korisnicko ime koje se koristi za prijavu u sistem.
     */
    private String username;

    /**
     * Lozinka korisnika koja se koristi za autentifikaciju.
     */
    private String password;

    /**
     * Podrazumevani konstruktor bez argumenata. Neophodan za deserijalizaciju putem Jackson biblioteke.
     */
    public User() {
    }

    /**
     * Kreira novog korisnika sa zadatim podacima.
     *
     * @param userID                    jedinstveni identifikator korisnika
     * @param username                  korisnicko ime za prijavu u sistem
     * @param password                  lozinka za autentifikaciju
     * @throws IllegalArgumentException ako neki od parametara nije valjan
     */
    public User(int userID, String username, String password) {
        setUserID(userID);
        setUsername(username);
        setPassword(password);
    }

    /**
     * Vraca jedinstveni identifikator korisnika.
     *
     * @return identifikator korisnika
     */
    public int getUserID() {
        return userID;
    }

    /**
     * Vraca korisnicko ime.
     *
     * @return korisnicko ime
     */
    public String getUsername() {
        return username;
    }

    /**
     * Vraca lozinku korisnika.
     *
     * @return lozinka
     */
    public String getPassword() {
        return password;
    }

    /**
     * Postavlja identifikator korisnika.
     *
     * @param userID                    novi identifikator (mora biti pozitivan broj)
     * @throws IllegalArgumentException ako je userID manji ili jednak nuli
     */
    public void setUserID(int userID) {
        if (userID <= 0) {
            throw new IllegalArgumentException("ID korisnika mora biti pozitivan broj.");
        }
        this.userID = userID;
    }

    /**
     * Postavlja korisnicko ime.
     *
     * @param username                  novo korisnicko ime (ne moze biti null ili praznog sadrzaja)
     * @throws IllegalArgumentException ako je username null ili prazan string
     */
    public void setUsername(String username) {
        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException("Korisnicko ime ne moze biti praznog sadrzaja.");
        }
        this.username = username;
    }

    /**
     * Postavlja lozinku korisnika.
     *
     * @param password                  nova lozinka (ne moze biti null i mora imati bar 4 karaktera)
     * @throws IllegalArgumentException ako je password null ili kraca od 4 karaktera
     */
    public void setPassword(String password) {
        if (password == null || password.length() < 4) {
            throw new IllegalArgumentException("Lozinka mora imati bar 4 karaktera.");
        }
        this.password = password;
    }

    /**
     * Poredi ovog korisnika sa drugim objektom na osnovu identifikatora korisnika.
     *
     * @param o objekat sa kojim se poredi
     * @return true ako su identifikatori korisnika jednaki
     */
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof User))
            return false;
        User other = (User) o;
        return userID == other.userID;
    }

    /**
     * Vraca hash kod korisnika na osnovu identifikatora.
     *
     * @return hash kod
     */
    @Override
    public int hashCode() {
        return Objects.hash(userID);
    }

    /**
     * Vraca tekstualnu reprezentaciju korisnika.
     *
     * @return string sa identifikatorom i korisnickim imenom
     */
    @Override
    public String toString() {
        return "User{id=" + userID + ", username='" + username + "'}";
    }
}