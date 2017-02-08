package de.fhbielefeld.swl.KINewsBoard.WebService.Crawler;

import de.fhbielefeld.swl.KINewsBoard.BusinessLayer.NewsBoardService;
import de.fhbielefeld.swl.KINewsBoard.DataLayer.DataModels.Crawler;
import de.fhbielefeld.swl.KINewsBoard.WebService.Shared.ViewModels.NewsEntryBaseModel;
import de.fhbielefeld.swl.KINewsBoard.WebService.Shared.utils.CustomPrincipal;

import javax.ejb.EJB;
import javax.naming.AuthenticationException;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

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
     * @param securityContext Der aktuelle Security Context
     * @param model Model des Nachrichteneintrages, das nur aus den Basis-Informationen besteht
     * @return Statuscode 200, wenn die Anfrage erfolgreich bearbeitet wurde,<br>
     * Statuscode 401, wenn die Authorisierung fehlschlägt
     * @throws AuthenticationException Wenn der Authentifizierungstoken des Crawlers ungültig ist
     */
    @POST
    public Response publish(
            @Context SecurityContext securityContext,
            @Valid @NotNull NewsEntryBaseModel model
    ) throws AuthenticationException {
        Crawler crawler = null;
        if (securityContext != null && securityContext.getUserPrincipal() != null) {
            if (securityContext.getUserPrincipal() instanceof CustomPrincipal)
                crawler = (Crawler) ((CustomPrincipal) securityContext.getUserPrincipal()).getValue();
        }

        if (crawler == null)
            throw new AuthenticationException();

        newsBoardService.publishNewsEntry(crawler, model.getNewsEntryModel());

        return Response.ok().build();
    }
}
