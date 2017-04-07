package de.fhbielefeld.swl.KINewsBoard.BusinessLayer;

import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Die Klasse <i>LuceneStartupService</i> startet den initialen Indizierungsvorgang.
 */
@Singleton
@Startup
public class LuceneStartupService {

    @PersistenceContext(name = "NewsBoardPU")
    private EntityManager em;

    /**
     * Initialisiert den Indizierungsvorgang.
     */
    @PostConstruct
    public void initialize() {
        System.out.println("--------------------------> Service Starting");

        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(em);
        try {
            fullTextEntityManager.createIndexer().startAndWait();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        System.out.println("<-------------------------- Service Started");
    }
}