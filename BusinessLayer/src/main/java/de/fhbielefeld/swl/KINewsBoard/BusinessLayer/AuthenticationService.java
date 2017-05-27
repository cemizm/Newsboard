package de.fhbielefeld.swl.KINewsBoard.BusinessLayer;


import de.fhbielefeld.swl.KINewsBoard.BusinessLayer.Authentication.UserWebServiceHelper;
import de.fhbielefeld.swl.KINewsBoard.BusinessLayer.Models.User;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import java.util.Hashtable;

/**
 * Die Klasse <i>AuthenticationService</i> dient der Authentifizierung im System.
 */
@Singleton
public class AuthenticationService {

    private Hashtable<String, User> users = new Hashtable<>();
    private final String RES_PATH = "de.fhbielefeld.swl.KINewsBoard";
    private final String LOGIN_ACTION = "login";


    /**
     * Initialisiert den Authenfizierungsservice.
     */
    @PostConstruct
    public void init() {

        User user = new User();
        user.setAuthtoken("f1ac2c84a417f043c08af24e25c232b1");
        user.setUsername("hwurst");
        user.setUserlevel(LOGIN_ACTION);
        user.setFirstname("Hans");
        user.setLastname("Wurst");
        user.setAddress("");
        user.setEmail("hwurst@fh-bielefeld.de");
        user.setPhone("");

        users.put(user.getAuthtoken(), user);
    }

    /**
     * Meldet den Benutzer anhand Benutzername und Passwort an.
     *
     * @param username Der Benutzername des Benutzers, der angemeldet werden soll
     * @param password Das Passwort des Benutzers, der angemeldet werden soll
     * @return Der angemeldete Benutzer
     */
    public User login(String username, String password) {
        if (username == null || username.isEmpty())
            throw new IllegalArgumentException("Parameter 'user' darf nicht null oder leer sein!");

        if (password == null || password.isEmpty())
            throw new IllegalArgumentException("Parameter 'password' darf nicht null oder leer sein!");

        UserWebServiceHelper userWebServiceHelper = new UserWebServiceHelper();

        User user = null;
        user = userWebServiceHelper.login(username, password);

        if (user == null)
            throw new IllegalArgumentException("User nicht im System vorhanden oder Passwort falsch.");

        if (!userWebServiceHelper.hasRight(user, LOGIN_ACTION))
            throw new IllegalArgumentException("User im System vorhanden, hat aber nicht die erforderlichen Zugriffsrechte. (Ressource: login)");

        users.put(user.getAuthtoken(), user);

        return user;
    }


    /**
     * Meldet einen Benutzer anhand des Authentifizierungstoken ab.
     *
     * @param token Der Token zur Authentifizierung
     */
    public void logout(String token) {
        users.remove(token);
    }

    /**
     * Ruft den Benutzer anhand des Authentifizierungstoken ab.
     *
     * @param token Der Token zur Authentifizierung
     * @return Der Benutzer mit dem entsprechenden Authentifizierungstoken
     */
    public User getUser(String token) {
        return users.get(token);
    }
}


