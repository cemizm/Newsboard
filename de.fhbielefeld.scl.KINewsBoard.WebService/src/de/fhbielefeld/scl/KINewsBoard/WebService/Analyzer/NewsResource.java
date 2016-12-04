package de.fhbielefeld.scl.KINewsBoard.WebService.Analyzer;

import de.fhbielefeld.scl.KINewsBoard.BusinessLayer.NewsBoardService;
import de.fhbielefeld.scl.KINewsBoard.DataLayer.DataModels.NewsEntry;
import de.fhbielefeld.scl.KINewsBoard.WebService.Analyzer.ViewModels.AnalyzerResultVM;
import de.fhbielefeld.scl.KINewsBoard.WebService.Shared.ViewModels.ErrorModel;
import de.fhbielefeld.scl.KINewsBoard.WebService.Shared.ViewModels.NewsEntryBaseModel;

import javax.ejb.EJB;
import javax.naming.AuthenticationException;
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
    public Response getNewsEntries(
            @HeaderParam("token") String token
    ) {
        List<NewsEntry> entries;

        try {
            entries = newsBoardService.getAnalyzerNewsEntries(token);
        } catch (AuthenticationException ex) {
            return Response.status(Response.Status.UNAUTHORIZED).entity(new ErrorModel(ex)).build();
        } catch (IllegalArgumentException ex) {
            return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorModel(ex)).build();
        } catch (Exception ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new ErrorModel(ex)).build();
        }

        List<NewsEntryBaseModel> res = entries.stream().map(NewsEntryBaseModel::new).collect(Collectors.toList());

        return Response.ok(res.toArray(new NewsEntryBaseModel[0])).build();
    }

    @POST
    @Path("/")
    public Response publish(
            @HeaderParam("token") String token,
            AnalyzerResultVM model
    ) {

        try {
            newsBoardService.publishAnalyzerResult(token, model.getNewsId(), model.getAnalyzerResult());
        } catch (AuthenticationException ex) {
            return Response.status(Response.Status.UNAUTHORIZED).entity(new ErrorModel(ex)).build();
        } catch (IllegalArgumentException ex) {
            return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorModel(ex)).build();
        } catch (Exception ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new ErrorModel(ex)).build();
        }

        return Response.ok().build();
    }
}
