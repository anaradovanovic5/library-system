package com.library.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

public class MemberTest {

    @Test
    void konstruktor_postavlja_id() {
        Member m = new Member(1, "anaR", "lozinka1", "Ana Radovanovic", "ana@mail.com");
        assertEquals(1, m.getUserID());
    }

    @Test
    void konstruktor_postavlja_korisnicko_ime() {
        Member m = new Member(1, "anaR", "lozinka1", "Ana Radovanovic", "ana@mail.com");
        assertEquals("anaR", m.getUsername());
    }

    @Test
    void konstruktor_postavlja_ime() {
        Member m = new Member(1, "anaR", "lozinka1", "Ana Radovanovic", "ana@mail.com");
        assertEquals("Ana Radovanovic", m.getName());
    }

    @Test
    void konstruktor_postavlja_email() {
        Member m = new Member(1, "anaR", "lozinka1", "Ana Radovanovic", "ana@mail.com");
        assertEquals("ana@mail.com", m.getEmail());
    }

    @Test
    void setName_postavlja_vrednost() {
        Member m = new Member(1, "anaR", "lozinka1", "Ana Radovanovic", "ana@mail.com");
        m.setName("Novo Ime");
        assertEquals("Novo Ime", m.getName());
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = { " " })
    void setName_baca_izuzetak_za_prazno_ime(String invalidName) {
        Member m = new Member(1, "anaR", "lozinka1", "Ana Radovanovic", "ana@mail.com");
        assertThrows(IllegalArgumentException.class, () -> m.setName(invalidName));
    }

    @Test
    void setEmail_postavlja_vrednost() {
        Member m = new Member(1, "anaR", "lozinka1", "Ana Radovanovic", "ana@mail.com");
        m.setEmail("novi@mail.com");
        assertEquals("novi@mail.com", m.getEmail());
    }

    @Test
    void setEmail_baca_izuzetak_za_null() {
        Member m = new Member(1, "anaR", "lozinka1", "Ana Radovanovic", "ana@mail.com");
        assertThrows(IllegalArgumentException.class, () -> m.setEmail(null));
    }

    @ParameterizedTest
    @ValueSource(strings = { "anamail.com", "ana", "" })
    void setEmail_baca_izuzetak_za_email_bez_at_karaktera(String invalidEmail) {
        Member m = new Member(1, "anaR", "lozinka1", "Ana Radovanovic", "ana@mail.com");
        assertThrows(IllegalArgumentException.class, () -> m.setEmail(invalidEmail));
    }

    @Test
    void toString_sadrzi_ime_i_email() {
        Member m = new Member(1, "anaR", "lozinka1", "Ana Radovanovic", "ana@mail.com");
        String s = m.toString();
        assertTrue(s.contains("Ana Radovanovic"));
        assertTrue(s.contains("ana@mail.com"));
    }

    @Test
    void equals_vraca_true_za_isti_userID_nasledjeno_iz_User() {
        Member m1 = new Member(1, "anaR", "lozinka1", "Ana Radovanovic", "ana@mail.com");
        Member m2 = new Member(1, "drugiUser", "drugaLoz1", "Drugo Ime", "drugi@mail.com");
        assertEquals(m1, m2);
    }

    @Test
    void equals_vraca_false_za_razlicit_userID() {
        Member m1 = new Member(1, "anaR", "lozinka1", "Ana Radovanovic", "ana@mail.com");
        Member m2 = new Member(2, "anaR", "lozinka1", "Ana Radovanovic", "ana@mail.com");
        assertNotEquals(m1, m2);
    }

    @Test
    void hashCode_je_isti_za_jednake_objekte() {
        Member m1 = new Member(1, "anaR", "lozinka1", "Ana Radovanovic", "ana@mail.com");
        Member m2 = new Member(1, "drugiUser", "drugaLoz1", "Drugo Ime", "drugi@mail.com");
        assertEquals(m1.hashCode(), m2.hashCode());
    }
}