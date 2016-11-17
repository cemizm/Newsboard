package de.fhbielefeld.scl.KINewsBoard.WebService.Crawler;

import de.fhbielefeld.scl.KINewsBoard.BusinessLayer.Models.NewsEntryModel;
import de.fhbielefeld.scl.KINewsBoard.BusinessLayer.NewsBoardService;

import javax.ejb.EJB;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

/**
 * Created by cem on 10.11.16.
 */

@Path("/news")
@Produces(MediaType.APPLICATION_JSON)
public class NewsResource {

    @EJB
    private NewsBoardService newsBoardService;

    @POST
    @Path("/")
    public NewsEntryModel publish(
            @HeaderParam("token") String token,
            NewsEntryModel model
    ) throws IOException {
        model = newsBoardService.publishNewsEntry(token, model);
        return model;
    }
}
