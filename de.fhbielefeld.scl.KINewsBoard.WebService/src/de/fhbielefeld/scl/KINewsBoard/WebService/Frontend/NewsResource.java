package de.fhbielefeld.scl.KINewsBoard.WebService.Frontend;

import com.sun.istack.internal.NotNull;
import de.fhbielefeld.scl.KINewsBoard.BusinessLayer.NewsBoardService;
import de.fhbielefeld.scl.KINewsBoard.DataLayer.DataModels.NewsEntry;
import de.fhbielefeld.scl.KINewsBoard.DataLayer.DataModels.View;
import de.fhbielefeld.scl.KINewsBoard.WebService.Frontend.ViewModels.NewsEntryDetailVM;
import de.fhbielefeld.scl.KINewsBoard.WebService.Frontend.ViewModels.NewsEntryVM;
import de.fhbielefeld.scl.KINewsBoard.WebService.Frontend.ViewModels.ViewVM;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
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
    public List<NewsEntryVM> getPublicNewsEntries(
            @DefaultValue("1") @QueryParam("page") int page,
            @QueryParam("keyword") String keyword
    ) {
        List<NewsEntryVM> res = newsBoardService.getPublicNewsEntries(page, keyword)
                .stream()
                .map(NewsEntryVM::new)
                .collect(Collectors.toList());

        return res;
    }

    @GET
    @Path("/{newsId}")
    public NewsEntryDetailVM getNewsEntryDetails(
            @NotNull @PathParam("newsId") String newsId
    ) {
        NewsEntry newsEntry = newsBoardService.getNewsEntryDetails(newsId);
        NewsEntryDetailVM detail = new NewsEntryDetailVM(newsEntry);

        return detail;
    }

    @GET
    @Path("/findByView/{viewId}")
    public ViewVM getViewEntries(
            @PathParam("viewId") int viewId
    ) {
        View view = newsBoardService.getView(viewId);

        if (view == null)
            return null;

        List<NewsEntryVM> entries = newsBoardService.getViewNewsEntries(viewId)
                .stream()
                .map(NewsEntryVM::new)
                .collect(Collectors.toList());

        ViewVM res = new ViewVM(view, entries);

        return res;
    }
}
