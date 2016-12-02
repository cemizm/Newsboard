package de.fhbielefeld.scl.KINewsBoard.WebService.Backend;

import com.sun.istack.internal.NotNull;
import de.fhbielefeld.scl.KINewsBoard.BusinessLayer.AuthenticationService;
import de.fhbielefeld.scl.KINewsBoard.BusinessLayer.Models.User;
import de.fhbielefeld.scl.KINewsBoard.WebService.Shared.ViewModels.ErrorModel;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

/**
 * Created by cem on 01.12.16.
 */
@Path("/auth")
@Produces(MediaType.APPLICATION_JSON)
public class AuthResource {
    @EJB
    private AuthenticationService service;

    @POST
    @Path("/login")
    public Response login(@HeaderParam("username") @NotNull String username,
                          @HeaderParam("password") @NotNull String password) {
        User user = null;
        try {
            user = service.login(username, password);
        } catch (Exception ex) {
            return Response.status(Response.Status.UNAUTHORIZED).entity(new ErrorModel(ex)).build();
        }

        if (user == null)
            return Response.status(Response.Status.UNAUTHORIZED).entity(new ErrorModel("Authentifizierung fehlgeschlagen.")).build();
        else
            return Response.ok(user).build();
    }

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

    @GET
    @Path("/user")
    @RolesAllowed({})
    public Response get(@Context SecurityContext securityContext) {
        User u = null;
        if (securityContext != null && securityContext.getUserPrincipal() != null) {
            if(securityContext.getUserPrincipal() instanceof User)
                u = (User) securityContext.getUserPrincipal();
        }

        if(u == null)
            return Response.status(Response.Status.UNAUTHORIZED).entity(new ErrorModel("Authentifizierung fehlgeschlagen.")).build();

        return Response.ok(u).build();
    }
}
