package de.fhbielefeld.scl.KINewsBoard.WebService.Analyzer;

import de.fhbielefeld.scl.KINewsBoard.BusinessLayer.Models.AnalyzerResultModel;
import de.fhbielefeld.scl.KINewsBoard.BusinessLayer.Models.NewsModel;
import de.fhbielefeld.scl.KINewsBoard.BusinessLayer.NewsBoardService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by cem on 10.11.16.
 */

@Path("/news")
@Produces(MediaType.APPLICATION_JSON)
public class NewsResource {

    @EJB
    private NewsBoardService newsBoardService;

    @GET
    @Path("/")
    public List<NewsModel> getNewsEntries(
            @HeaderParam("token") String token
    ) throws Exception {
        return newsBoardService.getAnalyzerNewsEntries(token);
    }


    @POST
    @Path("/")
    public AnalyzerResultModel publish(
            @HeaderParam("token") String token,
            AnalyzerResultModel model
    ) throws Exception {
        return newsBoardService.publishAnalyzerResult(token, model);
    }
}
