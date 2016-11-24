package de.fhbielefeld.scl.KINewsBoard.WebService.Backend;

import de.fhbielefeld.scl.KINewsBoard.BusinessLayer.AdminService;
import de.fhbielefeld.scl.KINewsBoard.WebService.Backend.ViewModels.AnalyzerVM;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by cem on 18.11.16.
 */
@Path("/analyzer")
@Produces(MediaType.APPLICATION_JSON)
public class AnalyzerResource {
    @EJB
    private AdminService adminService;

    @GET
    @Path("/")
    public List<AnalyzerVM> get() {
        return adminService.getAllAnalyzer().stream().map(AnalyzerVM::new).collect(Collectors.toList());
    }

    @POST
    @Path("/")
    public Response create(AnalyzerVM model) {
        adminService.createAnalyzer(model.getAnalyzer());
        return Response.ok().build();
    }

    @PUT
    @Path("/")
    public Response update(AnalyzerVM model) {
        adminService.updateAnalyzer(model.getAnalyzer());
        return Response.ok().build();
    }

    @DELETE
    @Path("/{analyzerId}")
    public Response delete(@PathParam("analyzerId") int analyzerId) {
        adminService.deleteAnalyzer(analyzerId);
        return Response.ok().build();
    }


}
