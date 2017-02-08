package de.fhbielefeld.swl.KINewsBoard.WebService.Crawler.Filters;

import de.fhbielefeld.swl.KINewsBoard.BusinessLayer.NewsBoardService;
import de.fhbielefeld.swl.KINewsBoard.DataLayer.DataModels.Crawler;
import de.fhbielefeld.swl.KINewsBoard.WebService.Shared.ViewModels.ErrorModel;
import de.fhbielefeld.swl.KINewsBoard.WebService.Shared.utils.CustomPrincipal;

import javax.ejb.EJB;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Provider
public class TokenFilter implements ContainerRequestFilter {
    @EJB
    private NewsBoardService newsBoardService;
    @Context
    private ResourceInfo resourceInfo;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

        final MultivaluedMap<String, String> headers = requestContext.getHeaders();
        final List<String> token = headers.get("token");

        if (token == null || token.isEmpty()) {
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
                    .entity(new ErrorModel("Kein Token in Request vorhanden.")).build());
            return;
        }

        Crawler foundCrawler = newsBoardService.getCrawlerByToken(token.get(0));


        if (foundCrawler == null) {
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
                    .entity(new ErrorModel("Übergebener Token existiert nicht.")).build());
            return;
        }

        if (foundCrawler.isDisabled()) {
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
                    .entity(new ErrorModel("Crawler nicht aktiv")).build());
            return;
        }

        requestContext.setSecurityContext(new SecurityContext() {
            @Override
            public Principal getUserPrincipal() {
                return new CustomPrincipal<>(foundCrawler);
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
