package com.library.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.library.domain.*;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LibraryService {

	private static final String USERS_FILE = "data/users.json";
	private static final String BOOKS_FILE = "data/books.json";
	private static final String AUTHORS_FILE = "data/authors.json";
	private static final String CATEGORIES_FILE = "data/categories.json";
	private static final String LOANS_FILE = "data/loans.json";
	private static final String RESERVATIONS_FILE = "data/reservations.json";

	private List<User> users = new ArrayList<>();
	private List<Book> books = new ArrayList<>();
	private List<Author> authors = new ArrayList<>();
	private List<Category> categories = new ArrayList<>();
	private List<Loan> loans = new ArrayList<>();
	private List<Reservation> reservations = new ArrayList<>();

	private User loggedInUser = null;

	private final ObjectMapper mapper = new ObjectMapper();

	public LibraryService() {
		new File("data").mkdirs();
		load();
	}

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

	public User login(String username, String password) {
		User user = findUserByUsername(username);
		if (user == null || !user.getPassword().equals(password)) {
			throw new IllegalArgumentException("Pogresno korisnicko ime ili lozinka.");
		}
		loggedInUser = user;
		return user;
	}

	public void logout() {
		loggedInUser = null;
	}

	public User getLoggedInUser() {
		return loggedInUser;
	}

	public Book addBook(String title, String isbn, int publishedYear, int authorID, int categoryID) {
		int id = nextBookId();
		Book book = new Book(id, title, isbn, publishedYear, authorID, categoryID);
		books.add(book);
		saveBooks();
		return book;
	}

	public void updateBook(int bookID, String newTitle, String newIsbn) {
		Book book = findBookById(bookID);
		if (newTitle != null && !newTitle.isBlank())
			book.setTitle(newTitle);
		if (newIsbn != null && !newIsbn.isBlank())
			book.setIsbn(newIsbn);
		saveBooks();
	}

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

	public Reservation createReservation(int userID, int bookID) {
		findBookById(bookID);

		int id = nextReservationId();
		Reservation res = new Reservation(id, userID, bookID);
		reservations.add(res);
		saveReservations();
		return res;
	}

	public void cancelReservation(int reservationID) {
		Reservation res = findReservationById(reservationID);
		if (!"active".equals(res.getStatus())) {
			throw new IllegalArgumentException("Rezervacija nije aktivna.");
		}
		res.setStatus("cancelled");
		saveReservations();
	}

	public List<Loan> getActiveLoans() {
		List<Loan> active = new ArrayList<>();
		for (Loan l : loans) {
			if (l.isActive())
				active.add(l);
		}
		return active;
	}

	public Author addAuthor(String name) {
		int id = nextAuthorId();
		Author a = new Author(id, name);
		authors.add(a);
		saveAuthors();
		return a;
	}

	public Category addCategory(String categoryName) {
		int id = nextCategoryId();
		Category c = new Category(id, categoryName);
		categories.add(c);
		saveCategories();
		return c;
	}

	public List<Book> getAllBooks() {
		return books;
	}

	public List<Author> getAllAuthors() {
		return authors;
	}

	public List<Category> getAllCategories() {
		return categories;
	}

	public List<User> getAllUsers() {
		return users;
	}

	public List<Loan> getAllLoans() {
		return loans;
	}

	public List<Reservation> getAllReservations() {
		return reservations;
	}

	private User findUserByUsername(String username) {
		for (User u : users) {
			if (u.getUsername().equals(username))
				return u;
		}
		return null;
	}

	private Book findBookById(int bookID) {
		for (Book b : books) {
			if (b.getBookID() == bookID)
				return b;
		}
		throw new IllegalArgumentException("Knjiga sa ID=" + bookID + " ne postoji.");
	}

	private Loan findLoanById(int loanID) {
		for (Loan l : loans) {
			if (l.getLoanID() == loanID)
				return l;
		}
		throw new IllegalArgumentException("Pozajmica sa ID=" + loanID + " ne postoji.");
	}

	private Reservation findReservationById(int reservationID) {
		for (Reservation r : reservations) {
			if (r.getReservationID() == reservationID)
				return r;
		}
		throw new IllegalArgumentException("Rezervacija sa ID=" + reservationID + " ne postoji.");
	}

	private int nextUserId() {
		return users.stream().mapToInt(User::getUserID).max().orElse(0) + 1;
	}

	private int nextBookId() {
		return books.stream().mapToInt(Book::getBookID).max().orElse(0) + 1;
	}

	private int nextAuthorId() {
		return authors.stream().mapToInt(Author::getAuthorID).max().orElse(0) + 1;
	}

	private int nextCategoryId() {
		return categories.stream().mapToInt(Category::getCategoryID).max().orElse(0) + 1;
	}

	private int nextLoanId() {
		return loans.stream().mapToInt(Loan::getLoanID).max().orElse(0) + 1;
	}

	private int nextReservationId() {
		return reservations.stream().mapToInt(Reservation::getReservationID).max().orElse(0) + 1;
	}

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
