package de.fhbielefeld.swl.KINewsBoard.BusinessLayer.Authentication;

import de.fhbielefeld.swl.KINewsBoard.BusinessLayer.Models.User;

import javax.json.Json;
import javax.json.JsonObject;
import java.io.StringReader;


public class UserWebServiceHelper {

    private final String RES_PATH = "de.fhbielefeld.swl.KINewsBoard";
    private final String LOGIN_ACTION = "login";
    private UserWebService_Service uwsService = new UserWebService_Service();
    private UserWebService uwsPort = uwsService.getUserWebServicePort();


    public User login(String username, String password) {
        username = username.replaceAll("@[\\w.\\-]+\\.\\w+", "");
        String loginRes = uwsPort.loginUser(username, password);

        if (loginRes == null || loginRes.isEmpty())
            return null;

        loginRes = loginRes.replaceAll(",[\\n\\r\\s]+}", "}");

        JsonObject loginObj = Json.createReader(new StringReader(loginRes)).readObject();

        User user = null;

        if (loginObj.containsKey("authtoken")) {
            user = new de.fhbielefeld.swl.KINewsBoard.BusinessLayer.Models.User();
            user.setAuthtoken(loginObj.getString("authtoken"));
            user.setUsername(loginObj.getString("username"));
            user.setFirstname(loginObj.getString("firstname"));
            user.setLastname(loginObj.getString("lastname"));
            user.setEmail(loginObj.getString("email"));
        }

        return user;
    }
    /**
     * Überprüft ob der User die entsprechenden Rechte besitzt
     *
     * @param user  Der zu überprüfende User
     * @param action Der entsprechende Rechte-String
     * @return liefert "true" zurück wenn der User die Rechte besitzt, "false" wenn nicht
     */
    public boolean hasRight(de.fhbielefeld.swl.KINewsBoard.BusinessLayer.Models.User user, String action) {
        String rightRes = uwsPort.userHasRight(user.getUsername(), RES_PATH, action);

        if (rightRes == null || rightRes.isEmpty())
            return false;

        JsonObject rightObj = Json.createReader(new StringReader(rightRes)).readObject();

        if (rightObj.getString("hasRight").equals("true")) {
            return true;
        }

        return false;
    }
}
