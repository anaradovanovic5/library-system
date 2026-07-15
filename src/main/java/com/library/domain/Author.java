package com.library.domain;

import java.util.Objects;

/**
 * Predstavlja autora knjige u sistemu biblioteke.
 *
 * <p>
 * Autor je povezan sa knjigama putem identifikatora. Jedna knjiga ima tacno jednog autora.
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
     * Podrazumevani konstruktor bez argumenata. Neophodan za deserijalizaciju putem Jackson biblioteke.
     */
    public Author() {
    }

    /**
     * Kreira novog autora sa zadatim podacima.
     *
     * @param authorID                  jedinstveni identifikator autora
     * @param name                      ime i prezime autora
     * @throws IllegalArgumentException ako neki od parametara nije valjan
     */
    public Author(int authorID, String name) {
        setAuthorID(authorID);
        setName(name);
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
     * @param authorID                  novi identifikator (mora biti pozitivan broj)
     * @throws IllegalArgumentException ako je authorID manji ili jednak nuli
     */
    public void setAuthorID(int authorID) {
        if (authorID <= 0) {
            throw new IllegalArgumentException("ID autora mora biti pozitivan broj.");
        }
        this.authorID = authorID;
    }

    /**
     * Postavlja ime autora.
     *
     * @param name                      novo ime autora (ne moze biti null ili praznog sadrzaja)
     * @throws IllegalArgumentException ako je name null ili prazan string
     */
    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Ime autora ne moze biti praznog sadrzaja.");
        }
        this.name = name;
    }

    /**
     * Poredi ovog autora sa drugim objektom na osnovu identifikatora autora.
     *
     * @param o objekat sa kojim se poredi
     * @return true ako su identifikatori autora jednaki
     */
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Author))
            return false;
        Author other = (Author) o;
        return authorID == other.authorID;
    }

    /**
     * Vraca hash kod autora na osnovu identifikatora.
     *
     * @return hash kod
     */
    @Override
    public int hashCode() {
        return Objects.hash(authorID);
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