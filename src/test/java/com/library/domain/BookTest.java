package com.library.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

public class BookTest {

    @Test
    void konstruktor_postavlja_id() {
        Book b = new Book(1, "1984", "978-0451524935", 1949, 2, 3);
        assertEquals(1, b.getBookID());
    }

    @Test
    void konstruktor_postavlja_naslov() {
        Book b = new Book(1, "1984", "978-0451524935", 1949, 2, 3);
        assertEquals("1984", b.getTitle());
    }

    @Test
    void konstruktor_postavlja_isbn() {
        Book b = new Book(1, "1984", "978-0451524935", 1949, 2, 3);
        assertEquals("978-0451524935", b.getIsbn());
    }

    @Test
    void konstruktor_postavlja_godinu_izdanja() {
        Book b = new Book(1, "1984", "978-0451524935", 1949, 2, 3);
        assertEquals(1949, b.getPublishedYear());
    }

    @Test
    void konstruktor_postavlja_autora() {
        Book b = new Book(1, "1984", "978-0451524935", 1949, 2, 3);
        assertEquals(2, b.getAuthorID());
    }

    @Test
    void konstruktor_postavlja_kategoriju() {
        Book b = new Book(1, "1984", "978-0451524935", 1949, 2, 3);
        assertEquals(3, b.getCategoryID());
    }

    @Test
    void konstruktor_postavlja_status_available() {
        Book b = new Book(1, "Knjiga", "123", 2000, 1, 1);
        assertEquals("available", b.getStatus());
    }

    @Test
    void isAvailable_vraca_true_kada_je_dostupna() {
        Book b = new Book(1, "Knjiga", "123", 2000, 1, 1);
        assertTrue(b.isAvailable());
    }

    @Test
    void isAvailable_vraca_false_kada_je_pozajmljena() {
        Book b = new Book(1, "Knjiga", "123", 2000, 1, 1);
        b.setStatus("loaned");
        assertFalse(b.isAvailable());
    }

    @Test
    void isAvailable_vraca_false_kada_je_rezervisana() {
        Book b = new Book(1, "Knjiga", "123", 2000, 1, 1);
        b.setStatus("reserved");
        assertFalse(b.isAvailable());
    }

    @Test
    void setBookID_postavlja_vrednost() {
        Book b = new Book(1, "Knjiga", "123", 2000, 1, 1);
        b.setBookID(5);
        assertEquals(5, b.getBookID());
    }

    @ParameterizedTest
    @ValueSource(ints = { 0, -1, -100 })
    void setBookID_baca_izuzetak_za_nepozitivne_vrednosti(int invalidId) {
        Book b = new Book(1, "Knjiga", "123", 2000, 1, 1);
        assertThrows(IllegalArgumentException.class, () -> b.setBookID(invalidId));
    }

    @Test
    void setTitle_postavlja_vrednost() {
        Book b = new Book(1, "Knjiga", "123", 2000, 1, 1);
        b.setTitle("Novi naslov");
        assertEquals("Novi naslov", b.getTitle());
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = { " ", "   " })
    void setTitle_baca_izuzetak_za_prazan_naslov(String invalidTitle) {
        Book b = new Book(1, "Knjiga", "123", 2000, 1, 1);
        assertThrows(IllegalArgumentException.class, () -> b.setTitle(invalidTitle));
    }

    @Test
    void setIsbn_postavlja_vrednost() {
        Book b = new Book(1, "Knjiga", "123", 2000, 1, 1);
        b.setIsbn("999-9999999999");
        assertEquals("999-9999999999", b.getIsbn());
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = { " " })
    void setIsbn_baca_izuzetak_za_prazan_isbn(String invalidIsbn) {
        Book b = new Book(1, "Knjiga", "123", 2000, 1, 1);
        assertThrows(IllegalArgumentException.class, () -> b.setIsbn(invalidIsbn));
    }

    @Test
    void setPublishedYear_postavlja_vrednost() {
        Book b = new Book(1, "Knjiga", "123", 2000, 1, 1);
        b.setPublishedYear(2024);
        assertEquals(2024, b.getPublishedYear());
    }

    @ParameterizedTest
    @ValueSource(ints = { 0, -5, 2027, 3000 })
    void setPublishedYear_baca_izuzetak_za_nevalidnu_godinu(int invalidYear) {
        Book b = new Book(1, "Knjiga", "123", 2000, 1, 1);
        assertThrows(IllegalArgumentException.class, () -> b.setPublishedYear(invalidYear));
    }

    @ParameterizedTest
    @ValueSource(strings = { "available", "loaned", "reserved" })
    void setStatus_prihvata_dozvoljene_vrednosti(String validStatus) {
        Book b = new Book(1, "Knjiga", "123", 2000, 1, 1);
        b.setStatus(validStatus);
        assertEquals(validStatus, b.getStatus());
    }

    @Test
    void setStatus_baca_izuzetak_za_null() {
        Book b = new Book(1, "Knjiga", "123", 2000, 1, 1);
        assertThrows(IllegalArgumentException.class, () -> b.setStatus(null));
    }

    @Test
    void setStatus_baca_izuzetak_za_nepoznat_status() {
        Book b = new Book(1, "Knjiga", "123", 2000, 1, 1);
        assertThrows(IllegalArgumentException.class, () -> b.setStatus("obrisana"));
    }

    @Test
    void setAuthorID_postavlja_vrednost() {
        Book b = new Book(1, "Knjiga", "123", 2000, 1, 1);
        b.setAuthorID(10);
        assertEquals(10, b.getAuthorID());
    }

    @ParameterizedTest
    @ValueSource(ints = { 0, -1 })
    void setAuthorID_baca_izuzetak_za_nepozitivne_vrednosti(int invalidId) {
        Book b = new Book(1, "Knjiga", "123", 2000, 1, 1);
        assertThrows(IllegalArgumentException.class, () -> b.setAuthorID(invalidId));
    }

    @Test
    void setCategoryID_postavlja_vrednost() {
        Book b = new Book(1, "Knjiga", "123", 2000, 1, 1);
        b.setCategoryID(20);
        assertEquals(20, b.getCategoryID());
    }

    @ParameterizedTest
    @ValueSource(ints = { 0, -1 })
    void setCategoryID_baca_izuzetak_za_nepozitivne_vrednosti(int invalidId) {
        Book b = new Book(1, "Knjiga", "123", 2000, 1, 1);
        assertThrows(IllegalArgumentException.class, () -> b.setCategoryID(invalidId));
    }

    @Test
    void toString_sadrzi_naslov_i_status() {
        Book b = new Book(1, "1984", "123", 1949, 1, 1);
        String s = b.toString();
        assertTrue(s.contains("1984"));
        assertTrue(s.contains("available"));
    }

    @Test
    void equals_vraca_true_za_isti_id() {
        Book b1 = new Book(1, "1984", "123", 1949, 1, 1);
        Book b2 = new Book(1, "Drugi naslov", "999", 2000, 2, 2);
        assertEquals(b1, b2);
    }

    @Test
    void equals_vraca_false_za_razlicit_id() {
        Book b1 = new Book(1, "1984", "123", 1949, 1, 1);
        Book b2 = new Book(2, "1984", "123", 1949, 1, 1);
        assertNotEquals(b1, b2);
    }

    @Test
    void equals_vraca_false_za_null() {
        Book b1 = new Book(1, "1984", "123", 1949, 1, 1);
        assertNotEquals(b1, null);
    }

    @Test
    void equals_vraca_false_za_drugu_klasu() {
        Book b1 = new Book(1, "1984", "123", 1949, 1, 1);
        assertNotEquals(b1, "1984");
    }

    @Test
    void equals_vraca_true_za_isti_objekat() {
        Book b1 = new Book(1, "1984", "123", 1949, 1, 1);
        assertEquals(b1, b1);
    }

    @Test
    void hashCode_je_isti_za_jednake_objekte() {
        Book b1 = new Book(1, "1984", "123", 1949, 1, 1);
        Book b2 = new Book(1, "Drugi naslov", "999", 2000, 2, 2);
        assertEquals(b1.hashCode(), b2.hashCode());
    }
}