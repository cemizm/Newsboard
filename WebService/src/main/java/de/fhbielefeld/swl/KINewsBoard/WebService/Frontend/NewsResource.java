package de.fhbielefeld.swl.KINewsBoard.WebService.Frontend;

import de.fhbielefeld.swl.KINewsBoard.BusinessLayer.NewsBoardService;
import de.fhbielefeld.swl.KINewsBoard.DataLayer.DataModels.NewsEntry;
import de.fhbielefeld.swl.KINewsBoard.DataLayer.DataModels.View;
import de.fhbielefeld.swl.KINewsBoard.WebService.Frontend.ViewModels.NewsEntryDetailVM;
import de.fhbielefeld.swl.KINewsBoard.WebService.Frontend.ViewModels.NewsEntryVM;
import de.fhbielefeld.swl.KINewsBoard.WebService.Frontend.ViewModels.ViewVM;

import javax.ejb.EJB;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Die Klasse <i>NewsResource</i> stellt dem Frontend den Zugriff auf Nachrichteneinträge bereit.
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
     * @return Statuscode 200, wenn die Anfrage erfolgreich bearbeitet wurde und die bereits veröffentlichten Nachrichteneinträge
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
     * @return Statuscode 200, wenn die Anfrage erfolgreich bearbeitet wurde und die Details eines Nachrichteneintrages
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
     * @return Statuscode 200, wenn die Anfrage erfolgreich bearbeitet wurde und der bewertete Nachrichteneintrag
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
     * @return Statuscode 200, wenn die Anfrage erfolgreich bearbeitet wurde und die Nachrichteneinträge, die in der Ansicht angezeigt werden können
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
