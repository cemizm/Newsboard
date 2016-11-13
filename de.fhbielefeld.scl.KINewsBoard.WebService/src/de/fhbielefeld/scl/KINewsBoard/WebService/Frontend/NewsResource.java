package de.fhbielefeld.scl.KINewsBoard.WebService.Frontend;

import com.sun.istack.internal.NotNull;
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
    public List<NewsModel> getPublicNewsEntries(
            @DefaultValue("1") @QueryParam("page") int page,
            @QueryParam("keyword") String keyword
    ) throws Exception {
        return newsBoardService.getPublicNewsEntries(page, keyword);
    }

    @GET
    @Path("/{newsId}")
    @Produces(MediaType.APPLICATION_JSON)
    public NewsModel getNewsEntryDetails(
            @NotNull @PathParam("newsId") String newsId
    ) throws Exception {
        return newsBoardService.getNewsEntryDetails(newsId);
    }

    @GET
    @Path("/findByView/{viewId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<NewsModel> getViewEntries(
            @PathParam("viewId") int viewId,
            @DefaultValue("1") @QueryParam("page") int page
    ) throws Exception {
        return newsBoardService.getViewNewsEntries(viewId, page);
    }
}
