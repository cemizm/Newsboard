package de.fhbielefeld.scl.KINewsBoard.WebService.Frontend;

import com.sun.istack.internal.NotNull;
import de.fhbielefeld.scl.KINewsBoard.BusinessLayer.Models.NewsEntryModel;
import de.fhbielefeld.scl.KINewsBoard.BusinessLayer.Models.ViewModel;
import de.fhbielefeld.scl.KINewsBoard.BusinessLayer.NewsBoardService;
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
        return newsBoardService.getPublicNewsEntries(page, keyword)
                .stream()
                .map(NewsEntryVM::new)
                .collect(Collectors.toList());
    }

    @GET
    @Path("/{newsId}")
    public NewsEntryDetailVM getNewsEntryDetails(
            @NotNull @PathParam("newsId") String newsId
    ) {
        NewsEntryModel model = newsBoardService.getNewsEntryDetails(newsId);
        return new NewsEntryDetailVM(model);
    }

    @GET
    @Path("/findByView/{viewId}")
    public ViewVM getViewEntries(
            @PathParam("viewId") int viewId
    ) {
        ViewModel model = newsBoardService.getView(viewId);

        if (model == null)
            return null;

        List<NewsEntryVM> entries = newsBoardService.getViewNewsEntries(viewId)
                .stream()
                .map(NewsEntryVM::new)
                .collect(Collectors.toList());

        return new ViewVM(model, entries);
    }
}
