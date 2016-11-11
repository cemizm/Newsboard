package de.fhbielefeld.scl.KINewsBoard.DataLayer;

import de.fhbielefeld.scl.KINewsBoard.DataLayer.DataModels.NewsEntry;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.io.Closeable;
import java.io.IOException;
import java.util.List;

/**
 * Created by cem on 10.11.16.
 */
public class NewsBoardDAO implements Closeable {

    private EntityManager entityManager;


    public List<NewsEntry> getNewsEntries() {
        EntityManager mngr = getEntityManager();

        return mngr.createNamedQuery("NewsEntry.findAll", NewsEntry.class).getResultList();
    }

    private EntityManager getEntityManager() {
        if (entityManager == null)
            entityManager = Persistence.createEntityManagerFactory("NewsBoardPU").createEntityManager();

        return entityManager;
    }

    @Override
    public void close() throws IOException {
        if (entityManager != null)
            entityManager.close();
    }
}
