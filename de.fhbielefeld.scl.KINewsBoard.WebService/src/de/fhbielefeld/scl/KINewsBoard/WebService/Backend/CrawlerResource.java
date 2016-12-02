package de.fhbielefeld.scl.KINewsBoard.WebService.Backend;

import de.fhbielefeld.scl.KINewsBoard.BusinessLayer.AdminService;
import de.fhbielefeld.scl.KINewsBoard.WebService.Backend.ViewModels.CrawlerVM;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by cem on 12.11.16.
 */
@Path("/crawler")
@Produces(MediaType.APPLICATION_JSON)
public class CrawlerResource {

    @EJB
    private AdminService adminService;

    @GET
    @Path("/")
    @RolesAllowed({})
    public List<CrawlerVM> get() {
        return adminService.getAllCrawler().stream().map(CrawlerVM::new).collect(Collectors.toList());
    }

    @POST
    @Path("/")
    @RolesAllowed({})
    public Response create(CrawlerVM model) {
        adminService.createCrawler(model.getCrawler());
        return Response.ok().build();
    }

    @PUT
    @Path("/")
    @RolesAllowed({})
    public Response update(CrawlerVM model) {
        adminService.updateCrawler(model.getCrawler());
        return Response.ok().build();
    }

    @DELETE
    @Path("/{crawlerId}")
    @RolesAllowed({})
    public Response delete(@PathParam("crawlerId") int crawlerId) {
        adminService.deleteCrawler(crawlerId);
        return Response.ok().build();
    }


}
