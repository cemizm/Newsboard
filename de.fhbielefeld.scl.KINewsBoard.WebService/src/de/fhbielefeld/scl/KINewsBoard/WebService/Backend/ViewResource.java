package de.fhbielefeld.scl.KINewsBoard.WebService.Backend;

import de.fhbielefeld.scl.KINewsBoard.BusinessLayer.AdminService;
import de.fhbielefeld.scl.KINewsBoard.WebService.Backend.ViewModels.ViewVM;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by cem on 18.11.16.
 */
@Path("/view")
@Produces(MediaType.APPLICATION_JSON)
public class ViewResource {
    @EJB
    private AdminService adminService;

    @GET
    @Path("/")
    @RolesAllowed({})
    public List<ViewVM> get() {
        return adminService.getAllView().stream().map(ViewVM::new).collect(Collectors.toList());
    }

    @POST
    @Path("/")
    @RolesAllowed({})
    public Response create(ViewVM model) {
        adminService.createView(model.getView());
        return Response.ok().build();
    }

    @PUT
    @Path("/")
    @RolesAllowed({})
    public Response update(ViewVM model) {
        adminService.updateView(model.getView());
        return Response.ok().build();
    }

    @DELETE
    @Path("/{viewId}")
    @RolesAllowed({})
    public Response delete(@PathParam("viewId") int viewId) {
        adminService.deleteView(viewId);
        return Response.ok().build();
    }
}
