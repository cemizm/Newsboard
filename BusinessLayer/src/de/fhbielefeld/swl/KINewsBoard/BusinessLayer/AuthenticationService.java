package de.fhbielefeld.swl.KINewsBoard.BusinessLayer;

import de.fhbielefeld.swl.KINewsBoard.BusinessLayer.Authentication.UserWebService;
import de.fhbielefeld.swl.KINewsBoard.BusinessLayer.Authentication.UserWebService_Service;
import de.fhbielefeld.swl.KINewsBoard.BusinessLayer.Models.User;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.json.Json;
import javax.json.JsonObject;
import java.io.StringReader;
import java.util.Hashtable;

/**
 * Die Klasse <i>AuthenticationService</i> dient der Authentifizierung im System.
 */
@Singleton
public class AuthenticationService {

    private Hashtable<String, User> users = new Hashtable<>();
    private final String RES_PATH = "de.fhbielefeld.swl.KINewsBoard";
    private final String LOGIN_ACTION = "login";
    private UserWebService_Service svc = new UserWebService_Service();
    private UserWebService s = svc.getUserWebServicePort();


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

        username = username.replaceAll("@[\\w.\\-]+\\.\\w+", "");
        String loginRes = s.loginUser(username, password);

        if (loginRes == null || loginRes.isEmpty())
            return null;

        loginRes = loginRes.replaceAll(",[\\n\\r\\s]+}", "}");

        JsonObject loginObj = Json.createReader(new StringReader(loginRes)).readObject();

        User user = null;
        if (loginObj.containsKey("authtoken")) {
            user = new User();
            user.setAuthtoken(loginObj.getString("authtoken"));
            user.setUsername(loginObj.getString("username"));
            user.setFirstname(loginObj.getString("firstname"));
            user.setLastname(loginObj.getString("lastname"));
            user.setEmail(loginObj.getString("email"));
        }

        if (user == null)
            return null;

        if (!hasRight(user, LOGIN_ACTION))
            return null;

        users.put(user.getAuthtoken(), user);

        return user;
    }

    /**
     * Überprüft ob der User die entsprechenden Rechte besitzt
     *
     * @param user  Der zu überprüfende User
     * @param action Der entsprechende Rechte-String
     * @return liefert "true" zurück wenn der User die Rechte besitzt, "false" wenn nicht
     */
    public boolean hasRight(User user, String action) {
        String rightRes = s.userHasRight(user.getUsername(), RES_PATH, action);

        if (rightRes == null || rightRes.isEmpty())
            return false;

        JsonObject rightObj = Json.createReader(new StringReader(rightRes)).readObject();

        if (rightObj.getString("hasRight").equals("true")) {
            return true;
        }

        return false;
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
