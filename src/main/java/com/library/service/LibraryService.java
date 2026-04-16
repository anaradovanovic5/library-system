package com.library.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.library.domain.*;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Servisna klasa koja sadrzi sve sistemske operacije biblioteke.
 *
 * <p>
 * Ova klasa implementira svih 12 sistemskih operacija: registraciju i prijavu
 * korisnika, upravljanje knjigama, pozajmicama i rezervacijama. Podaci se
 * cuvaju u memoriji i trajno snimaju u JSON fajlove putem Jackson biblioteke.
 * </p>
 *
 * @author Ana Radovanovic
 * @version 1.0.0
 */
public class LibraryService {

	/** Putanja do JSON fajla sa korisnicima. */
	private static final String USERS_FILE = "data/users.json";

	/** Putanja do JSON fajla sa knjigama. */
	private static final String BOOKS_FILE = "data/books.json";

	/** Putanja do JSON fajla sa autorima. */
	private static final String AUTHORS_FILE = "data/authors.json";

	/** Putanja do JSON fajla sa kategorijama. */
	private static final String CATEGORIES_FILE = "data/categories.json";

	/** Putanja do JSON fajla sa pozajmicama. */
	private static final String LOANS_FILE = "data/loans.json";

	/** Putanja do JSON fajla sa rezervacijama. */
	private static final String RESERVATIONS_FILE = "data/reservations.json";

	/** Lista svih korisnika ucitana u memoriju. */
	private List<User> users = new ArrayList<>();

	/** Lista svih knjiga ucitana u memoriju. */
	private List<Book> books = new ArrayList<>();

	/** Lista svih autora ucitana u memoriju. */
	private List<Author> authors = new ArrayList<>();

	/** Lista svih kategorija ucitana u memoriju. */
	private List<Category> categories = new ArrayList<>();

	/** Lista svih pozajmica ucitana u memoriju. */
	private List<Loan> loans = new ArrayList<>();

	/** Lista svih rezervacija ucitana u memoriju. */
	private List<Reservation> reservations = new ArrayList<>();

	/** Trenutno prijavljeni korisnik, null ako niko nije prijavljen. */
	private User loggedInUser = null;

	/** Jackson ObjectMapper za serijalizaciju i deserijalizaciju JSON-a. */
	private final ObjectMapper mapper = new ObjectMapper();

	/**
	 * Kreira novu instancu servisne klase. Automatski ucitava sve podatke iz JSON
	 * fajlova. Ako data/ folder ne postoji, kreira ga.
	 */
	public LibraryService() {
		new File("data").mkdirs();
		load();
	}

	// =========================================================================
	// 1. Registracija korisnika
	// =========================================================================

	/**
	 * Registruje novog clana biblioteke u sistemu.
	 *
	 * <p>
	 * Proverava da li korisnicko ime vec postoji u sistemu. Ako postoji, baca
	 * izuzetak. U suprotnom kreira novog clana i snima ga u JSON fajl.
	 * </p>
	 *
	 * @param username korisnicko ime (mora biti jedinstveno)
	 * @param password lozinka za prijavu
	 * @param name     ime i prezime clana
	 * @param email    email adresa clana
	 * @return novokreirani objekat clana
	 * @throws IllegalArgumentException ako korisnicko ime vec postoji
	 */
	public Member registerMember(String username, String password, String name, String email) {
		if (findUserByUsername(username) != null) {
			throw new IllegalArgumentException("Korisnicko ime vec postoji: " + username);
		}
		int id = nextUserId();
		Member m = new Member(id, username, password, name, email);
		users.add(m);
		saveUsers();
		return m;
	}

	/**
	 * Registruje novog bibliotekara u sistemu.
	 *
	 * <p>
	 * Proverava da li korisnicko ime vec postoji u sistemu. Ako postoji, baca
	 * izuzetak.
	 * </p>
	 *
	 * @param username   korisnicko ime (mora biti jedinstveno)
	 * @param password   lozinka za prijavu
	 * @param employeeID broj zaposlenog bibliotekara
	 * @return novokreirani objekat bibliotekara
	 * @throws IllegalArgumentException ako korisnicko ime vec postoji
	 */
	public Librarian registerLibrarian(String username, String password, int employeeID) {
		if (findUserByUsername(username) != null) {
			throw new IllegalArgumentException("Korisnicko ime vec postoji: " + username);
		}
		int id = nextUserId();
		Librarian l = new Librarian(id, username, password, id, employeeID);
		users.add(l);
		saveUsers();
		return l;
	}

	// =========================================================================
	// 2. Prijava korisnika
	// =========================================================================

	/**
	 * Prijavljuje korisnika u sistem sa zadatim kredencijalima.
	 *
	 * <p>
	 * Trazi korisnika po korisnickom imenu i proverava lozinku. Ako su kredencijali
	 * ispravni, postavlja prijavljenog korisnika.
	 * </p>
	 *
	 * @param username korisnicko ime
	 * @param password lozinka
	 * @return prijavljeni korisnik
	 * @throws IllegalArgumentException ako su korisnicko ime ili lozinka pogresni
	 */
	public User login(String username, String password) {
		User user = findUserByUsername(username);
		if (user == null || !user.getPassword().equals(password)) {
			throw new IllegalArgumentException("Pogresno korisnicko ime ili lozinka.");
		}
		loggedInUser = user;
		return user;
	}

	/**
	 * Odjavljuje trenutno prijavljenog korisnika iz sistema.
	 */
	public void logout() {
		loggedInUser = null;
	}

	/**
	 * Vraca trenutno prijavljenog korisnika.
	 *
	 * @return prijavljeni korisnik, ili null ako niko nije prijavljen
	 */
	public User getLoggedInUser() {
		return loggedInUser;
	}

	// =========================================================================
	// 3. Dodavanje knjige
	// =========================================================================

	/**
	 * Dodaje novu knjigu u katalog biblioteke.
	 *
	 * <p>
	 * Nova knjiga dobija automatski generisan identifikator i pocetni status
	 * "available".
	 * </p>
	 *
	 * @param title         naslov knjige
	 * @param isbn          ISBN broj knjige
	 * @param publishedYear godina izdanja
	 * @param authorID      identifikator autora
	 * @param categoryID    identifikator kategorije
	 * @return novokreirani objekat knjige
	 */
	public Book addBook(String title, String isbn, int publishedYear, int authorID, int categoryID) {
		int id = nextBookId();
		Book book = new Book(id, title, isbn, publishedYear, authorID, categoryID);
		books.add(book);
		saveBooks();
		return book;
	}

	// =========================================================================
	// 4. Azuriranje informacija o knjizi
	// =========================================================================

	/**
	 * Azurira naslov i ISBN postojece knjige.
	 *
	 * <p>
	 * Ako je neki od parametara null ili prazan string, odgovarajuce polje se ne
	 * menja.
	 * </p>
	 *
	 * @param bookID   identifikator knjige koja se azurira
	 * @param newTitle novi naslov knjige, ili null ako se ne menja
	 * @param newIsbn  novi ISBN broj, ili null ako se ne menja
	 * @throws IllegalArgumentException ako knjiga sa datim ID-em ne postoji
	 */
	public void updateBook(int bookID, String newTitle, String newIsbn) {
		Book book = findBookById(bookID);
		if (newTitle != null && !newTitle.isBlank())
			book.setTitle(newTitle);
		if (newIsbn != null && !newIsbn.isBlank())
			book.setIsbn(newIsbn);
		saveBooks();
	}

	// =========================================================================
	// 5. Pretraga knjiga
	// =========================================================================

	/**
	 * Pretrazuje knjige po kljucnoj reci.
	 *
	 * <p>
	 * Pretraga se vrsi po naslovu i ISBN broju knjige, bez razlikovanja velikih i
	 * malih slova.
	 * </p>
	 *
	 * @param keyword kljucna rec za pretragu
	 * @return lista knjiga koje odgovaraju kljucnoj reci, ili prazna lista
	 */
	public List<Book> searchBooks(String keyword) {
		List<Book> result = new ArrayList<>();
		String kw = keyword.toLowerCase();
		for (Book b : books) {
			if (b.getTitle().toLowerCase().contains(kw) || b.getIsbn().toLowerCase().contains(kw)) {
				result.add(b);
			}
		}
		return result;
	}

	// =========================================================================
	// 6. Kreiranje pozajmice
	// =========================================================================

	/**
	 * Kreira pozajmicu za zadatog korisnika i knjigu.
	 *
	 * <p>
	 * Knjiga mora biti dostupna (status "available"). Nakon kreiranja pozajmice,
	 * status knjige se menja na "loaned". Datum pozajmice se automatski postavlja
	 * na danasnji datum.
	 * </p>
	 *
	 * @param userID identifikator korisnika koji pozajmljuje
	 * @param bookID identifikator knjige koja se pozajmljuje
	 * @return novokreirani objekat pozajmice
	 * @throws IllegalArgumentException ako knjiga nije dostupna ili ne postoji
	 */
	public Loan createLoan(int userID, int bookID) {
		Book book = findBookById(bookID);
		if (!book.isAvailable()) {
			throw new IllegalArgumentException("Knjiga nije dostupna (status: " + book.getStatus() + ")");
		}
		book.setStatus("loaned");
		saveBooks();

		int id = nextLoanId();
		String today = LocalDate.now().toString();
		Loan loan = new Loan(id, userID, bookID, today);
		loans.add(loan);
		saveLoans();
		return loan;
	}

	// =========================================================================
	// 7. Evidentiranje povrata knjige
	// =========================================================================

	/**
	 * Evidentira povrat knjige i zatvara pozajmicu.
	 *
	 * <p>
	 * Postavlja datum vracanja na danasnji datum i menja status knjige nazad na
	 * "available".
	 * </p>
	 *
	 * @param loanID identifikator pozajmice koja se zatvara
	 * @throws IllegalArgumentException ako pozajmica ne postoji ili je vec
	 *                                  zatvorena
	 */
	public void returnBook(int loanID) {
		Loan loan = findLoanById(loanID);
		if (!loan.isActive()) {
			throw new IllegalArgumentException("Pozajmica je vec zatvorena.");
		}
		loan.setReturnDate(LocalDate.now().toString());
		saveLoans();

		Book book = findBookById(loan.getBookID());
		book.setStatus("available");
		saveBooks();
	}

	// =========================================================================
	// 8. Kreiranje rezervacije
	// =========================================================================

	/**
	 * Kreira rezervaciju knjige za zadatog korisnika.
	 *
	 * <p>
	 * Proverava da li knjiga postoji u sistemu. Nova rezervacija dobija status
	 * "active".
	 * </p>
	 *
	 * @param userID identifikator korisnika koji rezervise
	 * @param bookID identifikator knjige koja se rezervise
	 * @return novokreirani objekat rezervacije
	 * @throws IllegalArgumentException ako knjiga ne postoji
	 */
	public Reservation createReservation(int userID, int bookID) {
		findBookById(bookID);
		int id = nextReservationId();
		Reservation res = new Reservation(id, userID, bookID);
		reservations.add(res);
		saveReservations();
		return res;
	}

	// =========================================================================
	// 9. Otkazivanje rezervacije
	// =========================================================================

	/**
	 * Otkazuje aktivnu rezervaciju.
	 *
	 * <p>
	 * Status rezervacije se menja na "cancelled". Moze se otkazati samo rezervacija
	 * sa statusom "active".
	 * </p>
	 *
	 * @param reservationID identifikator rezervacije koja se otkazuje
	 * @throws IllegalArgumentException ako rezervacija ne postoji ili nije aktivna
	 */
	public void cancelReservation(int reservationID) {
		Reservation res = findReservationById(reservationID);
		if (!"active".equals(res.getStatus())) {
			throw new IllegalArgumentException("Rezervacija nije aktivna.");
		}
		res.setStatus("cancelled");
		saveReservations();
	}

	// =========================================================================
	// 10. Pregled aktivnih pozajmica
	// =========================================================================

	/**
	 * Vraca listu svih trenutno aktivnih pozajmica.
	 *
	 * <p>
	 * Aktivna pozajmica je ona kod koje knjiga jos nije vracena, tj. datum vracanja
	 * je null.
	 * </p>
	 *
	 * @return lista aktivnih pozajmica, ili prazna lista ako nema aktivnih
	 */
	public List<Loan> getActiveLoans() {
		List<Loan> active = new ArrayList<>();
		for (Loan l : loans) {
			if (l.isActive())
				active.add(l);
		}
		return active;
	}

	// =========================================================================
	// 11. Dodavanje autora
	// =========================================================================

	/**
	 * Dodaje novog autora u sistem.
	 *
	 * <p>
	 * Autor dobija automatski generisan identifikator.
	 * </p>
	 *
	 * @param name ime i prezime autora
	 * @return novokreirani objekat autora
	 */
	public Author addAuthor(String name) {
		int id = nextAuthorId();
		Author a = new Author(id, name);
		authors.add(a);
		saveAuthors();
		return a;
	}

	// =========================================================================
	// 12. Dodavanje kategorije
	// =========================================================================

	/**
	 * Dodaje novu kategoriju knjiga u sistem.
	 *
	 * <p>
	 * Kategorija dobija automatski generisan identifikator.
	 * </p>
	 *
	 * @param categoryName naziv nove kategorije
	 * @return novokreirani objekat kategorije
	 */
	public Category addCategory(String categoryName) {
		int id = nextCategoryId();
		Category c = new Category(id, categoryName);
		categories.add(c);
		saveCategories();
		return c;
	}

	// =========================================================================
	// Getter metode
	// =========================================================================

	/**
	 * Vraca listu svih knjiga u sistemu.
	 *
	 * @return lista svih knjiga
	 */
	public List<Book> getAllBooks() {
		return books;
	}

	/**
	 * Vraca listu svih autora u sistemu.
	 *
	 * @return lista svih autora
	 */
	public List<Author> getAllAuthors() {
		return authors;
	}

	/**
	 * Vraca listu svih kategorija u sistemu.
	 *
	 * @return lista svih kategorija
	 */
	public List<Category> getAllCategories() {
		return categories;
	}

	/**
	 * Vraca listu svih korisnika u sistemu.
	 *
	 * @return lista svih korisnika
	 */
	public List<User> getAllUsers() {
		return users;
	}

	/**
	 * Vraca listu svih pozajmica u sistemu.
	 *
	 * @return lista svih pozajmica
	 */
	public List<Loan> getAllLoans() {
		return loans;
	}

	/**
	 * Vraca listu svih rezervacija u sistemu.
	 *
	 * @return lista svih rezervacija
	 */
	public List<Reservation> getAllReservations() {
		return reservations;
	}

	// =========================================================================
	// Privatne pomocne metode
	// =========================================================================

	/**
	 * Trazi korisnika po korisnickom imenu.
	 *
	 * @param username korisnicko ime
	 * @return pronadjeni korisnik, ili null ako ne postoji
	 */
	private User findUserByUsername(String username) {
		for (User u : users) {
			if (u.getUsername().equals(username))
				return u;
		}
		return null;
	}

	/**
	 * Trazi knjigu po identifikatoru.
	 *
	 * @param bookID identifikator knjige
	 * @return pronadjena knjiga
	 * @throws IllegalArgumentException ako knjiga ne postoji
	 */
	private Book findBookById(int bookID) {
		for (Book b : books) {
			if (b.getBookID() == bookID)
				return b;
		}
		throw new IllegalArgumentException("Knjiga sa ID=" + bookID + " ne postoji.");
	}

	/**
	 * Trazi pozajmicu po identifikatoru.
	 *
	 * @param loanID identifikator pozajmice
	 * @return pronadjena pozajmica
	 * @throws IllegalArgumentException ako pozajmica ne postoji
	 */
	private Loan findLoanById(int loanID) {
		for (Loan l : loans) {
			if (l.getLoanID() == loanID)
				return l;
		}
		throw new IllegalArgumentException("Pozajmica sa ID=" + loanID + " ne postoji.");
	}

	/**
	 * Trazi rezervaciju po identifikatoru.
	 *
	 * @param reservationID identifikator rezervacije
	 * @return pronadjena rezervacija
	 * @throws IllegalArgumentException ako rezervacija ne postoji
	 */
	private Reservation findReservationById(int reservationID) {
		for (Reservation r : reservations) {
			if (r.getReservationID() == reservationID)
				return r;
		}
		throw new IllegalArgumentException("Rezervacija sa ID=" + reservationID + " ne postoji.");
	}

	/**
	 * Generise sledeci slobodni identifikator za korisnika.
	 *
	 * @return novi jedinstveni identifikator
	 */
	private int nextUserId() {
		return users.stream().mapToInt(User::getUserID).max().orElse(0) + 1;
	}

	/**
	 * Generise sledeci slobodni identifikator za knjigu.
	 *
	 * @return novi jedinstveni identifikator
	 */
	private int nextBookId() {
		return books.stream().mapToInt(Book::getBookID).max().orElse(0) + 1;
	}

	/**
	 * Generise sledeci slobodni identifikator za autora.
	 *
	 * @return novi jedinstveni identifikator
	 */
	private int nextAuthorId() {
		return authors.stream().mapToInt(Author::getAuthorID).max().orElse(0) + 1;
	}

	/**
	 * Generise sledeci slobodni identifikator za kategoriju.
	 *
	 * @return novi jedinstveni identifikator
	 */
	private int nextCategoryId() {
		return categories.stream().mapToInt(Category::getCategoryID).max().orElse(0) + 1;
	}

	/**
	 * Generise sledeci slobodni identifikator za pozajmicu.
	 *
	 * @return novi jedinstveni identifikator
	 */
	private int nextLoanId() {
		return loans.stream().mapToInt(Loan::getLoanID).max().orElse(0) + 1;
	}

	/**
	 * Generise sledeci slobodni identifikator za rezervaciju.
	 *
	 * @return novi jedinstveni identifikator
	 */
	private int nextReservationId() {
		return reservations.stream().mapToInt(Reservation::getReservationID).max().orElse(0) + 1;
	}

	/**
	 * Ucitava sve podatke iz JSON fajlova u memoriju.
	 */
	private void load() {
		users = loadList(USERS_FILE, new TypeReference<List<User>>() {
		});
		books = loadList(BOOKS_FILE, new TypeReference<List<Book>>() {
		});
		authors = loadList(AUTHORS_FILE, new TypeReference<List<Author>>() {
		});
		categories = loadList(CATEGORIES_FILE, new TypeReference<List<Category>>() {
		});
		loans = loadList(LOANS_FILE, new TypeReference<List<Loan>>() {
		});
		reservations = loadList(RESERVATIONS_FILE, new TypeReference<List<Reservation>>() {
		});
	}

	/**
	 * Genericki pomocna metoda za ucitavanje liste iz JSON fajla.
	 *
	 * @param <T>  tip elemenata u listi
	 * @param path putanja do JSON fajla
	 * @param ref  Jackson TypeReference za tip liste
	 * @return ucitana lista, ili prazna lista ako fajl ne postoji
	 */
	private <T> List<T> loadList(String path, TypeReference<List<T>> ref) {
		File f = new File(path);
		if (!f.exists() || f.length() == 0)
			return new ArrayList<>();
		try {
			return mapper.readValue(f, ref);
		} catch (IOException e) {
			System.err.println("Greska pri ucitavanju " + path + ": " + e.getMessage());
			return new ArrayList<>();
		}
	}

	/**
	 * Snima objekat u JSON fajl.
	 *
	 * @param path putanja do JSON fajla
	 * @param data objekat koji se snima
	 */
	private void save(String path, Object data) {
		try {
			mapper.writerWithDefaultPrettyPrinter().writeValue(new File(path), data);
		} catch (IOException e) {
			System.err.println("Greska pri snimanju " + path + ": " + e.getMessage());
		}
	}

	private void saveUsers() {
		save(USERS_FILE, users);
	}

	private void saveBooks() {
		save(BOOKS_FILE, books);
	}

	private void saveAuthors() {
		save(AUTHORS_FILE, authors);
	}

	private void saveCategories() {
		save(CATEGORIES_FILE, categories);
	}

	private void saveLoans() {
		save(LOANS_FILE, loans);
	}

	private void saveReservations() {
		save(RESERVATIONS_FILE, reservations);
	}
}
