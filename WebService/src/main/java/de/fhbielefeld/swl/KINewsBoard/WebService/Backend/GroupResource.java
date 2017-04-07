package de.fhbielefeld.swl.KINewsBoard.WebService.Backend;

import de.fhbielefeld.swl.KINewsBoard.BusinessLayer.AdminService;
import de.fhbielefeld.swl.KINewsBoard.WebService.Backend.ViewModels.GroupSetVM;

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
 * Die Klasse <i>GroupResource</i> stellt dem Backend den Zugriff auf Gruppen bereit.
 */
@Path("/group")
@Produces(MediaType.APPLICATION_JSON)
public class GroupResource {
    @EJB
    private AdminService adminService;

    /**
     * Ruft alle Gruppen ab.
     *
     * @return Liste aller Gruppen
     */
    @GET
    @RolesAllowed({})
    public List<GroupSetVM> get() {
        return adminService.getAllGroupSets().stream().map(GroupSetVM::new).collect(Collectors.toList());
    }

    /**
     * Erstellt die angegebene Gruppe.
     *
     * @param model Die zu erstellende Gruppe
     * @return Statuscode 200, wenn Anfrage erfolgreich bearbeitet wurde, <br>
     * Statuscode 400, wenn die Validierung der Gruppe fehlschlägt
     */
    @POST
    @RolesAllowed({})
    public Response create(@NotNull @Valid GroupSetVM model) {
        adminService.createGroupSet(model.getGroupSet());
        return Response.ok().build();
    }

    /**
     * Aktualisiert die angegebene Gruppe.
     *
     * @param model Die zu aktualisierende Gruppe
     * @return Statuscode 200, wenn Anfrage erfolgreich bearbeitet wurde, <br>
     * Statuscode 400, wenn die Validierung der Gruppe fehlschlägt
     */
    @PUT
    @RolesAllowed({})
    public Response update(@NotNull @Valid GroupSetVM model) {
        adminService.updateGroupSet(model.getGroupSet());
        return Response.ok().build();
    }

    /**
     * Löscht die Gruppe mit der angegebenen Id.
     *
     * @param groupSetId Die Id der zu löschenden Gruppe
     * @return Statuscode 200, wenn Anfrage erfolgreich bearbeitet wurde
     */
    @DELETE
    @Path("/{groupSetId}")
    @RolesAllowed({})
    public Response delete(@PathParam("groupSetId") int groupSetId) {
        adminService.deleteGroupSet(groupSetId);
        return Response.ok().build();
    }
}
