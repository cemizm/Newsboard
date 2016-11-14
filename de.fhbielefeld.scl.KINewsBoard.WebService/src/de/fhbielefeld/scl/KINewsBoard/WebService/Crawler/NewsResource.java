package de.fhbielefeld.scl.KINewsBoard.WebService.Crawler;

import de.fhbielefeld.scl.KINewsBoard.BusinessLayer.Models.NewsModel;
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
public class NewsResource {

    @EJB
    private NewsBoardService newsBoardService;

    @POST
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public NewsModel publish(
            @HeaderParam("token") String token,
            NewsModel model
    ) throws IOException {
        model = newsBoardService.publishNewsEntry(token, model);
        return model;
    }
}
