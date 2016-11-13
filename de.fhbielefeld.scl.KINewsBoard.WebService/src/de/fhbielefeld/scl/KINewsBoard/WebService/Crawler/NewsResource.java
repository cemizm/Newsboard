package de.fhbielefeld.scl.KINewsBoard.WebService.Crawler;

import de.fhbielefeld.scl.KINewsBoard.BusinessLayer.Models.NewsModel;
import de.fhbielefeld.scl.KINewsBoard.BusinessLayer.NewsBoardService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

/**
 * Created by cem on 10.11.16.
 */

@Path("/news")
public class NewsResource {
    private NewsBoardService newsBoardService;

    public NewsResource() {
        newsBoardService = new NewsBoardService();
    }

    @POST
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public NewsModel publish(
            @HeaderParam("token") String token,
            NewsModel model
    ) throws IOException {
        return newsBoardService.publishNewsEntry(token, model);
    }
}
