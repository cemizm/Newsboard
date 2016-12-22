package de.fhbielefeld.scl.KINewsBoard.WebService.Frontend;

import com.sun.istack.internal.NotNull;
import de.fhbielefeld.scl.KINewsBoard.BusinessLayer.NewsBoardService;
import de.fhbielefeld.scl.KINewsBoard.DataLayer.DataModels.NewsEntry;
import de.fhbielefeld.scl.KINewsBoard.DataLayer.DataModels.View;
import de.fhbielefeld.scl.KINewsBoard.WebService.Frontend.ViewModels.NewsEntryDetailVM;
import de.fhbielefeld.scl.KINewsBoard.WebService.Frontend.ViewModels.NewsEntryVM;
import de.fhbielefeld.scl.KINewsBoard.WebService.Frontend.ViewModels.ViewVM;

import javax.ejb.EJB;
import javax.validation.constraints.Min;
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

    /**
     * Ruft die bereits veröffentlichen Nachrichteneinträge ab.
     *
     * @param page    Die Seite, ab der die Ergebnisse geliefert werden sollen; default: 1, minimum: 1
     * @param keyword Der Suchbegriff, nach dem gefiltert werden soll
     * @param viewId  Die Id der Ansicht; default: 0
     * @return HTTP-Response, der aussagt, dass die Anfrage bearbeitet und das Ergebnis (Liste der Nachrichteneinträge) übertragen wurde
     */
    @GET
    public Response getPublicNewsEntries(
            @DefaultValue("1") @Min(1) @QueryParam("page") int page,
            @QueryParam("keyword") String keyword,
            @DefaultValue("0") @QueryParam("view") int viewId
    ) {

        View view = viewId > 0 ? newsBoardService.getView(viewId) : null;

        List<NewsEntryVM> res = newsBoardService.getNewsEntries(page, keyword, view)
                .stream()
                .map(n -> new NewsEntryVM(n, view))
                .collect(Collectors.toList());

        return Response.ok(res.toArray(new NewsEntryVM[0])).build();
    }

    /**
     * Ruft die Details zu einem Nachrichteneintrag ab.
     *
     * @param newsId Die Id des Nachrichteneintrages
     * @param viewId Die Id der Ansicht; default: 0
     * @return HTTP-Response, der aussagt, dass die Anfrage bearbeitet und das Ergebnis (der Nachrichteneintrag) übertragen wurde
     */
    @GET
    @Path("/{newsId}")
    public Response getNewsEntryDetails(
            @NotNull @PathParam("newsId") String newsId,
            @DefaultValue("0") @QueryParam("view") int viewId
    ) {

        NewsEntry newsEntry = newsBoardService.getNewsEntryDetails(newsId);

        View view = null;
        if (viewId != 0)
            view = newsBoardService.getView(viewId);

        NewsEntryDetailVM detail = new NewsEntryDetailVM(newsEntry, view);

        return Response.ok(detail).build();
    }

    /**
     * Bewertet einen Nachrichteneintrag anhand der angegebenen Id.
     *
     * @param newsId Die Id des Nachrichteneintrages
     * @param up     <i>true</i>, wenn der Nachrichteneintrag positiv bewertet werden soll
     * @return HTTP-Response, der aussagt, dass die Anfrage bearbeitet und das Ergebnis (der Nachrichteneintrag) übertragen wurde
     */
    @POST
    @Path("/{newsId}/rate")
    public Response rateNewsEntry(
            @NotNull @PathParam("newsId") String newsId,
            @QueryParam("up") boolean up
    ) {

        NewsEntry newsEntry = newsBoardService.rateNewsEntry(newsId, up);

        return Response.ok(new NewsEntryVM(newsEntry)).build();
    }

    /**
     * Ruft die Nachrichteneinträge zu der Ansicht mit der angegebenen Id ab.
     *
     * @param viewId Die Id der Ansicht
     * @return HTTP-Response, der aussagt, dass die Anfrage bearbeitet und das Ergebnis (Liste der Nachrichteneinträge, die der Ansicht zugeordnet sind) übertragen wurde
     */
    @GET
    @Path("/findByView/{viewId}")
    public Response getViewEntries(
            @NotNull @PathParam("viewId") int viewId
    ) {

        View view = newsBoardService.getView(viewId);

        List<NewsEntryVM> entries = newsBoardService.getNewsEntries(1, null, view)
                .stream()
                .map(n -> new NewsEntryVM(n, view))
                .collect(Collectors.toList());

        ViewVM res = new ViewVM(view, entries);

        return Response.ok(res).build();
    }
}
