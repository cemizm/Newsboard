package de.fhbielefeld.scl.KINewsBoard.WebService.Backend.Filters;

import de.fhbielefeld.scl.KINewsBoard.BusinessLayer.AuthenticationService;
import de.fhbielefeld.scl.KINewsBoard.BusinessLayer.Models.User;
import de.fhbielefeld.scl.KINewsBoard.WebService.Shared.ViewModels.ErrorModel;

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

/**
 * Created by cem on 01.12.16.
 */
@Provider
public class AuthenticationFilter implements ContainerRequestFilter {

    @EJB
    AuthenticationService service;

    @Context
    private ResourceInfo resourceInfo;

    private static final String AUTHENTICATION_SCHEME = "Basic";

    @Override
    public void filter(ContainerRequestContext requestContext) {
        Method method = resourceInfo.getResourceMethod();

        if (!method.isAnnotationPresent(PermitAll.class)) {
            if (method.isAnnotationPresent(DenyAll.class)) {
                requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
                        .entity(new ErrorModel("Der Zugriff ist nicht erlaubt.")).build());
                return;
            }

            if (method.isAnnotationPresent(RolesAllowed.class)) {

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
                        return false;
                    }

                    @Override
                    public boolean isSecure() {
                        return false;
                    }

                    @Override
                    public String getAuthenticationScheme() {
                        return null;
                    }
                });
            }
        }
    }
}