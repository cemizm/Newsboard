package de.fhbielefeld.scl.KINewsBoard.DataLayer;

import de.fhbielefeld.scl.KINewsBoard.DataLayer.DataModels.Crawler;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by cem on 14.11.16.
 */
public class CrawlerDAO {

    private EntityManager entityManager;

    public CrawlerDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Crawler get(int id) {
        return entityManager.find(Crawler.class, id);
    }

    public Crawler getByToken(String token) {
        return entityManager.createNamedQuery("Crawler.findByToken", Crawler.class)
                .setParameter("token", token)
                .getSingleResult();
    }

    public List<Crawler> getAll() {
        return entityManager.createNamedQuery("Crawler.findAll", Crawler.class).getResultList();
    }

    public void create(Crawler crawler) {
        if (crawler == null)
            throw new IllegalArgumentException("Paramter crawler darf nicht null sein");

        entityManager.getTransaction().begin();

        entityManager.persist(crawler);

        entityManager.getTransaction().commit();
    }

    public Crawler update(Crawler crawler) {
        if (crawler == null)
            throw new IllegalArgumentException("Paramter crawler darf nicht null sein");

        entityManager.getTransaction().begin();

        crawler = entityManager.merge(crawler);

        entityManager.getTransaction().commit();

        return crawler;
    }

    public void delete(int id) {
        Crawler crawler = get(id);
        delete(crawler);
    }

    public void delete(Crawler crawler) {
        if (crawler == null)
            throw new IllegalArgumentException("Paramter crawler darf nicht null sein");

        entityManager.getTransaction().begin();

        entityManager.remove(crawler);

        entityManager.getTransaction().commit();

    }
}
