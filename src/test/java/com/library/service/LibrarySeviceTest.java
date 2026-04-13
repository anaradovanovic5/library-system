package com.library.service;

import com.library.domain.*;
import org.junit.jupiter.api.*;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class LibraryServiceTest {

    private LibraryService service;

    @BeforeEach
    void setUp() {
        deleteIfExists("data/users.json");
        deleteIfExists("data/books.json");
        deleteIfExists("data/authors.json");
        deleteIfExists("data/categories.json");
        deleteIfExists("data/loans.json");
        deleteIfExists("data/reservations.json");
        service = new LibraryService();
    }

    private void deleteIfExists(String path) {
        File f = new File(path);
        if (f.exists()) f.delete();
    }


    @Test
    @Order(1)
    void registerMember_uspesno() {
        Member m = service.registerMember("ana", "pass", "Ana Jovic", "ana@mail.com");
        assertNotNull(m);
        assertEquals("ana", m.getUsername());
        assertEquals("Ana Jovic", m.getName());
    }

    @Test
    @Order(2)
    void registerMember_duplikatUsername_bacaIzuzetak() {
        service.registerMember("ana", "pass", "Ana", "ana@mail.com");
        assertThrows(IllegalArgumentException.class,
            () -> service.registerMember("ana", "druga", "Druga", "d@mail.com"));
    }


    @Test
    @Order(3)
    void login_ispravniPodaci_uspesno() {
        service.registerMember("ana", "pass123", "Ana", "ana@mail.com");
        User u = service.login("ana", "pass123");
        assertNotNull(u);
        assertEquals("ana", u.getUsername());
        assertEquals(u, service.getLoggedInUser());
    }

    @Test
    @Order(4)
    void login_pogresnaLozinka_bacaIzuzetak() {
        service.registerMember("ana", "pass123", "Ana", "ana@mail.com");
        assertThrows(IllegalArgumentException.class,
            () -> service.login("ana", "pogresna"));
    }

    @Test
    @Order(5)
    void logout_cistSession() {
        service.registerMember("ana", "pass", "Ana", "a@mail.com");
        service.login("ana", "pass");
        service.logout();
        assertNull(service.getLoggedInUser());
    }


    @Test
    @Order(6)
    void addBook_uspesno() {
        Book b = service.addBook("1984", "978-0451524935", 1949, 1, 1);
        assertNotNull(b);
        assertEquals("1984", b.getTitle());
        assertEquals("available", b.getStatus());
    }

    @Test
    @Order(7)
    void searchBooks_nadjePoBroju() {
        service.addBook("1984", "978-0451524935", 1949, 1, 1);
        service.addBook("Brave New World", "978-0060850524", 1932, 2, 1);

        List<Book> results = service.searchBooks("1984");
        assertEquals(1, results.size());
        assertEquals("1984", results.get(0).getTitle());
    }

    @Test
    @Order(8)
    void searchBooks_praznaPretragaVracaPraznoNijeNull() {
        service.addBook("Testna knjiga", "123", 2000, 1, 1);
        List<Book> results = service.searchBooks("xyzxyz");
        assertNotNull(results);
        assertTrue(results.isEmpty());
    }

    @Test
    @Order(9)
    void updateBook_menjaNaslov() {
        Book b = service.addBook("Stari naslov", "123", 2000, 1, 1);
        service.updateBook(b.getBookID(), "Novi naslov", null);
        List<Book> all = service.getAllBooks();
        assertEquals("Novi naslov", all.get(0).getTitle());
    }


    @Test
    @Order(10)
    void createLoan_dostupnaKnjiga_uspesno() {
        service.registerMember("ana", "pass", "Ana", "a@mail.com");
        Book b = service.addBook("Knjiga", "123", 2000, 1, 1);

        Loan l = service.createLoan(1, b.getBookID());
        assertNotNull(l);
        assertTrue(l.isActive());
        assertEquals("loaned", b.getStatus());
    }

    @Test
    @Order(11)
    void createLoan_nedostupnaKnjiga_bacaIzuzetak() {
        service.registerMember("ana", "pass", "Ana", "a@mail.com");
        Book b = service.addBook("Knjiga", "123", 2000, 1, 1);
        service.createLoan(1, b.getBookID());

        assertThrows(IllegalArgumentException.class,
            () -> service.createLoan(1, b.getBookID()));
    }

    @Test
    @Order(12)
    void returnBook_knjigaPostajeDostupna() {
        service.registerMember("ana", "pass", "Ana", "a@mail.com");
        Book b = service.addBook("Knjiga", "123", 2000, 1, 1);
        Loan l = service.createLoan(1, b.getBookID());

        service.returnBook(l.getLoanID());
        assertFalse(l.isActive());
        assertEquals("available", b.getStatus());
    }

    @Test
    @Order(13)
    void getActiveLoans_vracaSamoAktivne() {
        service.registerMember("ana", "pass", "Ana", "a@mail.com");
        Book b1 = service.addBook("Knjiga 1", "111", 2000, 1, 1);
        Book b2 = service.addBook("Knjiga 2", "222", 2001, 1, 1);

        Loan l1 = service.createLoan(1, b1.getBookID());
        service.createLoan(1, b2.getBookID());
        service.returnBook(l1.getLoanID());  // vrati prvu

        List<Loan> active = service.getActiveLoans();
        assertEquals(1, active.size());  // samo druga je aktivna
    }


    @Test
    @Order(14)
    void createReservation_uspesno() {
        service.registerMember("ana", "pass", "Ana", "a@mail.com");
        Book b = service.addBook("Knjiga", "123", 2000, 1, 1);

        Reservation r = service.createReservation(1, b.getBookID());
        assertNotNull(r);
        assertEquals("active", r.getStatus());
    }

    @Test
    @Order(15)
    void cancelReservation_menjStatusUCancelled() {
        service.registerMember("ana", "pass", "Ana", "a@mail.com");
        Book b = service.addBook("Knjiga", "123", 2000, 1, 1);
        Reservation r = service.createReservation(1, b.getBookID());

        service.cancelReservation(r.getReservationID());
        assertEquals("cancelled", r.getStatus());
    }


    @Test
    @Order(16)
    void addAuthor_uspesno() {
        Author a = service.addAuthor("George Orwell");
        assertNotNull(a);
        assertEquals("George Orwell", a.getName());
        assertEquals(1, service.getAllAuthors().size());
    }

    @Test
    @Order(17)
    void addCategory_uspesno() {
        Category c = service.addCategory("Fikcija");
        assertNotNull(c);
        assertEquals("Fikcija", c.getCategoryName());
        assertEquals(1, service.getAllCategories().size());
    }
}
