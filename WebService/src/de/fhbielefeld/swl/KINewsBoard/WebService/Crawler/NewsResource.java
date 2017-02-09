package de.fhbielefeld.swl.KINewsBoard.WebService.Crawler;

import de.fhbielefeld.swl.KINewsBoard.BusinessLayer.NewsBoardService;
import de.fhbielefeld.swl.KINewsBoard.WebService.Shared.ViewModels.NewsEntryBaseModel;

import javax.ejb.EJB;
import javax.naming.AuthenticationException;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Die Klasse <i>NewsResource</i> stellt dem Crawler den Zugriff auf Nachrichteneinträge bereit.
 */
@Path("/news")
@Produces(MediaType.APPLICATION_JSON)
public class NewsResource {

    @EJB
    private NewsBoardService newsBoardService;

    /**
     * Veröffentlicht den angegebenen Nachrichteneintrag, der vom angegebenen Crawler geliefert wurde.
     *
     * @param token Der Token des Crawlers
     * @param model Model des Nachrichteneintrages, das nur aus den Basis-Informationen besteht
     * @return Statuscode 200, wenn die Anfrage erfolgreich bearbeitet wurde,<br>
     * Statuscode 401, wenn die Authorisierung fehlschlägt
     * @throws AuthenticationException Wenn der Authentifizierungstoken des Crawlers ungültig ist
     */
    @POST
    public Response publish(
            @HeaderParam("token") String token,
            @Valid @NotNull NewsEntryBaseModel model
    ) throws AuthenticationException {

        newsBoardService.publishNewsEntry(token, model.getNewsEntryModel());

        return Response.ok().build();
    }
}
