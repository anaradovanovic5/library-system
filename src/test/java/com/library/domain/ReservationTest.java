package com.library.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class ReservationTest {

    @Test
    void konstruktor_postavlja_id() {
        Reservation r = new Reservation(1, 2, 3);
        assertEquals(1, r.getReservationID());
    }

    @Test
    void konstruktor_postavlja_userID() {
        Reservation r = new Reservation(1, 2, 3);
        assertEquals(2, r.getUserID());
    }

    @Test
    void konstruktor_postavlja_bookID() {
        Reservation r = new Reservation(1, 2, 3);
        assertEquals(3, r.getBookID());
    }

    @Test
    void konstruktor_postavlja_status_active() {
        Reservation r = new Reservation(1, 2, 3);
        assertEquals("active", r.getStatus());
    }

    @Test
    void setReservationID_postavlja_vrednost() {
        Reservation r = new Reservation(1, 2, 3);
        r.setReservationID(9);
        assertEquals(9, r.getReservationID());
    }

    @ParameterizedTest
    @ValueSource(ints = { 0, -1 })
    void setReservationID_baca_izuzetak_za_nepozitivne_vrednosti(int invalidId) {
        Reservation r = new Reservation(1, 2, 3);
        assertThrows(IllegalArgumentException.class, () -> r.setReservationID(invalidId));
    }

    @Test
    void setUserID_postavlja_vrednost() {
        Reservation r = new Reservation(1, 2, 3);
        r.setUserID(8);
        assertEquals(8, r.getUserID());
    }

    @ParameterizedTest
    @ValueSource(ints = { 0, -1 })
    void setUserID_baca_izuzetak_za_nepozitivne_vrednosti(int invalidId) {
        Reservation r = new Reservation(1, 2, 3);
        assertThrows(IllegalArgumentException.class, () -> r.setUserID(invalidId));
    }

    @Test
    void setBookID_postavlja_vrednost() {
        Reservation r = new Reservation(1, 2, 3);
        r.setBookID(7);
        assertEquals(7, r.getBookID());
    }

    @ParameterizedTest
    @ValueSource(ints = { 0, -1 })
    void setBookID_baca_izuzetak_za_nepozitivne_vrednosti(int invalidId) {
        Reservation r = new Reservation(1, 2, 3);
        assertThrows(IllegalArgumentException.class, () -> r.setBookID(invalidId));
    }

    @ParameterizedTest
    @ValueSource(strings = { "active", "cancelled" })
    void setStatus_prihvata_dozvoljene_vrednosti(String validStatus) {
        Reservation r = new Reservation(1, 2, 3);
        r.setStatus(validStatus);
        assertEquals(validStatus, r.getStatus());
    }

    @Test
    void setStatus_baca_izuzetak_za_null() {
        Reservation r = new Reservation(1, 2, 3);
        assertThrows(IllegalArgumentException.class, () -> r.setStatus(null));
    }

    @Test
    void setStatus_baca_izuzetak_za_nepoznat_status() {
        Reservation r = new Reservation(1, 2, 3);
        assertThrows(IllegalArgumentException.class, () -> r.setStatus("nepoznato"));
    }

    @Test
    void toString_sadrzi_bookID_i_status() {
        Reservation r = new Reservation(1, 2, 3);
        String s = r.toString();
        assertTrue(s.contains("bookID=3"));
        assertTrue(s.contains("active"));
    }

    @Test
    void equals_vraca_true_za_isti_id() {
        Reservation r1 = new Reservation(1, 2, 3);
        Reservation r2 = new Reservation(1, 9, 9);
        assertEquals(r1, r2);
    }

    @Test
    void equals_vraca_false_za_razlicit_id() {
        Reservation r1 = new Reservation(1, 2, 3);
        Reservation r2 = new Reservation(2, 2, 3);
        assertNotEquals(r1, r2);
    }

    @Test
    void hashCode_je_isti_za_jednake_objekte() {
        Reservation r1 = new Reservation(1, 2, 3);
        Reservation r2 = new Reservation(1, 9, 9);
        assertEquals(r1.hashCode(), r2.hashCode());
    }
}