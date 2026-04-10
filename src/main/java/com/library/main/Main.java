package com.library.main;

import com.library.domain.*;
import com.library.service.LibraryService;

import java.util.List;
import java.util.Scanner;

public class Main {

	private static final Scanner scanner = new Scanner(System.in);
	private static final LibraryService service = new LibraryService();

	public static void main(String[] args) {
		System.out.println("=== Sistem upravljanja bibliotekom ===");
		while (true) {
			if (service.getLoggedInUser() == null) {
				showGuestMenu();
			} else {
				showUserMenu();
			}
		}
	}

	
	
	private static void showGuestMenu() {
		System.out.println("\n--- Glavni meni ---");
		System.out.println("1. Registracija clana");
		System.out.println("2. Prijava");
		System.out.println("3. Pretraga knjiga");
		System.out.println("0. Izlaz");
		System.out.print("Izbor: ");

		switch (readLine()) {
		case "1" -> registerMember();
		case "2" -> login();
		case "3" -> searchBooks();
		case "0" -> {
			System.out.println("Dovidjenja!");
			System.exit(0);
		}
		default -> System.out.println("Nepoznata opcija.");
		}
	}

	
	
	private static void showUserMenu() {
		User u = service.getLoggedInUser();
		boolean isLibrarian = u instanceof Librarian;
		System.out.println(
				"\n--- Prijavljeni: " + u.getUsername() + (isLibrarian ? " [Bibliotekar]" : " [Clan]") + " ---");
		System.out.println("1. Pretraga knjiga");
		System.out.println("2. Rezervisi knjigu");
		System.out.println("3. Otkaži rezervaciju");
		System.out.println("4. Moje aktivne pozajmice");
		if (isLibrarian) {
			System.out.println("--- Bibliotekar ---");
			System.out.println("5. Dodaj knjigu");
			System.out.println("6. Azuriraj knjigu");
			System.out.println("7. Kreiraj pozajmicu");
			System.out.println("8. Evidentiraj povrat");
			System.out.println("9. Sve aktivne pozajmice");
			System.out.println("10. Dodaj autora");
			System.out.println("11. Dodaj kategoriju");
		}
		System.out.println("0. Odjava");
		System.out.print("Izbor: ");

		switch (readLine()) {
		case "1" -> searchBooks();
		case "2" -> createReservation();
		case "3" -> cancelReservation();
		case "4" -> showMyLoans();
		case "5" -> {
			if (isLibrarian)
				addBook();
		}
		case "6" -> {
			if (isLibrarian)
				updateBook();
		}
		case "7" -> {
			if (isLibrarian)
				createLoan();
		}
		case "8" -> {
			if (isLibrarian)
				returnBook();
		}
		case "9" -> {
			if (isLibrarian)
				showAllActiveLoans();
		}
		case "10" -> {
			if (isLibrarian)
				addAuthor();
		}
		case "11" -> {
			if (isLibrarian)
				addCategory();
		}
		case "0" -> {
			service.logout();
			System.out.println("Odjavili ste se.");
		}
		default -> System.out.println("Nepoznata opcija.");
		}
	}

	private static void registerMember() {
		System.out.print("Korisnicko ime: ");
		String username = readLine();
		System.out.print("Lozinka: ");
		String password = readLine();
		System.out.print("Ime: ");
		String name = readLine();
		System.out.print("Email: ");
		String email = readLine();
		try {
			Member m = service.registerMember(username, password, name, email);
			System.out.println("Registracija uspesna! " + m);
		} catch (Exception e) {
			System.out.println("Greska: " + e.getMessage());
		}
	}

	private static void login() {
		System.out.print("Korisnicko ime: ");
		String username = readLine();
		System.out.print("Lozinka: ");
		String password = readLine();
		try {
			User u = service.login(username, password);
			System.out.println("Dobrodosli, " + u.getUsername() + "!");
		} catch (Exception e) {
			System.out.println("Greska: " + e.getMessage());
		}
	}

	private static void searchBooks() {
		System.out.print("Kljucna rec: ");
		String kw = readLine();
		List<Book> result = service.searchBooks(kw);
		if (result.isEmpty()) {
			System.out.println("Nema rezultata.");
		} else {
			result.forEach(b -> System.out.println("  " + b));
		}
	}

	private static void addBook() {
		System.out.print("Naslov: ");
		String title = readLine();
		System.out.print("ISBN: ");
		String isbn = readLine();
		System.out.print("Godina izdanja: ");
		int year = readInt();
		System.out.print("ID autora: ");
		int authorID = readInt();
		System.out.print("ID kategorije: ");
		int catID = readInt();
		try {
			Book b = service.addBook(title, isbn, year, authorID, catID);
			System.out.println("Knjiga dodata: " + b);
		} catch (Exception e) {
			System.out.println("Greska: " + e.getMessage());
		}
	}

	private static void updateBook() {
		System.out.print("ID knjige: ");
		int id = readInt();
		System.out.print("Novi naslov (Enter = bez promene): ");
		String title = readLine();
		System.out.print("Novi ISBN (Enter = bez promene): ");
		String isbn = readLine();
		try {
			service.updateBook(id, title.isBlank() ? null : title, isbn.isBlank() ? null : isbn);
			System.out.println("Knjiga azurirana.");
		} catch (Exception e) {
			System.out.println("Greska: " + e.getMessage());
		}
	}

	private static void createLoan() {
		System.out.print("ID korisnika: ");
		int userID = readInt();
		System.out.print("ID knjige: ");
		int bookID = readInt();
		try {
			Loan l = service.createLoan(userID, bookID);
			System.out.println("Pozajmica kreirana: " + l);
		} catch (Exception e) {
			System.out.println("Greska: " + e.getMessage());
		}
	}

	private static void returnBook() {
		System.out.print("ID pozajmice: ");
		int loanID = readInt();
		try {
			service.returnBook(loanID);
			System.out.println("Povrat evidentiran.");
		} catch (Exception e) {
			System.out.println("Greska: " + e.getMessage());
		}
	}

	private static void createReservation() {
		System.out.print("ID knjige: ");
		int bookID = readInt();
		try {
			Reservation r = service.createReservation(service.getLoggedInUser().getUserID(), bookID);
			System.out.println("Rezervacija kreirana: " + r);
		} catch (Exception e) {
			System.out.println("Greska: " + e.getMessage());
		}
	}

	private static void cancelReservation() {
		System.out.print("ID rezervacije: ");
		int resID = readInt();
		try {
			service.cancelReservation(resID);
			System.out.println("Rezervacija otkazana.");
		} catch (Exception e) {
			System.out.println("Greska: " + e.getMessage());
		}
	}

	private static void showMyLoans() {
		int myID = service.getLoggedInUser().getUserID();
		boolean found = false;
		for (Loan l : service.getActiveLoans()) {
			if (l.getUserID() == myID) {
				System.out.println("  " + l);
				found = true;
			}
		}
		if (!found)
			System.out.println("Nemate aktivnih pozajmica.");
	}

	private static void showAllActiveLoans() {
		List<Loan> active = service.getActiveLoans();
		if (active.isEmpty()) {
			System.out.println("Nema aktivnih pozajmica.");
		} else {
			active.forEach(l -> System.out.println("  " + l));
		}
	}

	private static void addAuthor() {
		System.out.print("Ime autora: ");
		String name = readLine();
		Author a = service.addAuthor(name);
		System.out.println("Autor dodat: " + a);
	}

	private static void addCategory() {
		System.out.print("Naziv kategorije: ");
		String name = readLine();
		Category c = service.addCategory(name);
		System.out.println("Kategorija dodata: " + c);
	}

	private static String readLine() {
		return scanner.nextLine().trim();
	}

	private static int readInt() {
		try {
			int val = Integer.parseInt(scanner.nextLine().trim());
			return val;
		} catch (NumberFormatException e) {
			return -1;
		}
	}
}
