package de.fhbielefeld.scl.KINewsBoard.WebService;

import de.fhbielefeld.scl.KINewsBoard.BusinessLayer.Models.AnalyzerResultModel;
import de.fhbielefeld.scl.KINewsBoard.BusinessLayer.Models.NewsModel;
import de.fhbielefeld.scl.KINewsBoard.BusinessLayer.NewsBoardManager;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.List;

/**
 * Created by cem on 10.11.16.
 */
@Path("/analyzer")
public class AnalyzerService {

    @GET
    @Path("/entries/{token}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<NewsModel> getEntries(@PathParam("token") String token) throws IOException {
        try (NewsBoardManager mngr = new NewsBoardManager()) {
            return mngr.getAnalyzerEntries(token);
        }
    }

    @POST
    @Path("/publish/{token}")
    @Produces(MediaType.APPLICATION_JSON)
    public AnalyzerResultModel publish(@PathParam("token") String token, AnalyzerResultModel model) throws IOException {
        try (NewsBoardManager mngr = new NewsBoardManager()) {
            return mngr.publishAnalyzerResult(token, model);
        }
    }
}
