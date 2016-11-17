package de.fhbielefeld.scl.KINewsBoard.WebService.Backend;

import de.fhbielefeld.scl.KINewsBoard.BusinessLayer.AdminService;
import de.fhbielefeld.scl.KINewsBoard.BusinessLayer.Models.CrawlerModel;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

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
    public List<CrawlerModel> get() {
        return adminService.getAllCrawler();
    }

    @POST
    @Path("/")
    public Response create(CrawlerModel model) {
        adminService.createCrawler(model);
        return Response.ok().build();
    }

    @PUT
    @Path("/")
    public Response update(CrawlerModel model) {
        adminService.updateCrawler(model);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{crawlerId}")
    public Response delete(@PathParam("crawlerId") int crawlerId) {
        adminService.deleteCrawler(crawlerId);
        return Response.ok().build();
    }


}
