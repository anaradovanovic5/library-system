package com.library.service;

import com.library.domain.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LibraryServiceTest {

    private LibraryService service;
    private static final String TEST_FOLDER = "test-data";

    @BeforeEach
    void setUp() {
        service = new LibraryService(TEST_FOLDER);
    }

    @AfterEach
    void tearDown() {
        File folder = new File(TEST_FOLDER);
        File[] files = folder.listFiles();
        if (files != null) {
            for (File f : files) f.delete();
        }
        folder.delete();
    }

    //Registracija

    @Test
    void registerMember_registruje_novog_clana() {
        Member m = service.registerMember("ana1", "lozinka", "Ana", "ana@mail.com");
        assertEquals("ana1", m.getUsername());
        assertTrue(service.getAllUsers().contains(m));
    }

    @Test
    void registerMember_baca_izuzetak_za_zauzeto_korisnicko_ime() {
        service.registerMember("ana1", "lozinka", "Ana", "ana@mail.com");
        assertThrows(IllegalArgumentException.class, () ->
                service.registerMember("ana1", "drugalozinka", "Ana2", "ana2@mail.com"));
    }

    @Test
    void registerLibrarian_registruje_novog_bibliotekara() {
        Librarian l = service.registerLibrarian("bib1", "lozinka", 555);
        assertEquals(555, l.getEmployeeID());
    }

    //Login

    @Test
    void login_uspesan_sa_ispravnim_podacima() {
        service.registerMember("ana1", "lozinka", "Ana", "ana@mail.com");
        User u = service.login("ana1", "lozinka");
        assertEquals("ana1", u.getUsername());
        assertEquals(u, service.getLoggedInUser());
    }

    @Test
    void login_baca_izuzetak_za_nepostojeceg_korisnika() {
        assertThrows(IllegalArgumentException.class, () -> service.login("nepostoji", "sifra"));
    }

    @Test
    void login_baca_izuzetak_za_pogresnu_lozinku() {
        service.registerMember("ana1", "lozinka", "Ana", "ana@mail.com");
        assertThrows(IllegalArgumentException.class, () -> service.login("ana1", "pogresna"));
    }

    //Knjige

    @Test
    void addBook_dodaje_novu_knjigu() {
        Author a = service.addAuthor("Orvel");
        Category c = service.addCategory("Fikcija");
        Book b = service.addBook("1984", "111", 1949, a.getAuthorID(), c.getCategoryID());
        assertTrue(service.getAllBooks().contains(b));
        assertEquals("available", b.getStatus());
    }

    @Test
    void updateBook_azurira_naslov_i_isbn() {
        Author a = service.addAuthor("Orvel");
        Category c = service.addCategory("Fikcija");
        Book b = service.addBook("1984", "111", 1949, a.getAuthorID(), c.getCategoryID());
        service.updateBook(b.getBookID(), "Novi naslov", "222");
        assertEquals("Novi naslov", b.getTitle());
        assertEquals("222", b.getIsbn());
    }

    @Test
    void searchBooks_pronalazi_po_naslovu_case_insensitive() {
        Author a = service.addAuthor("Orvel");
        Category c = service.addCategory("Fikcija");
        service.addBook("1984", "111", 1949, a.getAuthorID(), c.getCategoryID());
        List<Book> found = service.searchBooks("1984");
        assertEquals(1, found.size());
    }

    //Pozajmica

    @Test
    void createLoan_kreira_pozajmicu_i_menja_status_knjige() {
        Author a = service.addAuthor("Orvel");
        Category c = service.addCategory("Fikcija");
        Book b = service.addBook("1984", "111", 1949, a.getAuthorID(), c.getCategoryID());
        Member m = service.registerMember("ana1", "lozinka", "Ana", "ana@mail.com");

        Loan loan = service.createLoan(m.getUserID(), b.getBookID());

        assertEquals("loaned", b.getStatus());
        assertTrue(loan.isActive());
    }

    @Test
    void createLoan_baca_izuzetak_ako_knjiga_nije_dostupna() {
        Author a = service.addAuthor("Orvel");
        Category c = service.addCategory("Fikcija");
        Book b = service.addBook("1984", "111", 1949, a.getAuthorID(), c.getCategoryID());
        Member m = service.registerMember("ana1", "lozinka", "Ana", "ana@mail.com");

        service.createLoan(m.getUserID(), b.getBookID());

        assertThrows(IllegalArgumentException.class, () ->
                service.createLoan(m.getUserID(), b.getBookID()));
    }

    @Test
    void returnBook_zatvara_pozajmicu_i_oslobadja_knjigu() {
        Author a = service.addAuthor("Orvel");
        Category c = service.addCategory("Fikcija");
        Book b = service.addBook("1984", "111", 1949, a.getAuthorID(), c.getCategoryID());
        Member m = service.registerMember("ana1", "lozinka", "Ana", "ana@mail.com");
        Loan loan = service.createLoan(m.getUserID(), b.getBookID());

        service.returnBook(loan.getLoanID());

        assertFalse(loan.isActive());
        assertEquals("available", b.getStatus());
    }

    //Rezervacija

    @Test
    void createReservation_baca_izuzetak_ako_je_knjiga_dostupna() {
        Author a = service.addAuthor("Orvel");
        Category c = service.addCategory("Fikcija");
        Book b = service.addBook("1984", "111", 1949, a.getAuthorID(), c.getCategoryID());
        Member m = service.registerMember("ana1", "lozinka", "Ana", "ana@mail.com");

        assertThrows(IllegalArgumentException.class, () ->
                service.createReservation(m.getUserID(), b.getBookID()));
    }

    @Test
    void createReservation_uspesna_kada_je_knjiga_pozajmljena() {
        Author a = service.addAuthor("Orvel");
        Category c = service.addCategory("Fikcija");
        Book b = service.addBook("1984", "111", 1949, a.getAuthorID(), c.getCategoryID());
        Member m1 = service.registerMember("ana1", "lozinka", "Ana", "ana@mail.com");
        Member m2 = service.registerMember("ana2", "lozinka", "Ana2", "ana2@mail.com");

        service.createLoan(m1.getUserID(), b.getBookID()); // knjiga postaje "loaned"

        Reservation r = service.createReservation(m2.getUserID(), b.getBookID());

        assertEquals("active", r.getStatus());
    }

    @Test
    void cancelReservation_otkazuje_aktivnu_rezervaciju() {
        Author a = service.addAuthor("Orvel");
        Category c = service.addCategory("Fikcija");
        Book b = service.addBook("1984", "111", 1949, a.getAuthorID(), c.getCategoryID());
        Member m1 = service.registerMember("ana1", "lozinka", "Ana", "ana@mail.com");
        Member m2 = service.registerMember("ana2", "lozinka", "Ana2", "ana2@mail.com");
        service.createLoan(m1.getUserID(), b.getBookID());
        Reservation r = service.createReservation(m2.getUserID(), b.getBookID());

        service.cancelReservation(r.getReservationID());

        assertEquals("cancelled", r.getStatus());
    }

    //Aktivne pozajmice

    @Test
    void getActiveLoans_vraca_samo_aktivne() {
        Author a = service.addAuthor("Orvel");
        Category c = service.addCategory("Fikcija");
        Book b1 = service.addBook("1984", "111", 1949, a.getAuthorID(), c.getCategoryID());
        Book b2 = service.addBook("Farma", "222", 1945, a.getAuthorID(), c.getCategoryID());
        Member m = service.registerMember("ana1", "lozinka", "Ana", "ana@mail.com");

        Loan loan1 = service.createLoan(m.getUserID(), b1.getBookID());
        Loan loan2 = service.createLoan(m.getUserID(), b2.getBookID());
        service.returnBook(loan1.getLoanID());

        List<Loan> active = service.getActiveLoans();
        assertEquals(1, active.size());
        assertTrue(active.contains(loan2));
    }
}