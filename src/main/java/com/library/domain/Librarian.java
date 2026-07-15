package com.library.domain;

/**
 * Predstavlja bibliotekara u sistemu biblioteke.
 *
 * <p>
 * Bibliotekar nasledjuje klasu {@link User} i ima administrativne privilegije.
 * Moze da dodaje i azurira knjige, kreira pozajmice i upravlja rezervacijama.
 * </p>
 *
 * @author Ana Radovanovic
 * @version 1.0.0
 * @see User
 */
public class Librarian extends User {

    /**
     * Jedinstveni identifikator bibliotekara u sistemu.
     */
    private int librarianID;

    /**
     * Broj zaposlenog bibliotekara u instituciji.
     */
    private int employeeID;

    /**
     * Podrazumevani konstruktor bez argumenata. Neophodan za deserijalizaciju putem Jackson biblioteke.
     */
    public Librarian() {
    }

    /**
     * Kreira novog bibliotekara sa svim potrebnim podacima.
     *
     * @param userID                    jedinstveni identifikator korisnika
     * @param username                  korisnicko ime za prijavu
     * @param password                  lozinka za prijavu
     * @param librarianID               identifikator bibliotekara
     * @param employeeID                broj zaposlenog
     * @throws IllegalArgumentException ako neki od parametara nije valjan
     */
    public Librarian(int userID, String username, String password, int librarianID, int employeeID) {
        super(userID, username, password);
        setLibrarianID(librarianID);
        setEmployeeID(employeeID);
    }

    /**
     * Vraca identifikator bibliotekara.
     *
     * @return identifikator bibliotekara
     */
    public int getLibrarianID() {
        return librarianID;
    }

    /**
     * Vraca broj zaposlenog.
     *
     * @return broj zaposlenog
     */
    public int getEmployeeID() {
        return employeeID;
    }

    /**
     * Postavlja identifikator bibliotekara.
     *
     * @param librarianID               novi identifikator (mora biti pozitivan broj)
     * @throws IllegalArgumentException ako je librarianID manji ili jednak nuli
     */
    public void setLibrarianID(int librarianID) {
        if (librarianID <= 0) {
            throw new IllegalArgumentException("ID bibliotekara mora biti pozitivan broj.");
        }
        this.librarianID = librarianID;
    }

    /**
     * Postavlja broj zaposlenog.
     *
     * @param employeeID                novi broj zaposlenog (mora biti pozitivan broj)
     * @throws IllegalArgumentException ako je employeeID manji ili jednak nuli
     */
    public void setEmployeeID(int employeeID) {
        if (employeeID <= 0) {
            throw new IllegalArgumentException("Broj zaposlenog mora biti pozitivan broj.");
        }
        this.employeeID = employeeID;
    }

    /**
     * Vraca tekstualnu reprezentaciju bibliotekara.
     *
     * @return string sa identifikatorom i brojem zaposlenog
     */
    @Override
    public String toString() {
        return "Librarian{id=" + getUserID() + ", employeeID=" + employeeID + "}";
    }
}