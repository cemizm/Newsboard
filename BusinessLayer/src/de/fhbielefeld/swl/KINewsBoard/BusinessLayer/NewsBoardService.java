package de.fhbielefeld.swl.KINewsBoard.BusinessLayer;

import de.fhbielefeld.swl.KINewsBoard.DataLayer.DataModels.*;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.BooleanJunction;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.hibernate.sql.JoinType;

import javax.ejb.Stateless;
import javax.naming.AuthenticationException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Die Klasse <i>NewsBoardService</i> dient dem Zugriff auf die Daten in der NewsBoard-Datenbank.
 */
@Stateless
public class NewsBoardService {

    private final int MAX_RESULTS = 20;

    @PersistenceContext(name = "NewsBoardPU")
    private EntityManager entityManager;

    /**
     * Ermitellt, beginnend mit der in <i>start</i> angegebenen Seite, die nächsten 20 Nachrichteneinträgen, die in der Web-/Mobile-Ansicht angezeigt
     * werden können.
     *
     * @param start   Die Seite, ab der die nächsten 20 Nachrichteneinträge ermitellt werden sollen
     * @param keyword Das Schlüsselwort, nach dem die Nachrichteneinträge gefiltert werden sollen
     * @param view    Die Ansicht, in der die Nachrichteneinträge gezeigt werden sollen
     * @return Liste der Nachrichteneinträge, die in der Web-/Mobile-Ansicht angezeigt werden
     */
    public List<NewsEntry> getNewsEntries(int start, String keyword, View view) {
        FullTextEntityManager ftem = Search.getFullTextEntityManager(entityManager);
        Session session = entityManager.unwrap(Session.class);

        QueryBuilder qb = ftem.getSearchFactory().buildQueryBuilder().forEntity(NewsEntry.class).get();

        BooleanJunction bj = qb.bool();

        if (keyword != null && !keyword.isEmpty())
            bj.must(qb.keyword().onFields("title", "content").matching(keyword).createQuery());

        if (bj.isEmpty())
            bj.must(qb.all().createQuery());

        FullTextQuery ftq = ftem.createFullTextQuery(bj.createQuery(), NewsEntry.class);

        ftq.setSort(new Sort(new SortField("date", SortField.Type.LONG, true),
                new SortField("rating", SortField.Type.INT, true),
                SortField.FIELD_SCORE));

        Criteria c = session.createCriteria(NewsEntry.class);
        if (view != null && view.getCrawlers().size() > 0) {
            c.createAlias("crawler", "crawler", JoinType.LEFT_OUTER_JOIN);
            c.createAlias("crawler.views", "view", JoinType.LEFT_OUTER_JOIN);
            c.add(Restrictions.eq("view.id", view.getId()));
        }

        //BUG: setting max results changes sort order of entities
        //and does not work on joins (only limits the total results not the count of news entries)

        //c.setMaxResults(MAX_RESULTS);
        //c.setFirstResult(MAX_RESULTS * (start - 1));

        //following has really bad performance, but is the last option to go

        List<NewsEntry> list = ftq.setCriteriaQuery(c).getResultList();

        int startIndex = MAX_RESULTS * (start - 1);
        int endIndex = MAX_RESULTS * start;

        if (list.size() <= startIndex)
            return new ArrayList<>();

        if (list.size() < endIndex)
            endIndex = list.size();

        return list.subList(startIndex, endIndex);
    }

    /**
     * Lädt die Details einer Nachricht anhand der angegebenen Id.
     *
     * @param newsId Die Id der Nachricht, dessen Details geladen werden sollen
     * @return Die Details zu der Nachricht
     */
    public NewsEntry getNewsEntryDetails(String newsId) {
        NewsEntry entry = entityManager.find(NewsEntry.class, newsId);

        if (entry == null)
            throw new IllegalArgumentException("News mit '" + newsId + "' nicht gefunden.");

        return entry;
    }

    /**
     * Bewertet einen Nachrichteneintrag anhand der angegebenen Id.
     *
     * @param newsEntryId Die Id des Nachrichteneintrages, der bewertet werden soll
     * @param up          <i>true</i>, wenn der nachrichteneintrag positiv bewertet werden soll
     * @return Die bewertete Nachricht
     */
    public NewsEntry rateNewsEntry(String newsEntryId, boolean up) {
        NewsEntry newsEntry = getNewsEntryDetails(newsEntryId);
        newsEntry.setRating(newsEntry.getRating() + (up ? 1 : -1));
        return entityManager.merge(newsEntry);
    }

    /**
     * Ruft die Ansicht anhand der angegebenen Id ab.
     *
     * @param viewId Die Id der Ansicht, die abgeruft werden soll
     * @return Eine Ansicht mit der angegebenen Id
     */
    public View getView(int viewId) {
        View view = entityManager.find(View.class, viewId);

        if (view == null)
            throw new IllegalArgumentException("View nicht gefunden!");

        return view;
    }

    //region Crawler Methods

    /**
     * Veröffentlicht einen Nachrichteneintrag.
     *
     * @param token     Der Authentifizierungstoken für einen Crawler
     * @param newsEntry Der Nachrichteneintrag, der veröffentlicht werden soll
     * @return Der veröffentlichte Nachrichteneintrag
     * @throws AuthenticationException Wenn der Authentifizierungstoken des Crawlers nicht gültig ist
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

    //endregion

    //region Analyzer Methods

    /**
     * Liefert alle noch nicht analysierten Nachrichteneinträge für einen Analyzer.
     *
     * @param token Der Authentifizierungstoken für einen AnalyzerModel
     * @return Liste der Nachrichteneinträge
     * @throws AuthenticationException Wenn der Authentifizierungstoken des Analyzers ungültig ist
     */
    public List<NewsEntry> getAnalyzerNewsEntries(String token) throws AuthenticationException {
        Analyzer analyzer = getAnalyzerByToken(token);

        return entityManager.createNamedQuery("NewsEntry.getNotAnalyzedNewsEntries", NewsEntry.class)
                .setParameter("analyzer", analyzer.getId())
                .setMaxResults(MAX_RESULTS)
                .getResultList();
    }

    /**
     * Veröffentlicht ein Analyseergebnis zu einem Nachrichteneintrag.
     *
     * @param token          Der Authentifizierungstoken für einen AnalyzerModel.
     * @param newsId         Die Id zu einem Nachrichteneintrag
     * @param analyzerResult Das Analyseergebnis
     * @return Das veröffentlichte Analyseergebnis.
     * @throws AuthenticationException Wenn der Authentifizierungstoken des Analyzers ungültig ist
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

    //endregion

    //region Helper Methods

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

    //endregion

}
