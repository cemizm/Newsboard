package de.fhbielefeld.scl.KINewsBoard.DataLayer;

import de.fhbielefeld.scl.KINewsBoard.DataLayer.DataModels.NewsEntry;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

/**
 * Created by cem on 13.11.16.
 */
public class NewsEntryDAO {

    private EntityManager entityManager;

    public NewsEntryDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public NewsEntry get(String id) {
        if (id == null || id.isEmpty())
            throw new IllegalArgumentException("Paramter id darf nicht null oder leer sein");

        return entityManager.find(NewsEntry.class, id);
    }

    public List<NewsEntry> getAll() {

        return entityManager.createNamedQuery("NewsEntry.findAll", NewsEntry.class).getResultList();
    }

    public void create(NewsEntry newsEntry) {
        if (newsEntry == null)
            throw new IllegalArgumentException("Paramter newsEntry darf nicht null sein");

        entityManager.getTransaction().begin();

        entityManager.persist(newsEntry);

        entityManager.getTransaction().commit();
    }

    public NewsEntry update(NewsEntry newsEntry) {
        if (newsEntry == null)
            throw new IllegalArgumentException("Paramter newsEntry darf nicht null sein");

        entityManager.getTransaction().begin();

        newsEntry = entityManager.merge(newsEntry);

        entityManager.getTransaction().commit();

        return newsEntry;
    }

    public void delete(String id) {
        if (id == null || id.isEmpty())
            throw new IllegalArgumentException("Paramter id darf nicht null oder leer sein");

        NewsEntry entry = get(id);
        delete(entry);
    }

    public void delete(NewsEntry newsEntry) {
        if (newsEntry == null)
            throw new IllegalArgumentException("Paramter newsEntry darf nicht null sein");

        entityManager.getTransaction().begin();

        entityManager.remove(newsEntry);

        entityManager.getTransaction().commit();

    }
}
