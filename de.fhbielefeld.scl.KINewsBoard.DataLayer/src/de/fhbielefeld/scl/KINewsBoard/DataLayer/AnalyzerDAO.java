package de.fhbielefeld.scl.KINewsBoard.DataLayer;

import de.fhbielefeld.scl.KINewsBoard.DataLayer.DataModels.Analyzer;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by cem on 13.11.16.
 */
public class AnalyzerDAO {

    private EntityManager entityManager;

    public AnalyzerDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Analyzer get(String id) {
        if (id == null || id.isEmpty())
            throw new IllegalArgumentException("Paramter id darf nicht null oder leer sein");

        return entityManager.find(Analyzer.class, id);
    }

    public List<Analyzer> getAll() {

        return entityManager.createNamedQuery("Analyzer.findAll", Analyzer.class).getResultList();
    }

    public void create(Analyzer analyzer) {
        if (analyzer == null)
            throw new IllegalArgumentException("Paramter analyzer darf nicht null sein");

        entityManager.getTransaction().begin();

        entityManager.persist(analyzer);

        entityManager.getTransaction().commit();
    }

    public Analyzer update(Analyzer analyzer) {
        if (analyzer == null)
            throw new IllegalArgumentException("Paramter analyzer darf nicht null sein");

        entityManager.getTransaction().begin();

        analyzer = entityManager.merge(analyzer);

        entityManager.getTransaction().commit();

        return analyzer;
    }

    public void delete(String id) {
        if (id == null || id.isEmpty())
            throw new IllegalArgumentException("Paramter id darf nicht null oder leer sein");

        Analyzer entry = get(id);
        delete(entry);
    }

    public void delete(Analyzer analyzer) {
        if (analyzer == null)
            throw new IllegalArgumentException("Paramter analyzer darf nicht null sein");

        entityManager.getTransaction().begin();

        entityManager.remove(analyzer);

        entityManager.getTransaction().commit();

    }
}
