package de.fhbielefeld.scl.KINewsBoard.BusinessLayer.Models;

import javax.security.auth.Subject;
import java.security.Principal;

/**
 * Created by cem on 01.12.16.
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


    public String getAuthtoken() {
        return authtoken;
    }

    public void setAuthtoken(String authtoken) {
        this.authtoken = authtoken;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserlevel() {
        return userlevel;
    }

    public void setUserlevel(String userlevel) {
        this.userlevel = userlevel;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String getName() {
        return getUsername();
    }

    @Override
    public boolean implies(Subject subject) {
        return false;
    }
}
