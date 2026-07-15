package com.library.domain;

import java.util.Objects;

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
     * Podrazumevani konstruktor bez argumenata. Neophodan za deserijalizaciju putem Jackson biblioteke.
     */
    public Category() {
    }

    /**
     * Kreira novu kategoriju sa zadatim podacima.
     *
     * @param categoryID                jedinstveni identifikator kategorije
     * @param categoryName              naziv kategorije
     * @throws IllegalArgumentException ako neki od parametara nije valjan
     */
    public Category(int categoryID, String categoryName) {
        setCategoryID(categoryID);
        setCategoryName(categoryName);
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
     * @param categoryID                novi identifikator (mora biti pozitivan broj)
     * @throws IllegalArgumentException ako je categoryID manji ili jednak nuli
     */
    public void setCategoryID(int categoryID) {
        if (categoryID <= 0) {
            throw new IllegalArgumentException("ID kategorije mora biti pozitivan broj.");
        }
        this.categoryID = categoryID;
    }

    /**
     * Postavlja naziv kategorije.
     *
     * @param categoryName              novi naziv kategorije (ne moze biti null ili praznog sadrzaja)
     * @throws IllegalArgumentException ako je categoryName null ili prazan string
     */
    public void setCategoryName(String categoryName) {
        if (categoryName == null || categoryName.isBlank()) {
            throw new IllegalArgumentException("Naziv kategorije ne moze biti praznog sadrzaja.");
        }
        this.categoryName = categoryName;
    }

    /**
     * Poredi ovu kategoriju sa drugim objektom na osnovu identifikatora kategorije.
     *
     * @param o objekat sa kojim se poredi
     * @return true ako su identifikatori kategorija jednaki
     */
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Category))
            return false;
        Category other = (Category) o;
        return categoryID == other.categoryID;
    }

    /**
     * Vraca hash kod kategorije na osnovu identifikatora.
     *
     * @return hash kod
     */
    @Override
    public int hashCode() {
        return Objects.hash(categoryID);
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