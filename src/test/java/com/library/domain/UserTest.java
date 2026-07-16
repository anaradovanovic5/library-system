package com.library.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

public class UserTest {

    @Test
    void konstruktor_postavlja_id() {
        User u = new User(1, "anaR", "lozinka1");
        assertEquals(1, u.getUserID());
    }

    @Test
    void konstruktor_postavlja_korisnicko_ime() {
        User u = new User(1, "anaR", "lozinka1");
        assertEquals("anaR", u.getUsername());
    }

    @Test
    void konstruktor_postavlja_lozinku() {
        User u = new User(1, "anaR", "lozinka1");
        assertEquals("lozinka1", u.getPassword());
    }

    @Test
    void setUserID_postavlja_vrednost() {
        User u = new User(1, "anaR", "lozinka1");
        u.setUserID(8);
        assertEquals(8, u.getUserID());
    }

    @ParameterizedTest
    @ValueSource(ints = { 0, -1, -50 })
    void setUserID_baca_izuzetak_za_nepozitivne_vrednosti(int invalidId) {
        User u = new User(1, "anaR", "lozinka1");
        assertThrows(IllegalArgumentException.class, () -> u.setUserID(invalidId));
    }

    @Test
    void setUsername_postavlja_vrednost() {
        User u = new User(1, "anaR", "lozinka1");
        u.setUsername("novoIme");
        assertEquals("novoIme", u.getUsername());
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = { " " })
    void setUsername_baca_izuzetak_za_prazno_korisnicko_ime(String invalidUsername) {
        User u = new User(1, "anaR", "lozinka1");
        assertThrows(IllegalArgumentException.class, () -> u.setUsername(invalidUsername));
    }

    @Test
    void setPassword_postavlja_vrednost() {
        User u = new User(1, "anaR", "lozinka1");
        u.setPassword("novaLozinka");
        assertEquals("novaLozinka", u.getPassword());
    }

    @Test
    void setPassword_baca_izuzetak_za_null() {
        User u = new User(1, "anaR", "lozinka1");
        assertThrows(IllegalArgumentException.class, () -> u.setPassword(null));
    }

    @ParameterizedTest
    @ValueSource(strings = { "", "a", "ab", "abc" })
    void setPassword_baca_izuzetak_za_lozinku_kracu_od_4_karaktera(String invalidPassword) {
        User u = new User(1, "anaR", "lozinka1");
        assertThrows(IllegalArgumentException.class, () -> u.setPassword(invalidPassword));
    }

    @Test
    void toString_sadrzi_korisnicko_ime() {
        User u = new User(1, "anaR", "lozinka1");
        assertTrue(u.toString().contains("anaR"));
    }

    @Test
    void equals_vraca_true_za_isti_id() {
        User u1 = new User(1, "anaR", "lozinka1");
        User u2 = new User(1, "drugiUser", "drugaLoz1");
        assertEquals(u1, u2);
    }

    @Test
    void equals_vraca_false_za_razlicit_id() {
        User u1 = new User(1, "anaR", "lozinka1");
        User u2 = new User(2, "anaR", "lozinka1");
        assertNotEquals(u1, u2);
    }

    @Test
    void equals_vraca_false_za_null() {
        User u1 = new User(1, "anaR", "lozinka1");
        assertNotEquals(u1, null);
    }

    @Test
    void hashCode_je_isti_za_jednake_objekte() {
        User u1 = new User(1, "anaR", "lozinka1");
        User u2 = new User(1, "drugiUser", "drugaLoz1");
        assertEquals(u1.hashCode(), u2.hashCode());
    }
}