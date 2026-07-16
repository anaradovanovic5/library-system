package com.library.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class LibrarianTest {

    @Test
    void konstruktor_postavlja_userID() {
        Librarian l = new Librarian(1, "bibliotekar1", "lozinka1", 1, 555);
        assertEquals(1, l.getUserID());
    }

    @Test
    void konstruktor_postavlja_librarianID() {
        Librarian l = new Librarian(1, "bibliotekar1", "lozinka1", 1, 555);
        assertEquals(1, l.getLibrarianID());
    }

    @Test
    void konstruktor_postavlja_employeeID() {
        Librarian l = new Librarian(1, "bibliotekar1", "lozinka1", 1, 555);
        assertEquals(555, l.getEmployeeID());
    }

    @Test
    void setLibrarianID_postavlja_vrednost() {
        Librarian l = new Librarian(1, "bibliotekar1", "lozinka1", 1, 555);
        l.setLibrarianID(9);
        assertEquals(9, l.getLibrarianID());
    }

    @ParameterizedTest
    @ValueSource(ints = { 0, -1, -50 })
    void setLibrarianID_baca_izuzetak_za_nepozitivne_vrednosti(int invalidId) {
        Librarian l = new Librarian(1, "bibliotekar1", "lozinka1", 1, 555);
        assertThrows(IllegalArgumentException.class, () -> l.setLibrarianID(invalidId));
    }

    @Test
    void setEmployeeID_postavlja_vrednost() {
        Librarian l = new Librarian(1, "bibliotekar1", "lozinka1", 1, 555);
        l.setEmployeeID(777);
        assertEquals(777, l.getEmployeeID());
    }

    @ParameterizedTest
    @ValueSource(ints = { 0, -1, -50 })
    void setEmployeeID_baca_izuzetak_za_nepozitivne_vrednosti(int invalidId) {
        Librarian l = new Librarian(1, "bibliotekar1", "lozinka1", 1, 555);
        assertThrows(IllegalArgumentException.class, () -> l.setEmployeeID(invalidId));
    }

    @Test
    void toString_sadrzi_employeeID() {
        Librarian l = new Librarian(1, "bibliotekar1", "lozinka1", 1, 555);
        assertTrue(l.toString().contains("555"));
    }

    @Test
    void equals_vraca_true_za_isti_userID_nasledjeno_iz_User() {
        Librarian l1 = new Librarian(1, "bibliotekar1", "lozinka1", 1, 555);
        Librarian l2 = new Librarian(1, "bibliotekar2", "lozinka2", 1, 999);
        assertEquals(l1, l2);
    }

    @Test
    void equals_vraca_false_za_razlicit_userID() {
        Librarian l1 = new Librarian(1, "bibliotekar1", "lozinka1", 1, 555);
        Librarian l2 = new Librarian(2, "bibliotekar1", "lozinka1", 2, 555);
        assertNotEquals(l1, l2);
    }

    @Test
    void hashCode_je_isti_za_jednake_objekte() {
        Librarian l1 = new Librarian(1, "bibliotekar1", "lozinka1", 1, 555);
        Librarian l2 = new Librarian(1, "bibliotekar2", "lozinka2", 1, 999);
        assertEquals(l1.hashCode(), l2.hashCode());
    }
}