package de.fhbielefeld.swl.KINewsBoard.BusinessLayer;

import de.fhbielefeld.swl.KINewsBoard.BusinessLayer.Authentication2.UserWebService;
import de.fhbielefeld.swl.KINewsBoard.BusinessLayer.Authentication2.UserWebService_Service;
import de.fhbielefeld.swl.KINewsBoard.BusinessLayer.Models.User;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.StringReader;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;

/**
 * Die Klasse <i>AuthenticationService</i> dient der Authentifizierung im System.
 */
@Singleton
public class AuthenticationService {

    private Hashtable<String, User> users = new Hashtable<>();
    private final List<String> acceptedUserLogins = Arrays.asList("cbasoglu", "kschima", "cgips", "ffehring");
    private final int ADMIN_LEVEL = 3;


    /**
     * Initialisiert den Authenfizierungsservice.
     */
    @PostConstruct
    public void init() {
        User user = new User();
        user.setAuthtoken("f1ac2c84a417f043c08af24e25c232b1");
        user.setUsername("hwurst");
        user.setUserlevel("3");
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

        String res = s.loginUser(username, password);

        if (res == null || res.isEmpty())
            return null;

        res = res.replaceAll(",[\\n\\r\\s]+}", "}");

        JsonReader reader = Json.createReader(new StringReader(res));
        JsonObject object = reader.readObject();

        User user = null;
        if (object.containsKey("authtoken")) {
            user = new User();
            user.setAuthtoken(object.getString("authtoken"));
            user.setUsername(object.getString("username"));
            user.setUserlevel(object.getString("userlevel"));
            user.setFirstname(object.getString("firstname"));
            user.setLastname(object.getString("lastname"));
            user.setAddress(object.getString("address"));
            user.setEmail(object.getString("email"));
            user.setPhone(object.getString("phone"));
        }

        if (user == null)
            return null;

        if (Integer.parseInt(user.getUserlevel()) != ADMIN_LEVEL) {
            if (!acceptedUserLogins.contains(user.getUsername())) {
                return null; //TODO: Error message
            }
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
