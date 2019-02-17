package de.fhbielefeld.swl.KINewsBoard.WebService.Backend.Filters;

import de.fhbielefeld.swl.KINewsBoard.BusinessLayer.AuthenticationService;
import de.fhbielefeld.swl.KINewsBoard.BusinessLayer.Models.User;
import de.fhbielefeld.swl.KINewsBoard.WebService.Shared.ViewModels.ErrorModel;

import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.*;
import javax.ws.rs.ext.Provider;
import java.lang.reflect.Method;
import java.security.Principal;
import java.util.List;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;

/**
 * Die Klasse <i>AuthenticationFilter</i> ist für die Filterung der Authentifizierung beim Ausführen eines Webservice-Aufrufes zuständig.
 */
@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {

    private static final String AUTHENTICATION_SCHEME = "Basic";
    @EJB
    AuthenticationService service;
    @Context
    private ResourceInfo resourceInfo;

    /**
     * Die Methode wird vor jedem Webservice-Aufruf ausgeführt, um die Authentifizierung zu filtern.
     *
     * @param requestContext Der Anfragekontext
     */
    @Override
    public void filter(ContainerRequestContext requestContext) {
        Method method = resourceInfo.getResourceMethod();


        if (method.isAnnotationPresent(PermitAll.class)) {
            final MultivaluedMap<String, String> headers = requestContext.getHeaders();
            final List<String> authorization = headers.get(HttpHeaders.AUTHORIZATION);

            if (authorization == null || authorization.isEmpty()) {
                requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
                        .entity(new ErrorModel("Der Zugriff ist nicht erlaubt.")).build());
                return;
            }

            final String token = authorization.get(0).replaceFirst(AUTHENTICATION_SCHEME + " ", "");

            User user = service.getUser(token);
            if (user == null) {
                requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
                        .entity(new ErrorModel("Der Zugriff ist nicht erlaubt.")).build());
                return;
            }

            requestContext.setSecurityContext(new SecurityContext() {
                @Override
                public Principal getUserPrincipal() {
                    return user;
                }

                @Override
                public boolean isUserInRole(String s) {
                    return true;
                }

                @Override
                public boolean isSecure() {
                    return true;
                }

                @Override
                public String getAuthenticationScheme() {
                    return AUTHENTICATION_SCHEME;
                }
            });
        }
    }
    
}