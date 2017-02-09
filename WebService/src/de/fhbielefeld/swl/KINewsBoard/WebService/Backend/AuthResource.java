package de.fhbielefeld.swl.KINewsBoard.WebService.Backend;

import com.sun.istack.internal.NotNull;
import de.fhbielefeld.swl.KINewsBoard.BusinessLayer.AuthenticationService;
import de.fhbielefeld.swl.KINewsBoard.BusinessLayer.Models.User;
import de.fhbielefeld.swl.KINewsBoard.WebService.Shared.ViewModels.ErrorModel;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.naming.AuthenticationException;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

/**
 * Die Klasse <i>AuthResource</i> stellt dem Backend den Zugriff auf die Authentifizierung bereit.
 */
@Path("/auth")
@Produces(MediaType.APPLICATION_JSON)
public class AuthResource {
    @EJB
    private AuthenticationService service;

    /**
     * Meldet den Benutzer anhand seiner Benutzdaten im System an.
     *
     * @param username Der Benutzername des Benutzers
     * @param password Das Benutzerpasswort des Benutzers
     * @return Statuscode 200, wenn Anfrage erfolgreich bearbeitet wurde, <br>
     * Statuscode 401, wenn die Benutzerdaten ungültig sind
     * @throws AuthenticationException Wenn die Benutzerdaten ungültig sind
     */
    @POST
    @Path("/login")
    public Response login(@HeaderParam("username") @NotNull String username,
                          @HeaderParam("password") @NotNull String password) throws AuthenticationException {

        User user = service.login(username, password);

        return Response.ok(user).build();
    }

    /**
     * Meldet den Benutzer vom System ab.
     *
     * @param securityContext Beinhaltet Informationen über den angemeldeten Benutzer
     * @return Statuscode 200, wenn Anfrage erfolgreich bearbeitet wurde
     */
    @POST
    @Path("/logout")
    @RolesAllowed({})
    public Response logout(@Context SecurityContext securityContext) {
        if (securityContext != null) {
            User u = (User) securityContext.getUserPrincipal();
            service.logout(u.getAuthtoken());
        }
        return Response.ok().build();
    }

    /**
     * Ruft Informationen über den angemeldeten Benutzer ab.
     *
     * @param securityContext Beinhaltet Informationen über den angemeldeten Benutzer
     * @return Statuscode 200, wenn Anfrage erfolgreich bearbeitet wurde
     */
    @GET
    @Path("/user")
    @RolesAllowed({})
    public Response get(@Context SecurityContext securityContext) {
        User u = null;
        if (securityContext != null && securityContext.getUserPrincipal() != null) {
            if (securityContext.getUserPrincipal() instanceof User)
                u = (User) securityContext.getUserPrincipal();
        }

        if (u == null)
            return Response.status(Response.Status.UNAUTHORIZED).entity(new ErrorModel("Authentifizierung fehlgeschlagen.")).build();

        return Response.ok(u).build();
    }
}
