package de.fhbielefeld.scl.KINewsBoard.WebService.Backend;

import de.fhbielefeld.scl.KINewsBoard.BusinessLayer.AdminService;
import de.fhbielefeld.scl.KINewsBoard.WebService.Backend.ViewModels.GroupSetVM;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by cem on 18.11.16.
 */
@Path("/group")
@Produces(MediaType.APPLICATION_JSON)
public class GroupResource {
    @EJB
    private AdminService adminService;

    @GET
    @Path("/")
    @RolesAllowed({})
    public List<GroupSetVM> get() {
        return adminService.getAllGroupSets().stream().map(GroupSetVM::new).collect(Collectors.toList());
    }

    @POST
    @Path("/")
    @RolesAllowed({})
    public Response create(@NotNull GroupSetVM model) {
        adminService.createGroupSet(model.getGroupSet());
        return Response.ok().build();
    }

    @PUT
    @Path("/")
    @RolesAllowed({})
    public Response update(@NotNull GroupSetVM model) {
        adminService.updateGroupSet(model.getGroupSet());
        return Response.ok().build();
    }

    @DELETE
    @Path("/{groupSetId}")
    @RolesAllowed({})
    public Response delete(@PathParam("groupSetId") int groupSetId) {
        adminService.deleteGroupSet(groupSetId);
        return Response.ok().build();
    }
}
