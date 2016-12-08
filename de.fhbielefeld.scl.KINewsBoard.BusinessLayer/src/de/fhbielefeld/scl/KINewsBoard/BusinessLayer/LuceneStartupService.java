package de.fhbielefeld.scl.KINewsBoard.BusinessLayer;

import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by cem on 08.12.16.
 */
@Singleton
@Startup
public class LuceneStartupService {

    @PersistenceContext(name = "NewsBoardPU")
    private EntityManager em;

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