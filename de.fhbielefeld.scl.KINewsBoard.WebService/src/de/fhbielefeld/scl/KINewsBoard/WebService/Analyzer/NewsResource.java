package de.fhbielefeld.scl.KINewsBoard.WebService.Analyzer;

import de.fhbielefeld.scl.KINewsBoard.BusinessLayer.NewsBoardService;
import de.fhbielefeld.scl.KINewsBoard.DataLayer.DataModels.NewsEntry;
import de.fhbielefeld.scl.KINewsBoard.WebService.Analyzer.ViewModels.AnalyzerResultVM;
import de.fhbielefeld.scl.KINewsBoard.WebService.Shared.ViewModels.NewsEntryBaseModel;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

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
    public List<NewsEntryBaseModel> getNewsEntries(
            @HeaderParam("token") String token
    ) {
        List<NewsEntry> entries = newsBoardService.getAnalyzerNewsEntries(token);
        List<NewsEntryBaseModel> res = entries.stream().map(NewsEntryBaseModel::new).collect(Collectors.toList());
        return res;
    }


    @POST
    @Path("/")
    public Response publish(
            @HeaderParam("token") String token,
            AnalyzerResultVM model
    ) throws Exception {
        newsBoardService.publishAnalyzerResult(token, model.getNewsId(), model.getAnalyzerResult());

        return Response.ok().build();
    }
}
