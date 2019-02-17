package de.fhbielefeld.swl.KINewsBoard.WebService.Backend;

import de.fhbielefeld.swl.KINewsBoard.BusinessLayer.AdminService;
import de.fhbielefeld.swl.KINewsBoard.WebService.Backend.ViewModels.ViewVM;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Die Klasse <i>ViewResource</i> stellt dem Backend den Zugriff auf Ansichten bereit.
 */
@Path("/view")
@Produces(MediaType.APPLICATION_JSON)
public class ViewResource {
    @EJB
    private AdminService adminService;

    /**
     * Ruft alle Ansichten ab.
     *
     * @return Liste aller Ansichten
     */
    @GET
    @PermitAll
    public List<ViewVM> get() {
        return adminService.getAllView().stream().map(ViewVM::new).collect(Collectors.toList());
    }

    /**
     * Erstellt die angegebene Ansicht.
     *
     * @param model Die zu erstellende Ansicht
     * @return Statuscode 200, wenn Anfrage erfolgreich bearbeitet wurde, <br>
     * Statuscode 400, wenn die Validierung der Ansicht fehlschlägt
     */
    @POST
    @PermitAll
    public Response create(@NotNull @Valid ViewVM model) {
        adminService.createView(model.getView());
        return Response.ok().build();
    }

    /**
     * Aktualisiert die angegebene Ansicht.
     *
     * @param model Die zu aktualisierende Ansicht
     * @return Statuscode 200, wenn Anfrage erfolgreich bearbeitet wurde, <br>
     * Statuscode 400, wenn die Validierung der Ansicht fehlschlägt
     */
    @PUT
    @PermitAll
    public Response update(@NotNull @Valid ViewVM model) {
        adminService.updateView(model.getView());
        return Response.ok().build();
    }

    /**
     * Löscht die Ansicht mit der angegebenen Id.
     *
     * @param viewId Die Id der zu löschenden Ansicht
     * @return Statuscode 200, wenn Anfrage erfolgreich bearbeitet wurde
     */
    @DELETE
    @Path("/{viewId}")
    @PermitAll
    public Response delete(@PathParam("viewId") int viewId) {
        adminService.deleteView(viewId);
        return Response.ok().build();
    }
}
