package de.fhbielefeld.scl.KINewsBoard.WebService.Analyzer;

import de.fhbielefeld.scl.KINewsBoard.BusinessLayer.Models.AnalyzerResultModel;
import de.fhbielefeld.scl.KINewsBoard.BusinessLayer.Models.NewsModel;
import de.fhbielefeld.scl.KINewsBoard.BusinessLayer.NewsBoardService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by cem on 10.11.16.
 */

@Path("/news")
public class NewsResource {

    private NewsBoardService newsBoardService;

    public NewsResource() {
        newsBoardService = new NewsBoardService();
    }

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public List<NewsModel> getNewsEntries(
            @HeaderParam("token") String token
    ) throws Exception {
        return newsBoardService.getAnalyzerNewsEntries(token);
    }


    @POST
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public AnalyzerResultModel publish(
            @HeaderParam("token") String token,
            AnalyzerResultModel model
    ) throws Exception {
        return newsBoardService.publishAnalyzerResult(token, model);
    }
}
