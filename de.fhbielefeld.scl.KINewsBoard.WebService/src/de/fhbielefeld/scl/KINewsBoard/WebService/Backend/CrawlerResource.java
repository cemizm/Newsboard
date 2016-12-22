package de.fhbielefeld.scl.KINewsBoard.WebService.Backend;

import de.fhbielefeld.scl.KINewsBoard.BusinessLayer.AdminService;
import de.fhbielefeld.scl.KINewsBoard.WebService.Backend.ViewModels.CrawlerVM;

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
 * Die Klasse <i>CrawlerResource</i> stellt dem Backend den Zugriff auf Crawler bereit.
 */
@Path("/crawler")
@Produces(MediaType.APPLICATION_JSON)
public class CrawlerResource {

    @EJB
    private AdminService adminService;

    @GET
    @RolesAllowed({})
    public List<CrawlerVM> get() {
        return adminService.getAllCrawler().stream().map(CrawlerVM::new).collect(Collectors.toList());
    }

    /**
     * Erstellt den angegeben Crawler.
     *
     * @param model Der zu erstellende Crawler
     * @return Statuscode 200, wenn Anfrage erfolgreich bearbeitet wurde, <br>
     * Statuscode 400, wenn die Validierung des Crawlers fehlschlägt
     */
    @POST
    @RolesAllowed({})
    public Response create(@NotNull @Valid CrawlerVM model) {
        adminService.createCrawler(model.getCrawler());
        return Response.ok().build();
    }

    /**
     * Aktualisiert den angegebenen Crawler.
     *
     * @param model Der zu aktualisierende Crawler
     * @return Statuscode 200, wenn Anfrage erfolgreich bearbeitet wurde, <br>
     * Statuscode 400, wenn die Validierung des Crawlers fehlschlägt
     */
    @PUT
    @RolesAllowed({})
    public Response update(@NotNull @Valid CrawlerVM model) {
        adminService.updateCrawler(model.getCrawler());
        return Response.ok().build();
    }

    /**
     * Löscht den Crawler mit der angegebenen Id.
     *
     * @param crawlerId Die Id des zu löschenden Crawlers
     * @return Statuscode 200, wenn Anfrage erfolgreich bearbeitet wurde
     */
    @DELETE
    @Path("/{crawlerId}")
    @RolesAllowed({})
    public Response delete(@PathParam("crawlerId") int crawlerId) {
        adminService.deleteCrawler(crawlerId);
        return Response.ok().build();
    }

}
