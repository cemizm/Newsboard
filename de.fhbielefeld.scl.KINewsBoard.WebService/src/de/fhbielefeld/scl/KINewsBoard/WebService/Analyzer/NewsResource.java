package de.fhbielefeld.scl.KINewsBoard.WebService.Analyzer;

import de.fhbielefeld.scl.KINewsBoard.BusinessLayer.NewsBoardService;
import de.fhbielefeld.scl.KINewsBoard.DataLayer.DataModels.NewsEntry;
import de.fhbielefeld.scl.KINewsBoard.WebService.Shared.ViewModels.AnalyzerResultBaseModel;
import de.fhbielefeld.scl.KINewsBoard.WebService.Shared.ViewModels.NewsEntryBaseModel;

import javax.ejb.EJB;
import javax.naming.AuthenticationException;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
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
     * Ruft alle Nachrichteneinträge ab, die von dem Analyzer mit dem angegebenen Authentifizierungstoken analysiert wurden.
     * @param token Der Authentifizierungstoken des Analyzers
     * @return HTTP-Response, der aussagt, dass die Anfrage bearbeitet und das Ergebnis (Liste der Nachrichteneinträge) übertragen wurde
     * @throws AuthenticationException Wenn der Authentifizierungstoken des Analyzers ungültig ist
     */
    @GET
    public Response getNewsEntries(
            @HeaderParam("token") String token
    ) throws AuthenticationException {
        List<NewsEntry> entries = newsBoardService.getAnalyzerNewsEntries(token);

        List<NewsEntryBaseModel> res = entries.stream().map(NewsEntryBaseModel::new).collect(Collectors.toList());

        return Response.ok(res.toArray(new NewsEntryBaseModel[0])).build();
    }

    /**
     * Veröffentlicht den Nachrichteneintrag mit der angegebenen Id als Analyseergebnis des Analyzers mit dem angegebenen Authentifizierungstoken.
     *
     * @param token Der Authentifizierungstoken des Analyzers
     * @param newsId Die Id des Nachrichteneintrages
     * @param model Model des AnalyzersResults, das nur aus den Basis-Informationen besteht
     * @return HTTP-Response, der aussagt, dass die Anfrage bearbeitet und das Ergebnis übertragen wurde
     * @throws AuthenticationException
     */
    @POST
    @Path("/{newsId}")
    public Response publish(
            @HeaderParam("token") String token,
            @NotNull @PathParam("newsId") String newsId,
            @Valid @NotNull AnalyzerResultBaseModel model
    ) throws AuthenticationException {

        newsBoardService.publishAnalyzerResult(token, newsId, model.getAnalyzerResult());

        return Response.ok().build();
    }
}
