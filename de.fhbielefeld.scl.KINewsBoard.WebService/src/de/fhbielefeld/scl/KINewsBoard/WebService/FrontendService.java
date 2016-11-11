package de.fhbielefeld.scl.KINewsBoard.WebService;

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

@Path("/frontend")
public class FrontendService {

    @GET
    @Path("/public")
    @Produces(MediaType.APPLICATION_JSON)
    public List<NewsModel> getPublicNewsEntries() throws IOException {
        try (NewsBoardManager mngr = new NewsBoardManager()) {
            return mngr.getPublicNewsEntries();
        }
    }

    @GET
    @Path("/view/{viewid}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<NewsModel> getViewEntries(@PathParam("viewid") int viewId) throws IOException {
        try (NewsBoardManager mngr = new NewsBoardManager()) {
            return mngr.getViewNewsEntries(viewId);
        }
    }

    @GET
    @Path("/details/{viewid}")
    @Produces(MediaType.APPLICATION_JSON)
    public NewsModel getNewsEntryDetails(@PathParam("viewid") int viewId) throws IOException {
        try (NewsBoardManager mngr = new NewsBoardManager()) {
            return mngr.getNewsEntryDetails(viewId);
        }
    }
}
