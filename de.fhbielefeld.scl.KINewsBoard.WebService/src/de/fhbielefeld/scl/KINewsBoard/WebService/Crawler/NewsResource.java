package de.fhbielefeld.scl.KINewsBoard.WebService.Crawler;

import de.fhbielefeld.scl.KINewsBoard.BusinessLayer.NewsBoardService;
import de.fhbielefeld.scl.KINewsBoard.WebService.Shared.ViewModels.NewsEntryBaseModel;

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
 * Created by cem on 10.11.16.
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
     * @return HTTP-Response, dass die Anfrage bearbeitet und das Ergebnis übertragen wurde
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
