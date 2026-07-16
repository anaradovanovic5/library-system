package com.library.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

public class CategoryTest {

    @Test
    void konstruktor_postavlja_id() {
        Category c = new Category(1, "Fikcija");
        assertEquals(1, c.getCategoryID());
    }

    @Test
    void konstruktor_postavlja_naziv() {
        Category c = new Category(1, "Fikcija");
        assertEquals("Fikcija", c.getCategoryName());
    }

    @Test
    void setCategoryID_postavlja_vrednost() {
        Category c = new Category(1, "Fikcija");
        c.setCategoryID(9);
        assertEquals(9, c.getCategoryID());
    }

    @ParameterizedTest
    @ValueSource(ints = { 0, -1, -50 })
    void setCategoryID_baca_izuzetak_za_nepozitivne_vrednosti(int invalidId) {
        Category c = new Category(1, "Fikcija");
        assertThrows(IllegalArgumentException.class, () -> c.setCategoryID(invalidId));
    }

    @Test
    void setCategoryName_postavlja_vrednost() {
        Category c = new Category(1, "Fikcija");
        c.setCategoryName("Nauka");
        assertEquals("Nauka", c.getCategoryName());
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = { " ", "   " })
    void setCategoryName_baca_izuzetak_za_prazan_naziv(String invalidName) {
        Category c = new Category(1, "Fikcija");
        assertThrows(IllegalArgumentException.class, () -> c.setCategoryName(invalidName));
    }

    @Test
    void toString_sadrzi_naziv() {
        Category c = new Category(1, "Fikcija");
        assertTrue(c.toString().contains("Fikcija"));
    }

    @Test
    void equals_vraca_true_za_isti_id() {
        Category c1 = new Category(1, "Fikcija");
        Category c2 = new Category(1, "Nauka");
        assertEquals(c1, c2);
    }

    @Test
    void equals_vraca_false_za_razlicit_id() {
        Category c1 = new Category(1, "Fikcija");
        Category c2 = new Category(2, "Fikcija");
        assertNotEquals(c1, c2);
    }

    @Test
    void equals_vraca_false_za_null() {
        Category c1 = new Category(1, "Fikcija");
        assertNotEquals(c1, null);
    }

    @Test
    void equals_vraca_false_za_drugu_klasu() {
        Category c1 = new Category(1, "Fikcija");
        assertNotEquals(c1, "Fikcija");
    }

    @Test
    void hashCode_je_isti_za_jednake_objekte() {
        Category c1 = new Category(1, "Fikcija");
        Category c2 = new Category(1, "Nauka");
        assertEquals(c1.hashCode(), c2.hashCode());
    }
}