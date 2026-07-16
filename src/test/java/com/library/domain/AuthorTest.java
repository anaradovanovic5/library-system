package com.library.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

public class AuthorTest {

    @Test
    void konstruktor_postavlja_id() {
        Author a = new Author(1, "Dzordz Orvel");
        assertEquals(1, a.getAuthorID());
    }

    @Test
    void konstruktor_postavlja_ime() {
        Author a = new Author(1, "Dzordz Orvel");
        assertEquals("Dzordz Orvel", a.getName());
    }

    @Test
    void setAuthorID_postavlja_vrednost() {
        Author a = new Author(1, "Dzordz Orvel");
        a.setAuthorID(7);
        assertEquals(7, a.getAuthorID());
    }

    @ParameterizedTest
    @ValueSource(ints = { 0, -1, -50 })
    void setAuthorID_baca_izuzetak_za_nepozitivne_vrednosti(int invalidId) {
        Author a = new Author(1, "Dzordz Orvel");
        assertThrows(IllegalArgumentException.class, () -> a.setAuthorID(invalidId));
    }

    @Test
    void setName_postavlja_vrednost() {
        Author a = new Author(1, "Dzordz Orvel");
        a.setName("Aldous Haksli");
        assertEquals("Aldous Haksli", a.getName());
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = { " ", "   " })
    void setName_baca_izuzetak_za_prazno_ime(String invalidName) {
        Author a = new Author(1, "Dzordz Orvel");
        assertThrows(IllegalArgumentException.class, () -> a.setName(invalidName));
    }

    @Test
    void toString_sadrzi_ime() {
        Author a = new Author(1, "Dzordz Orvel");
        assertTrue(a.toString().contains("Dzordz Orvel"));
    }

    @Test
    void equals_vraca_true_za_isti_id() {
        Author a1 = new Author(1, "Dzordz Orvel");
        Author a2 = new Author(1, "Drugo ime");
        assertEquals(a1, a2);
    }

    @Test
    void equals_vraca_false_za_razlicit_id() {
        Author a1 = new Author(1, "Dzordz Orvel");
        Author a2 = new Author(2, "Dzordz Orvel");
        assertNotEquals(a1, a2);
    }

    @Test
    void equals_vraca_false_za_null() {
        Author a1 = new Author(1, "Dzordz Orvel");
        assertNotEquals(a1, null);
    }

    @Test
    void equals_vraca_false_za_drugu_klasu() {
        Author a1 = new Author(1, "Dzordz Orvel");
        assertNotEquals(a1, "Dzordz Orvel");
    }

    @Test
    void hashCode_je_isti_za_jednake_objekte() {
        Author a1 = new Author(1, "Dzordz Orvel");
        Author a2 = new Author(1, "Drugo ime");
        assertEquals(a1.hashCode(), a2.hashCode());
    }
}