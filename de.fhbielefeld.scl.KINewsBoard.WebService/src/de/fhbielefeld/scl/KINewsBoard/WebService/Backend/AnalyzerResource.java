package de.fhbielefeld.scl.KINewsBoard.WebService.Backend;

import de.fhbielefeld.scl.KINewsBoard.BusinessLayer.AdminService;
import de.fhbielefeld.scl.KINewsBoard.WebService.Backend.ViewModels.AnalyzerVM;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Die Klasse <i>AnalyzerResource</i> stellt dem Backend den Zugriff auf Analyzer bereit.
 */
@Path("/analyzer")
@Produces(MediaType.APPLICATION_JSON)
public class AnalyzerResource {
    @EJB
    private AdminService adminService;

    @GET
    @RolesAllowed({})
    public List<AnalyzerVM> get() {
        return adminService.getAllAnalyzer().stream().map(AnalyzerVM::new).collect(Collectors.toList());
    }

    /**
     * Erstellt einen Analyzer.
     *
     * @param model Der zu erstellende Analyzer
     * @return Statuscode 200, wenn Anfrage erfolgreich bearbeitet wurde, <br>
     * Statuscode 400, wenn die Validierung des Analyzers fehlschlägt
     */
    @POST
    @RolesAllowed({})
    public Response create(@NotNull @Valid AnalyzerVM model) {
        adminService.createAnalyzer(model.getAnalyzer());
        return Response.ok().build();
    }

    /**
     * Aktualisiert den angegebenen Analyzer.
     *
     * @param model Der zu aktualisierende Analyzer
     * @return Statuscode 200, wenn Anfrage erfolgreich bearbeitet wurde, <br>
     * Statuscode 400, wenn die Validierung des Analyzers fehlschlägt
     */
    @PUT
    @RolesAllowed({})
    public Response update(@NotNull @Valid AnalyzerVM model) {
        adminService.updateAnalyzer(model.getAnalyzer());
        return Response.ok().build();
    }

    /**
     * Löscht den Analyzer mit der angegebenen Id.
     *
     * @param analyzerId Die Id des zu löschenden Analyzers
     * @return Statuscode 200, wenn Anfrage erfolgreich bearbeitet wurde
     */
    @DELETE
    @Path("/{analyzerId}")
    @RolesAllowed({})
    public Response delete(@PathParam("analyzerId") int analyzerId) {
        adminService.deleteAnalyzer(analyzerId);
        return Response.ok().build();
    }

}
