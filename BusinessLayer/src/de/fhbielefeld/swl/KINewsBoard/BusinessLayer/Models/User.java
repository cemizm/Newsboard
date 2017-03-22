package de.fhbielefeld.swl.KINewsBoard.BusinessLayer.Models;

import javax.security.auth.Subject;
import java.security.Principal;

/**
 * Die Klasse <i>User</i> hat Zugriff auf die Daten eines Benutzers.
 */
public class User implements Principal {
    private String authtoken;
    private String username;
    private String userlevel;
    private String firstname;
    private String lastname;
    private String address;
    private String email;
    private String phone;

    public User() {
    }

    /**
     * Ruft den Authentufizierungsttoken des Benutzers ab.
     *
     * @return Der Authentufizierungsttoken des Benutzers
     */
    public String getAuthtoken() {
        return authtoken;
    }

    /**
     * Legt den Authentufizierungsttoken des Benutzers fest.
     *
     * @param authtoken Der Authentufizierungsttoken des Benutzers, der festgelegt werden soll
     */
    public void setAuthtoken(String authtoken) {
        this.authtoken = authtoken;
    }

    /**
     * Ruft den Benutzernamen des Benutzers ab.
     *
     * @return Der Benutzername des Benutzers
     */
    public String getUsername() {
        return username;
    }

    /**
     * Legt den Benutzernamen des Benutzers fest.
     *
     * @param username Der Benutzername, der festgelegt werden soll
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Ruft den Rechtelevel des Benutzers ab.
     *
     * @return Der Rechtelevel des Benutzers, "login" erlaubt das einloggen in das System
     */
    public String getUserlevel() {
        return userlevel;
    }

    /**
     * Legt das Rechtelevel des Benutzers fest.
     *
     * @param userlevel Das Rechtelevel, das festgelegt werden soll, wobei 3 Adminrechten und 1 Rechte eines normalen Benutzers entsprechen
     */
    public void setUserlevel(String userlevel) {
        this.userlevel = userlevel;
    }

    /**
     * Ruft den Vornamen des Benutzers ab.
     *
     * @return Der Vorname des Benutzers
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * Legt den Vornamen des Benutzers fest.
     *
     * @param firstname Der Vorname, der festgelegt werden soll
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     * Ruft den Nachnamen des Benutzers ab.
     *
     * @return Der Nachname des Benutzers
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * Legt den Nachnamen des Benutzers fest.
     *
     * @param lastname Der Nachname, der festgelegt werden soll
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * Ruft die Anschrift des Benutzers ab.
     *
     * @return Die Anschrift des Benutzers
     */
    public String getAddress() {
        return address;
    }

    /**
     * Legt die Anschrift des Benutzers fest.
     *
     * @param address Die Anschrift, die festgelegt werden soll
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Ruft die Email-Adresse des Benutzers ab.
     *
     * @return Die Email-Adresse des Benutzers
     */
    public String getEmail() {
        return email;
    }

    /**
     * Legt die Email-Adresse des Benutzers
     *
     * @param email Die Email-Adresse, die festgelegt werden soll
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Ruft die Telefonnummer des Benutzer ab.
     *
     * @return Die Telefonnummer des Benutzers
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Legt die Telefonnummer des Benutzers fest.
     *
     * @param phone Die Nummer, die festgelegt werden soll
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Ruft den Benutzernamen des Benutzers ab.
     *
     * @return Der Benutzername des Benutzers
     */
    @Override
    public String getName() {
        return getUsername();
    }

    /**
     * Nicht implementiert.
     */
    @Override
    public boolean implies(Subject subject) {
        return false;
    }
}
