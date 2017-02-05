package de.fhbielefeld.swl.KINewsBoard.WebService.Analyzer.Filters;


import de.fhbielefeld.swl.KINewsBoard.BusinessLayer.AdminService;
import de.fhbielefeld.swl.KINewsBoard.DataLayer.DataModels.Analyzer;
import de.fhbielefeld.swl.KINewsBoard.WebService.Shared.ViewModels.ErrorModel;

import javax.ejb.EJB;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

@Provider
public class TokenFilter implements ContainerRequestFilter {
    @EJB
    private AdminService adminService;
    @Context
    private ResourceInfo resourceInfo;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

        final MultivaluedMap<String, String> headers = requestContext.getHeaders();
        final List<String> token = headers.get("token");

        if (token == null || token.isEmpty()) {
            requestContext.abortWith(Response.status(Response.Status.BAD_REQUEST)
                    .entity(new ErrorModel("Kein Token in Request vorhanden.")).build());
            return;
        }

        final List<Analyzer> allAnalyzer = adminService.getAllAnalyzer();

        Analyzer foundAnalyzer = null;

        for (Analyzer currAnalyzer: allAnalyzer) {
            if (currAnalyzer.getToken().equals(token.get(0))) {
                foundAnalyzer = currAnalyzer;
            }
        }

        if (foundAnalyzer == null) {
            requestContext.abortWith(Response.status(Response.Status.BAD_REQUEST)
                    .entity(new ErrorModel("Übergebener Token existiert nicht.")).build());
            return;
        }

        if (foundAnalyzer.isDisabled()) {
            requestContext.abortWith(Response.status(Response.Status.BAD_REQUEST)
                    .entity(new ErrorModel("Analyzer nicht aktiv")).build());
            return;
        }
    }
}
