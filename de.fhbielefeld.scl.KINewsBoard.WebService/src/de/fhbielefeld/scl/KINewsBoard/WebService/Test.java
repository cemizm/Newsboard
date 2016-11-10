package de.fhbielefeld.scl.KINewsBoard.WebService;

import de.fhbielefeld.scl.KINewsBoard.BusinessLayer.MeineKlasse;
import de.fhbielefeld.scl.KINewsBoard.DataLayer.NewsEntry;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by cem on 28.10.16.
 */
@Path("/customer")
public class Test {

    MeineKlasse test = new MeineKlasse();


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<NewsEntry> getClichedMessage() {

        return test.Machwas();
    }
}
