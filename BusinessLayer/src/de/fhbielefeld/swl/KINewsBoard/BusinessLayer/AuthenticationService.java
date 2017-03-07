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
     * @param username Der Benutzername des Benuters, der angemeldet werden soll
     * @param password Das Passwort des Benutzers, der angemeldet werden soll
     * @return Der angemeldete Benutzer
     */
    public User login(String username, String password) {
        if (username == null || username.isEmpty())
            throw new IllegalArgumentException("Parameter 'user' darf nicht null oder leer sein!");

        if (password == null || password.isEmpty())
            throw new IllegalArgumentException("Parameter 'password' darf nicht null oder leer sein!");

        username = username.replaceAll("@[\\w.\\-]+\\.\\w+", "");

        UserWebService_Service svc = new UserWebService_Service();
        UserWebService s = svc.getUserWebServicePort();

        String loginRes = s.loginUser(username, password);

        if (loginRes == null || loginRes.isEmpty())
            return null;

        loginRes = loginRes.replaceAll(",[\\n\\r\\s]+}", "}");

        String rightRes = s.userHasRight(username, RES_PATH, LOGIN_ACTION);

        if (rightRes == null || rightRes.isEmpty())
            return null;

        JsonObject loginObj = Json.createReader(new StringReader(loginRes)).readObject();
        JsonObject rightObj = Json.createReader(new StringReader(rightRes)).readObject();

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

        if (rightObj.getString("hasRight").equals("true")) {
            user.setUserlevel(LOGIN_ACTION);
        }

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
