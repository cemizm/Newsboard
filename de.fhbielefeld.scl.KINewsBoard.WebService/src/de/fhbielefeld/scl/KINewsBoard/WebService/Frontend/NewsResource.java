package de.fhbielefeld.scl.KINewsBoard.WebService.Frontend;

import com.sun.istack.internal.NotNull;
import de.fhbielefeld.scl.KINewsBoard.BusinessLayer.NewsBoardService;
import de.fhbielefeld.scl.KINewsBoard.DataLayer.DataModels.NewsEntry;
import de.fhbielefeld.scl.KINewsBoard.DataLayer.DataModels.View;
import de.fhbielefeld.scl.KINewsBoard.WebService.Frontend.ViewModels.NewsEntryDetailVM;
import de.fhbielefeld.scl.KINewsBoard.WebService.Frontend.ViewModels.NewsEntryVM;
import de.fhbielefeld.scl.KINewsBoard.WebService.Frontend.ViewModels.ViewVM;
import de.fhbielefeld.scl.KINewsBoard.WebService.Shared.ViewModels.ErrorModel;

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
    public Response getPublicNewsEntries(
            @DefaultValue("1") @QueryParam("page") int page,
            @QueryParam("keyword") String keyword
    ) {
        List<NewsEntryVM> res = newsBoardService.getPublicNewsEntries(page, keyword)
                .stream()
                .map(NewsEntryVM::new)
                .collect(Collectors.toList());

        return Response.ok(res.toArray(new NewsEntryVM[0])).build();
    }

    @GET
    @Path("/{newsId}")
    public Response getNewsEntryDetails(
            @NotNull @PathParam("newsId") String newsId
    ) {
        NewsEntry newsEntry = null;
        try {


            newsEntry = newsBoardService.getNewsEntryDetails(newsId);

        } catch (IllegalArgumentException ex) {
            return Response.status(Response.Status.NOT_FOUND).entity(new ErrorModel(ex)).build();
        } catch (Exception ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new ErrorModel(ex)).build();
        }

        NewsEntryDetailVM detail = new NewsEntryDetailVM(newsEntry);

        return Response.ok(detail).build();
    }

    @POST
    @Path("/{newsId}/rate")
    public Response rateNewsEntry(
            @NotNull @PathParam("newsId") String newsId,
            @QueryParam("up") boolean up
    ) {
        NewsEntry newsEntry = null;
        try {
            newsEntry = newsBoardService.rateNewsEntry(newsId, up);

        } catch (IllegalArgumentException ex) {
            return Response.status(Response.Status.NOT_FOUND).entity(new ErrorModel(ex)).build();
        } catch (Exception ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new ErrorModel(ex)).build();
        }


        return Response.ok(new NewsEntryVM(newsEntry)).build();
    }

    @GET
    @Path("/findByView/{viewId}")
    public Response getViewEntries(
            @PathParam("viewId") int viewId
    ) {

        View view = null;
        try {
            view = newsBoardService.getView(viewId);
        } catch (IllegalArgumentException ex) {
            return Response.status(Response.Status.NOT_FOUND).entity(new ErrorModel(ex)).build();
        } catch (Exception ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new ErrorModel(ex)).build();
        }

        List<NewsEntryVM> entries = newsBoardService.getViewNewsEntries(viewId)
                .stream()
                .map(NewsEntryVM::new)
                .collect(Collectors.toList());

        ViewVM res = new ViewVM(view, entries);

        return Response.ok(res).build();
    }
}
