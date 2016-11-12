package de.fhbielefeld.scl.KINewsBoard.WebService.Analyzer;

import de.fhbielefeld.scl.KINewsBoard.BusinessLayer.Models.NewsModel;
import de.fhbielefeld.scl.KINewsBoard.BusinessLayer.NewsBoardManager;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.List;

/**
 * Created by cem on 10.11.16.
 */

@Path("/news")
public class NewsResource {

    @GET
    @Path("/{token}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<NewsModel> getNewsEntries(@PathParam("token") String token) throws IOException {
        try (NewsBoardManager mngr = new NewsBoardManager()) {
            return mngr.getAnalyzerNewsEntries(token);
        }
    }
}
