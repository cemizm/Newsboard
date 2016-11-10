package de.fhbielefeld.scl.KINewsBoard.WebService;

import de.fhbielefeld.scl.KINewsBoard.BusinessLayer.Customer;
import de.fhbielefeld.scl.KINewsBoard.BusinessLayer.MeineKlasse;

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
    public List<Customer> getClichedMessage() {

        ArrayList<Customer> list = new ArrayList<>();
        list.add(new Customer("Cem Basoglu", 25));
        list.add(new Customer("Test", 34));
        return list;
    }
}
