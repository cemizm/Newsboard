package de.fhbielefeld.scl.KINewsBoard.BusinessLayer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cem on 28.10.16.
 */
public class MeineKlasse {

    public List<Customer> Machwas() {
        ArrayList<Customer> list = new ArrayList<>();
        list.add(new Customer("Cem Basoglu", 25));
        list.add(new Customer("Test", 34));
        return list;
    }
}
