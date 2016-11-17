package de.fhbielefeld.scl.KINewsBoard.DataLayer;

import de.fhbielefeld.scl.KINewsBoard.DataLayer.DataModels.AnalyzerResult;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by azad- on 14.11.2016.
 */
public class AnalyzerResultDAO {
    private EntityManager entityManager;

    AnalyzerResultDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    public List<AnalyzerResult> getAll() {

        return entityManager.createNamedQuery("AnalyzerResult.findAll", AnalyzerResult.class).getResultList();
    }

    public AnalyzerResult update(AnalyzerResult analyzerResult) {
        if (analyzerResult == null)
            throw new IllegalArgumentException("Parameter analyzerResult darf nicht null oder leer sein");

        return entityManager.merge(analyzerResult);
    }

    public void create(AnalyzerResult analyzerResult) {
        if (analyzerResult == null)
            throw new IllegalArgumentException("Parameter analyzerResult darf nicht null oder leer sein");

        entityManager.persist(analyzerResult);
    }

    public void delete(AnalyzerResult analyzerResult) {
        if (analyzerResult == null)
            throw new IllegalArgumentException("Parameter analyzerResult darf nicht null oder leer sein");

        entityManager.remove(analyzerResult);
    }

}
