package de.fhbielefeld.scl.KINewsBoard.DataLayer;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceUnit;
import java.util.List;

/**
 * Created by cem on 10.11.16.
 */
public class NewsBoardManager {

    @PersistenceUnit
    private EntityManager entityManager;


    public List<NewsEntry> getNewsEntries() {

        return entityManager.createNamedQuery("NewsEntry.findAll", NewsEntry.class).getResultList();
    }


}
