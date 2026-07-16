package com.library.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class LoanTest {

    @Test
    void konstruktor_postavlja_id() {
        Loan loan = new Loan(1, 2, 3, "2024-01-15");
        assertEquals(1, loan.getLoanID());
    }

    @Test
    void konstruktor_postavlja_userID() {
        Loan loan = new Loan(1, 2, 3, "2024-01-15");
        assertEquals(2, loan.getUserID());
    }

    @Test
    void konstruktor_postavlja_bookID() {
        Loan loan = new Loan(1, 2, 3, "2024-01-15");
        assertEquals(3, loan.getBookID());
    }

    @Test
    void konstruktor_postavlja_datum_pozajmice() {
        Loan loan = new Loan(1, 2, 3, "2024-01-15");
        assertEquals("2024-01-15", loan.getLoanDate());
    }

    @Test
    void konstruktor_postavlja_datum_vracanja_na_null() {
        Loan loan = new Loan(1, 2, 3, "2024-01-15");
        assertNull(loan.getReturnDate());
    }

    @Test
    void isActive_vraca_true_kada_nije_vracena() {
        Loan loan = new Loan(1, 2, 3, "2024-01-15");
        assertTrue(loan.isActive());
    }

    @Test
    void isActive_vraca_false_kada_je_vracena() {
        Loan loan = new Loan(1, 2, 3, "2024-01-15");
        loan.setReturnDate("2024-02-01");
        assertFalse(loan.isActive());
    }

    @Test
    void setLoanID_postavlja_vrednost() {
        Loan loan = new Loan(1, 2, 3, "2024-01-15");
        loan.setLoanID(9);
        assertEquals(9, loan.getLoanID());
    }

    @ParameterizedTest
    @ValueSource(ints = { 0, -1 })
    void setLoanID_baca_izuzetak_za_nepozitivne_vrednosti(int invalidId) {
        Loan loan = new Loan(1, 2, 3, "2024-01-15");
        assertThrows(IllegalArgumentException.class, () -> loan.setLoanID(invalidId));
    }

    @Test
    void setUserID_postavlja_vrednost() {
        Loan loan = new Loan(1, 2, 3, "2024-01-15");
        loan.setUserID(8);
        assertEquals(8, loan.getUserID());
    }

    @ParameterizedTest
    @ValueSource(ints = { 0, -1 })
    void setUserID_baca_izuzetak_za_nepozitivne_vrednosti(int invalidId) {
        Loan loan = new Loan(1, 2, 3, "2024-01-15");
        assertThrows(IllegalArgumentException.class, () -> loan.setUserID(invalidId));
    }

    @Test
    void setBookID_postavlja_vrednost() {
        Loan loan = new Loan(1, 2, 3, "2024-01-15");
        loan.setBookID(7);
        assertEquals(7, loan.getBookID());
    }

    @ParameterizedTest
    @ValueSource(ints = { 0, -1 })
    void setBookID_baca_izuzetak_za_nepozitivne_vrednosti(int invalidId) {
        Loan loan = new Loan(1, 2, 3, "2024-01-15");
        assertThrows(IllegalArgumentException.class, () -> loan.setBookID(invalidId));
    }

    @Test
    void setLoanDate_postavlja_vrednost() {
        Loan loan = new Loan(1, 2, 3, "2024-01-15");
        loan.setLoanDate("2024-03-10");
        assertEquals("2024-03-10", loan.getLoanDate());
    }

    @Test
    void setLoanDate_baca_izuzetak_za_null() {
        Loan loan = new Loan(1, 2, 3, "2024-01-15");
        assertThrows(IllegalArgumentException.class, () -> loan.setLoanDate(null));
    }

    @ParameterizedTest
    @ValueSource(strings = { "15-01-2024", "nije datum", "2024/01/15", "" })
    void setLoanDate_baca_izuzetak_za_nevalidan_format(String invalidDate) {
        Loan loan = new Loan(1, 2, 3, "2024-01-15");
        assertThrows(IllegalArgumentException.class, () -> loan.setLoanDate(invalidDate));
    }

    @Test
    void setReturnDate_postavlja_vrednost() {
        Loan loan = new Loan(1, 2, 3, "2024-01-15");
        loan.setReturnDate("2024-02-01");
        assertEquals("2024-02-01", loan.getReturnDate());
    }

    @Test
    void setReturnDate_prihvata_null() {
        Loan loan = new Loan(1, 2, 3, "2024-01-15");
        loan.setReturnDate("2024-02-01");
        loan.setReturnDate(null);
        assertNull(loan.getReturnDate());
    }

    @ParameterizedTest
    @ValueSource(strings = { "01-02-2024", "nije datum" })
    void setReturnDate_baca_izuzetak_za_nevalidan_format(String invalidDate) {
        Loan loan = new Loan(1, 2, 3, "2024-01-15");
        assertThrows(IllegalArgumentException.class, () -> loan.setReturnDate(invalidDate));
    }

    @Test
    void toString_sadrzi_datum_i_status() {
        Loan loan = new Loan(1, 2, 3, "2024-01-15");
        String s = loan.toString();
        assertTrue(s.contains("2024-01-15"));
        assertTrue(s.contains("returned=false"));
    }

    @Test
    void equals_vraca_true_za_isti_id() {
        Loan loan1 = new Loan(1, 2, 3, "2024-01-15");
        Loan loan2 = new Loan(1, 9, 9, "2024-05-05");
        assertEquals(loan1, loan2);
    }

    @Test
    void equals_vraca_false_za_razlicit_id() {
        Loan loan1 = new Loan(1, 2, 3, "2024-01-15");
        Loan loan2 = new Loan(2, 2, 3, "2024-01-15");
        assertNotEquals(loan1, loan2);
    }

    @Test
    void hashCode_je_isti_za_jednake_objekte() {
        Loan loan1 = new Loan(1, 2, 3, "2024-01-15");
        Loan loan2 = new Loan(1, 9, 9, "2024-05-05");
        assertEquals(loan1.hashCode(), loan2.hashCode());
    }
}