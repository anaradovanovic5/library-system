package com.library.main;

import com.library.domain.*;
import com.library.service.LibraryService;

import java.util.List;
import java.util.Scanner;

public class Main {

    private static LibraryService service = new LibraryService();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            printMenu();
            String choice = scanner.nextLine().trim();
            try {
                switch (choice) {
                    case "1": registerMember(); break;
                    case "2": registerLibrarian(); break;
                    case "3": login(); break;
                    case "4": addBook(); break;
                    case "5": searchBooks(); break;
                    case "6": createLoan(); break;
                    case "7": returnBook(); break;
                    case "8": createReservation(); break;
                    case "9": listActiveLoans(); break;
                    case "0": running = false; break;
                    default: System.out.println("Nepoznata opcija.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Greska: " + e.getMessage());
            }
        }
        System.out.println("Dovidjenja!");
    }

    private static void printMenu() {
        System.out.println("\n=== BIBLIOTEKA - MENI ===");
        System.out.println("1. Registruj clana");
        System.out.println("2. Registruj bibliotekara");
        System.out.println("3. Prijava");
        System.out.println("4. Dodaj knjigu (potreban autor i kategorija)");
        System.out.println("5. Pretrazi knjige");
        System.out.println("6. Kreiraj pozajmicu");
        System.out.println("7. Vrati knjigu");
        System.out.println("8. Kreiraj rezervaciju");
        System.out.println("9. Prikazi aktivne pozajmice");
        System.out.println("0. Izlaz");
        System.out.print("Izbor: ");
    }

    private static void registerMember() {
        System.out.print("Korisnicko ime: ");
        String username = scanner.nextLine();
        System.out.print("Lozinka: ");
        String password = scanner.nextLine();
        System.out.print("Ime i prezime: ");
        String name = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        Member m = service.registerMember(username, password, name, email);
        System.out.println("Registrovan: " + m);
    }

    private static void registerLibrarian() {
        System.out.print("Korisnicko ime: ");
        String username = scanner.nextLine();
        System.out.print("Lozinka: ");
        String password = scanner.nextLine();
        System.out.print("ID zaposlenog: ");
        int employeeID = Integer.parseInt(scanner.nextLine());
        Librarian l = service.registerLibrarian(username, password, employeeID);
        System.out.println("Registrovan: " + l);
    }

    private static void login() {
        System.out.print("Korisnicko ime: ");
        String username = scanner.nextLine();
        System.out.print("Lozinka: ");
        String password = scanner.nextLine();
        User u = service.login(username, password);
        System.out.println("Prijavljeni ste kao: " + u);
    }

    private static void addBook() {
        System.out.print("Naslov: ");
        String title = scanner.nextLine();
        System.out.print("ISBN: ");
        String isbn = scanner.nextLine();
        System.out.print("Godina izdanja: ");
        int year = Integer.parseInt(scanner.nextLine());
        System.out.print("ID autora (0 za novog autora): ");
        int authorID = Integer.parseInt(scanner.nextLine());
        if (authorID == 0) {
            System.out.print("Ime novog autora: ");
            Author a = service.addAuthor(scanner.nextLine());
            authorID = a.getAuthorID();
        }
        System.out.print("ID kategorije (0 za novu kategoriju): ");
        int categoryID = Integer.parseInt(scanner.nextLine());
        if (categoryID == 0) {
            System.out.print("Naziv nove kategorije: ");
            Category c = service.addCategory(scanner.nextLine());
            categoryID = c.getCategoryID();
        }
        Book b = service.addBook(title, isbn, year, authorID, categoryID);
        System.out.println("Dodata knjiga: " + b);
    }

    private static void searchBooks() {
        System.out.print("Kljucna rec: ");
        String keyword = scanner.nextLine();
        List<Book> found = service.searchBooks(keyword);
        if (found.isEmpty()) {
            System.out.println("Nema rezultata.");
        } else {
            found.forEach(System.out::println);
        }
    }

    private static void createLoan() {
        System.out.print("ID korisnika: ");
        int userID = Integer.parseInt(scanner.nextLine());
        System.out.print("ID knjige: ");
        int bookID = Integer.parseInt(scanner.nextLine());
        Loan loan = service.createLoan(userID, bookID);
        System.out.println("Kreirana pozajmica: " + loan);
    }

    private static void returnBook() {
        System.out.print("ID pozajmice: ");
        int loanID = Integer.parseInt(scanner.nextLine());
        service.returnBook(loanID);
        System.out.println("Knjiga vracena.");
    }

    private static void createReservation() {
        System.out.print("ID korisnika: ");
        int userID = Integer.parseInt(scanner.nextLine());
        System.out.print("ID knjige: ");
        int bookID = Integer.parseInt(scanner.nextLine());
        Reservation r = service.createReservation(userID, bookID);
        System.out.println("Kreirana rezervacija: " + r);
    }

    private static void listActiveLoans() {
        List<Loan> active = service.getActiveLoans();
        if (active.isEmpty()) {
            System.out.println("Nema aktivnih pozajmica.");
        } else {
            active.forEach(System.out::println);
        }
    }
}