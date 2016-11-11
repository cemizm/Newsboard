package de.fhbielefeld.scl.KINewsBoard.WebService;

import de.fhbielefeld.scl.KINewsBoard.BusinessLayer.Models.NewsModel;
import de.fhbielefeld.scl.KINewsBoard.BusinessLayer.NewsBoardManager;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

/**
 * Created by cem on 10.11.16.
 */

@Path("/crawler")
public class CrawlerService {

    @POST
    @Path("/publish/{token}")
    @Produces(MediaType.APPLICATION_JSON)
    public NewsModel publishNewsEntry(@PathParam("token") String token, NewsModel model) throws IOException {
        try (NewsBoardManager mngr = new NewsBoardManager()) {
            return mngr.publishNewsEntry(token, model);
        }
    }
}
