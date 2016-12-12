package de.fhbielefeld.scl.KINewsBoard.BusinessLayer;

import de.fhbielefeld.scl.KINewsBoard.DataLayer.DataModels.*;

import javax.ejb.Stateless;
import javax.naming.AuthenticationException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Klasse für den Zugriff auf die Daten in der News Board Datenbank.
 */
@Stateless
public class NewsBoardService {

    @PersistenceContext(name = "NewsBoardPU")
    private EntityManager entityManager;

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
        NewsEntry entry = entityManager.find(NewsEntry.class, newsId);

        if (entry == null)
            throw new IllegalArgumentException("News mit '" + newsId + "' nicht gefunden.");

        return entry;
    }

    public NewsEntry rateNewsEntry(String newsEntryId, boolean up) {
        NewsEntry newsEntry = getNewsEntryDetails(newsEntryId);
        newsEntry.setRating(newsEntry.getRating() + (up ? 1 : -1));
        return entityManager.merge(newsEntry);
    }

    /**
     * Veröffentlicht einen News Eintrag.
     *
     * @param token     Der Authentifizierungs-Token für einen Crawler.
     * @param newsEntry Der News Eintrag der veröffentlicht werden soll.
     * @return Der veröffentlichte News Eintrag
     */
    public NewsEntry publishNewsEntry(String token, NewsEntry newsEntry) throws AuthenticationException {
        Crawler crawler = getCrawlerByToken(token);

        if (newsEntry == null)
            throw new IllegalArgumentException("Parameter newsEntry darf nicht null sein");

        NewsEntry existing = entityManager.find(NewsEntry.class, newsEntry.getId());

        if (existing != null)
            throw new IllegalArgumentException("News Eintrag mit Id schon vorhanden!");

        newsEntry.setCrawler(crawler);

        if (newsEntry.getDate() == null)
            newsEntry.setDate(new Date());

        entityManager.persist(newsEntry);

        return newsEntry;
    }

    /**
     * Liefert alle noch nicht analysierten News Einträge für einen Ananlyzer.
     *
     * @param token der Auth-Token für einen AnalyzerModel.
     * @return Liste der News Einträge.
     */
    public List<NewsEntry> getAnalyzerNewsEntries(String token) throws AuthenticationException {
        Analyzer analyzer = getAnalyzerByToken(token);

        return entityManager.createNamedQuery("NewsEntry.getNotAnalyzedNewsEntries", NewsEntry.class)
                .setParameter("analyzer", analyzer.getId())
                .getResultList();
    }

    /**
     * Veröffentlicht ein Analyse Ergebnis zu einem News Eintrag.
     *
     * @param token          der Auth-Token für einen AnalyzerModel.
     * @param analyzerResult das Analyse Ergebnis.
     * @return Das veröffentlichte Analyse Ergebnis.
     */
    public AnalyzerResult publishAnalyzerResult(String token, String newsId, AnalyzerResult analyzerResult) throws AuthenticationException {

        Analyzer analyzer = getAnalyzerByToken(token);
        NewsEntry newsEntry = getNewsEntryDetails(newsId);

        AnalyzerResult exisiting = getAnalyzerResult(analyzer.getId(), newsEntry.getId());
        if (exisiting != null)
            throw new IllegalArgumentException("Für diesen News Eintrag existiert bereits ein Analyse Ergebnis");

        analyzerResult.setAnalyzer(analyzer);
        analyzerResult.setNewsEntry(newsEntry);

        entityManager.persist(analyzerResult);

        return analyzerResult;
    }

    private AnalyzerResult getAnalyzerResult(int analyzerId, String newsId) {
        Optional<AnalyzerResult> o = entityManager.createNamedQuery("AnalyzerResult.findByNewsToken", AnalyzerResult.class)
                .setParameter("newsId", newsId)
                .setParameter("analyzerId", analyzerId)
                .getResultList().stream().findFirst();

        return o.isPresent() ? o.get() : null;
    }

    private Analyzer getAnalyzerByToken(String token) throws AuthenticationException {
        Optional<Analyzer> o = entityManager.createNamedQuery("Analyzer.findByToken", Analyzer.class)
                .setParameter("token", token).getResultList().stream().findFirst();

        if (!o.isPresent())
            throw new AuthenticationException("Token nicht gültig.");

        return o.get();
    }

    private Crawler getCrawlerByToken(String token) throws AuthenticationException {
        Optional<Crawler> o = entityManager.createNamedQuery("Crawler.findByToken", Crawler.class)
                .setParameter("token", token).getResultList().stream().findFirst();

        if (!o.isPresent())
            throw new AuthenticationException("Token nicht gültig.");

        return o.get();
    }

    public View getView(int viewId) {
        View view = entityManager.find(View.class, viewId);

        if (view == null)
            throw new IllegalArgumentException("View nicht gefunden!");

        return view;
    }


}
