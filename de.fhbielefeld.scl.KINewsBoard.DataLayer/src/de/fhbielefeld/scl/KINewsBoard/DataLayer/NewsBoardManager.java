package de.fhbielefeld.scl.KINewsBoard.DataLayer;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class NewsBoardManager {
    private EntityManager entityManager;

    private NewsEntryDAO newsEntryDAO;
    private AnalyzerDAO analyzerDAO;
    private CrawlerDAO crawlerDAO;
    private AnalyzerResultDAO analyzerResultDAO;
    private GroupSetDAO groupSetDAO;
    private ViewDAO viewDAO;

    public NewsBoardManager() {

    }

    public NewsEntryDAO getNewsEntryDAO() {
        if (newsEntryDAO == null)
            newsEntryDAO = new NewsEntryDAO(getEntityManager());

        return newsEntryDAO;
    }

    public AnalyzerDAO getAnalyzerDAO() {
        if (analyzerDAO == null)
            analyzerDAO = new AnalyzerDAO(getEntityManager());

        return analyzerDAO;
    }

    public CrawlerDAO getCrawlerDAO() {
        if (crawlerDAO == null)
            crawlerDAO = new CrawlerDAO(getEntityManager());

        return crawlerDAO;
    }

    private EntityManager getEntityManager() {
        if (entityManager == null)
            entityManager = Persistence.createEntityManagerFactory("NewsBoardPU").createEntityManager();

        return entityManager;
    }

    public AnalyzerResultDAO getAnalyzerResultDAO() {
        if (analyzerResultDAO == null)
            analyzerResultDAO = new AnalyzerResultDAO(getEntityManager());
        return analyzerResultDAO;
    }

    public GroupSetDAO getGroupSetDAO() {
        if (groupSetDAO == null)
            groupSetDAO = new GroupSetDAO(getEntityManager());
        return groupSetDAO;
    }

    public ViewDAO getViewDAO() {
        if (viewDAO == null)
            viewDAO = new ViewDAO(getEntityManager());
        return viewDAO;
    }

    public void Close() {
        if (entityManager != null)
            entityManager.close();
    }
}
