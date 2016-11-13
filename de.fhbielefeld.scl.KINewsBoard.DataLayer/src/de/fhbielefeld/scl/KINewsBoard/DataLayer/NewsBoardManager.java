package de.fhbielefeld.scl.KINewsBoard.DataLayer;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class NewsBoardManager {
    private EntityManager entityManager;

    private NewsEntryDAO newsEntryDAO;
    private AnalyzerDAO analyzerDAO;

    public NewsBoardManager(){

    }

    public NewsEntryDAO getNewsEntryDAO(){
        if(newsEntryDAO == null)
            newsEntryDAO = new NewsEntryDAO(getEntityManager());

        return newsEntryDAO;
    }

    public AnalyzerDAO getAnalyzerDAO() {
        if(analyzerDAO == null)
            analyzerDAO = new AnalyzerDAO(getEntityManager());

        return analyzerDAO;
    }

    private EntityManager getEntityManager() {
        if (entityManager == null)
            entityManager = Persistence.createEntityManagerFactory("NewsBoardPU").createEntityManager();

        return entityManager;
    }

    public void Close() {
        if(entityManager != null)
            entityManager.close();
    }
}
