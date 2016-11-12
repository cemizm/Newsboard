package de.fhbielefeld.scl.KINewsBoard.WebService.Frontend;

import de.fhbielefeld.scl.KINewsBoard.BusinessLayer.Models.NewsModel;
import de.fhbielefeld.scl.KINewsBoard.BusinessLayer.NewsBoardManager;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.List;

/**
 * Created by cem on 10.11.16.
 */

@Path("/news")
public class NewsResource {

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public List<NewsModel> getPublicNewsEntries() throws IOException {
        try (NewsBoardManager mngr = new NewsBoardManager()) {
            return mngr.getPublicNewsEntries();
        }
    }

    @GET
    @Path("/{newsId}")
    @Produces(MediaType.APPLICATION_JSON)
    public NewsModel getNewsEntryDetails(@PathParam("newsId") int newsId) throws IOException {
        try (NewsBoardManager mngr = new NewsBoardManager()) {
            return mngr.getNewsEntryDetails(newsId);
        }
    }

    @GET
    @Path("/findByView/{viewId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<NewsModel> getViewEntries(@PathParam("viewId") int viewId) throws IOException {
        try (NewsBoardManager mngr = new NewsBoardManager()) {
            return mngr.getViewNewsEntries(viewId);
        }
    }
}
