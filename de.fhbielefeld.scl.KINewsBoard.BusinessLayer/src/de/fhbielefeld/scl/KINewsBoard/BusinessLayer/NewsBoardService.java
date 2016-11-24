package de.fhbielefeld.scl.KINewsBoard.BusinessLayer;

import de.fhbielefeld.scl.KINewsBoard.DataLayer.DBUtils;
import de.fhbielefeld.scl.KINewsBoard.DataLayer.DataModels.*;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import java.util.List;

/**
 * Klasse für den Zugriff auf die Daten in der News Board Datenbank.
 */
@Stateless
public class NewsBoardService {

    private EntityManager entityManager;

    public NewsBoardService() {
        DBUtils.getEntityManager();
    }

    /**
     * Ermitellt <code>limit</code> definierten Anzahl von News Einträge die in der Web/Mobile Ansicht angezeit werden können, beginnend mit der <code>start</code> angegeben Seite.
     *
     * @param start   Die Seite ab der die nächsten <code>limit</code> News Einträge ermitellt werden sollen.
     * @param keyword Das Keyword nach dem die News Einträge gefilter werden sollen.
     * @return Liste der News Einträge die in der Web/Mobile Ansicht angezeigt werden.
     */
    public List<NewsEntry> getPublicNewsEntries(int start, String keyword) {
        return entityManager.createNamedQuery("NewsEntry.findAll", NewsEntry.class).getResultList();
    }

    /**
     * Ermitellt ab Seite <code>start</code>, die in der mit <code>viewId</code> angegeben View definierte Anzahl von News Einträge.
     *
     * @param viewId die Id der View zu dem die News Einträge ermitellt werden sollen.
     * @return Liste der News Einträge die der View zugeordnet sind.
     * @throws IllegalArgumentException wenn View mit <code>viewId</code> nicht existiert.
     */
    public List<NewsEntry> getViewNewsEntries(int viewId) {
        return entityManager.createNamedQuery("NewsEntry.findAll", NewsEntry.class).getResultList();
    }

    /**
     * Lädt die Details zu der angebgen News Id.
     *
     * @param newsId Die Id der News dessen Details geladen werden sollen.
     * @return Die Detail Ansicht zu der News.
     */
    public NewsEntry getNewsEntryDetails(String newsId) {
        return entityManager.find(NewsEntry.class, newsId);
    }

    /**
     * Veröffentlicht einen News Eintrag.
     *
     * @param token Der Authentifizierungs-Token für einen Crawler.
     * @param model Der News Eintrag der veröffentlicht werden soll.
     * @return Der veröffentlichte News Eintrag
     */
    public NewsEntry publishNewsEntry(String token, NewsEntry newsEntry) {
        Crawler crawler = entityManager.createNamedQuery("Crawler.findByToken", Crawler.class)
                .setParameter("token", token)
                .getSingleResult();

        if (crawler == null)
            throw new IllegalArgumentException("Token nicht gültig.");

        if (newsEntry == null)
            throw new IllegalArgumentException("Parameter newsEntry darf nicht null sein");

        entityManager.persist(newsEntry);

        return newsEntry;
    }

    public View getView(int viewId) {
        View view = entityManager.find(View.class, viewId);

        if (view == null)
            throw new IllegalArgumentException("View nicht gefunden.");

        return view;
    }

    /**
     * Liefert alle noch nicht analysierten News Einträge für einen Ananlyzer.
     *
     * @param token der Auth-Token für einen AnalyzerModel.
     * @return Liste der News Einträge.
     */
    public List<NewsEntry> getAnalyzerNewsEntries(String token) {
        return entityManager.createNamedQuery("NewsEntry.findAll", NewsEntry.class).getResultList();
    }

    /**
     * Veröffentlicht ein Analyse Ergebnis zu einem News Eintrag.
     *
     * @param token          der Auth-Token für einen AnalyzerModel.
     * @param analyzerResult das Analyse Ergebnis.
     * @return Das veröffentlichte Analyse Ergebnis.
     */
    public AnalyzerResult publishAnalyzerResult(String token, AnalyzerResult analyzerResult) {
        Analyzer analyzer = entityManager.createNamedQuery("Analyzer.findByToken", Analyzer.class)
                .setParameter("token", token)
                .getSingleResult();

        if (analyzer == null)
            throw new IllegalArgumentException("Token nicht gültig.");

        return analyzerResult;
    }

}
