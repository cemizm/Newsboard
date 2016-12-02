package de.fhbielefeld.scl.KINewsBoard.BusinessLayer;

import de.fhbielefeld.scl.KINewsBoard.BusinessLayer.Authentication.UserWebService;
import de.fhbielefeld.scl.KINewsBoard.BusinessLayer.Authentication.UserWebService_Service;
import de.fhbielefeld.scl.KINewsBoard.BusinessLayer.Models.User;

import javax.ejb.Singleton;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.StringReader;
import java.util.Hashtable;

/**
 * Created by cem on 01.12.16.
 */
@Singleton
public class AuthenticationService {

    private Hashtable<String, User> users = new Hashtable<>();

    public User login(String username, String password) {
        if (username == null || username.isEmpty())
            throw new IllegalArgumentException("Parameter 'user' darf nicht null oder leer sein!");

        if (password == null || password.isEmpty())
            throw new IllegalArgumentException("Parameter 'password' darf nicht null oder leer sein!");

        username = username.replaceAll("@[\\w.\\-]+\\.\\w+", "");
/*
        User user = new User();
        user.setAuthtoken("f1ac2c84a417f043c08af24e25c232b1");
        user.setUsername("hwurst");
        user.setUserlevel("3");
        user.setFirstname("Hans");
        user.setLastname("Wurst");
        user.setAddress("");
        user.setEmail("hwurst@fh-bielefeld.de");
        user.setPhone("");
*/

        UserWebService_Service svc = new UserWebService_Service();
        UserWebService s = svc.getUserWebServicePort();

        String res = s.loginUser(username, password);

        if (res == null || res.isEmpty())
            return null;

        res = res.replaceAll(",[\\n\\r\\s]+}","}");

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

        if (user != null)
            users.put(user.getAuthtoken(), user);

        return user;
    }

    public void logout(String token) {
        users.remove(token);
    }

    public User getUser(String token) {
        return users.get(token);
    }
}
