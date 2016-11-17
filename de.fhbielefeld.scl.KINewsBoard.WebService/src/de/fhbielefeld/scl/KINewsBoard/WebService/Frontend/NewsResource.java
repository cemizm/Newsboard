package de.fhbielefeld.scl.KINewsBoard.WebService.Frontend;

import com.sun.istack.internal.NotNull;
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
    public List<NewsModel> getPublicNewsEntries(
            @DefaultValue("1") @QueryParam("page") int page,
            @QueryParam("keyword") String keyword
    ) throws Exception {
        return newsBoardService.getPublicNewsEntries(page, keyword);
    }

    @GET
    @Path("/{newsId}")
    public NewsModel getNewsEntryDetails(
            @NotNull @PathParam("newsId") String newsId
    ) throws Exception {
        return newsBoardService.getNewsEntryDetails(newsId);
    }

    @GET
    @Path("/findByView/{viewId}")
    public List<NewsModel> getViewEntries(
            @PathParam("viewId") int viewId,
            @DefaultValue("1") @QueryParam("page") int page
    ) throws Exception {
        return newsBoardService.getViewNewsEntries(viewId, page);
    }
}
