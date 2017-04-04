package de.fhbielefeld.swl.KINewsBoard.BusinessLayer;

import de.fhbielefeld.swl.KINewsBoard.DataLayer.DataModels.*;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Die Klasse <i>AdminService</i> dient dem Abruf, dem Erstellen, dem Aktualisieren und dem Löschen von Daten aus dem DataLayer.
 */
@Stateless
public class AdminService {

    @PersistenceContext(name = "NewsBoardPU")
    private EntityManager entityManager;

    //region Analyzer

    /**
     * Ruft einen Analyzer mit entsprechender Id ab.
     *
     * @param id Id des Analyzers, der abgerufen werden soll
     * @return Ein Analyzer mit der entsprechenden Id, sonst <i>null</i>
     */
    public Analyzer getAnalyzer(int id) {
        return entityManager.find(Analyzer.class, id);
    }

    /**
     * Ruft alle vorhandenen Analyzer ab.
     *
     * @return Liste aller gefundenen Analyzer
     */
    public List<Analyzer> getAllAnalyzer() {
        return entityManager.createNamedQuery("Analyzer.findAll", Analyzer.class).getResultList();
    }

    /**
     * Erstellt einen neuen Analyzer.
     *
     * @param analyzer Der zu erstellende Analyzer
     */
    public void createAnalyzer(Analyzer analyzer) {
        checkAnalyzer(analyzer);

        List<Integer> groups = analyzer.getGroupSets().stream().map(GroupSet::getId).collect(Collectors.toList());

        analyzer.setGroupSets(new HashSet<>());
        syncAnalyzerGroups(analyzer, groups);

        entityManager.persist(analyzer);
    }

    /**
     * Aktualisiert einen vorhandenen Analyzer.
     *
     * @param analyzer Der zu aktualisierende Analyzer
     * @return Der aktualisierte Analyzer
     */
    public Analyzer updateAnalyzer(Analyzer analyzer) {
        checkAnalyzer(analyzer);

        Analyzer dbAnalyzer = entityManager.find(Analyzer.class, analyzer.getId());

        if (dbAnalyzer == null)
            throw new IllegalArgumentException("Analyzer mit Id '" + analyzer.getId() + "' ist nicht vorhanden!");

        List<Integer> groups = analyzer.getGroupSets().stream().map(GroupSet::getId).collect(Collectors.toList());
        analyzer.setGroupSets(new HashSet<>());

        dbAnalyzer.getGroupSets().stream().collect(Collectors.toList()).forEach(g -> g.removeAnalyzer(dbAnalyzer));

        List<Integer> crawler = analyzer.getCrawlers().stream().map(Crawler::getId).collect(Collectors.toList());
        analyzer.setCrawlers(new HashSet<>());

        dbAnalyzer.getCrawlers().stream().collect(Collectors.toList()).forEach(g -> g.removeAnalyzer(dbAnalyzer));


        analyzer = entityManager.merge(analyzer);

        syncAnalyzerGroups(analyzer, groups);
        syncAnalyzerCrawler(analyzer, crawler);

        return entityManager.merge(analyzer);
    }

    /**
     * Löscht einen Analyzer mit entsprechender Id.
     *
     * @param id Die Id des Analyzers, der gelöscht werden soll
     */
    public void deleteAnalyzer(int id) {
        Analyzer analyzer = getAnalyzer(id);
        deleteAnalyzer(analyzer);
    }

    /**
     * Löscht einen vorhandenen Analyzer.
     *
     * @param analyzer Der zu löschende Analyzer
     */
    public void deleteAnalyzer(Analyzer analyzer) {
        if (analyzer == null)
            throw new IllegalArgumentException("Parameter analyzer darf nicht null sein");

        List<GroupSet> toRemove = analyzer.getGroupSets().stream().collect(Collectors.toList());
        for (GroupSet gs : toRemove)
            gs.removeAnalyzer(analyzer);

        List<AnalyzerResult> toRemoveResults = analyzer.getResults().stream().collect(Collectors.toList());
        for (AnalyzerResult ar : toRemoveResults)
            analyzer.removeAnalyzerResult(ar);

        entityManager.remove(analyzer);
    }

    private void syncAnalyzerGroups(Analyzer analyzer, List<Integer> groups) {
        for (Integer gsId : groups) {
            GroupSet dbGS = entityManager.find(GroupSet.class, gsId);

            if (dbGS == null)
                throw new IllegalArgumentException("Gruppe '" + gsId + "' nicht gefunden.");

            dbGS.addAnalyzer(analyzer);
        }
    }

    private void syncAnalyzerCrawler(Analyzer analyzer, List<Integer> crawler) {
        for (Integer crawlerId : crawler) {
            Crawler dbCrawler = entityManager.find(Crawler.class, crawlerId);

            if (dbCrawler == null)
                throw new IllegalArgumentException("Crawler '" + crawlerId + "' nicht gefunden.");

            dbCrawler.addAnalyzer(analyzer);
        }
    }

    private void checkAnalyzer(Analyzer analyzer) {
        if (analyzer == null)
            throw new IllegalArgumentException("Parameter analyzer darf nicht null sein");

        Analyzer exisiting = getAnalyzerByToken(analyzer.getToken());

        if (exisiting != null && exisiting.getId() != analyzer.getId())
            throw new IllegalArgumentException("Analyzer mit dem Token exisitiert bereits!");
    }

    private Analyzer getAnalyzerByToken(String token) {
        if (token == null || token.isEmpty())
            throw new IllegalArgumentException("Token darf nicht leer sein!");

        Optional<Analyzer> o = entityManager.createNamedQuery("Analyzer.findByToken", Analyzer.class)
                .setParameter("token", token).getResultList().stream().findFirst();

        return o.isPresent() ? o.get() : null;
    }

    //endregion

    //region AnalyzerResult

    /**
     * Ruft alle vorhandenen Analyseergebnisse ab.
     *
     * @return Liste aller gefundenen Analyseergebnissen
     */
    public List<AnalyzerResult> getAllAnalyzerResult() {
        return entityManager.createNamedQuery("AnalyzerResult.findAll", AnalyzerResult.class).getResultList();
    }

    /**
     * Aktualisiert ein vorhandenes Analyseergebnis.
     *
     * @param analyzerResult Das zu aktualisierende Analyseergebnis
     * @return Das aktualisierte Analyseergebnis
     */
    public AnalyzerResult updateAnalyzerResult(AnalyzerResult analyzerResult) {
        if (analyzerResult == null)
            throw new IllegalArgumentException("Parameter analyzerResult darf nicht null sein");

        return entityManager.merge(analyzerResult);
    }

    /**
     * Erstellt ein neues Analyseergebnis.
     *
     * @param analyzerResult Das zu erstellende Analyseergebnis
     */
    public void createAnalyzerResult(AnalyzerResult analyzerResult) {
        if (analyzerResult == null)
            throw new IllegalArgumentException("Parameter analyzerResult darf nicht null sein");

        entityManager.persist(analyzerResult);
    }

    /**
     * Löscht ein vorhandenes Analyseergebnis.
     *
     * @param analyzerResult Das zu löschende Analyseergebnis.
     */
    public void deleteAnalyzerResult(AnalyzerResult analyzerResult) {
        if (analyzerResult == null)
            throw new IllegalArgumentException("Parameter analyzerResult darf nicht null sein");

        entityManager.remove(analyzerResult);
    }

    //endregion

    //region Crawler

    /**
     * Ruft einen Crawler mit der entsprechenden Id ab.
     *
     * @param id Die Id des Crawlers, der abgerufen werden soll
     * @return Der gefundene Crawler mit entsprechender Id
     */
    public Crawler getCrawler(int id) {
        return entityManager.find(Crawler.class, id);
    }

    /**
     * Ruft alle vorhandenen Crawler ab.
     *
     * @return Liste aller gefundenen Crawler
     */
    public List<Crawler> getAllCrawler() {
        return entityManager.createNamedQuery("Crawler.findAll", Crawler.class).getResultList();
    }

    /**
     * Erstellt einen neuen Crawler.
     *
     * @param crawler Der zu erstellende Crawler
     */
    public void createCrawler(Crawler crawler) {
        checkCrawler(crawler);

        entityManager.persist(crawler);
    }

    /**
     * Aktualisiert einen vorhandenen Crawler.
     *
     * @param crawler Der zu aktualisierende Crawler
     * @return Der aktualisierte Crawler
     */
    public Crawler updateCrawler(Crawler crawler) {
        checkCrawler(crawler);

        return entityManager.merge(crawler);
    }

    /**
     * Löscht einen Crawler mit entsprechender Id.
     *
     * @param id Id des zu löschenden Crawlers
     */
    public void deleteCrawler(int id) {
        Crawler crawler = getCrawler(id);
        deleteCrawler(crawler);
    }

    /**
     * Löscht einen vorhandenen Crawler.
     *
     * @param crawler Der zu löschende Crawler
     */
    public void deleteCrawler(Crawler crawler) {
        if (crawler == null)
            throw new IllegalArgumentException("Parameter crawler darf nicht null sein");

        List<View> toRemove = crawler.getViews().stream().collect(Collectors.toList());
        for (View v : toRemove)
            v.removeCrawler(crawler);

        entityManager.remove(crawler);
    }

    private void checkCrawler(Crawler crawler) {
        if (crawler == null)
            throw new IllegalArgumentException("Parameter crawler darf nicht null sein");

        Crawler exisiting = getCrawlerByToken(crawler.getToken());

        if (exisiting != null && exisiting.getId() != crawler.getId())
            throw new IllegalArgumentException("Crawler mit dem Token exisitiert bereits!");
    }

    private Crawler getCrawlerByToken(String token) {
        if (token == null || token.isEmpty())
            throw new IllegalArgumentException("Token darf nicht leer sein!");

        Optional<Crawler> o = entityManager.createNamedQuery("Crawler.findByToken", Crawler.class)
                .setParameter("token", token).getResultList().stream().findFirst();

        return o.isPresent() ? o.get() : null;
    }

    //endregion

    //region View

    /**
     * Ruft eine Ansicht mit entsprechender Id ab.
     *
     * @param id Die Id der Ansicht, die abgerufen werden soll
     * @return Die Ansicht mit entsprechender Id, sonst <i>null</i>
     */
    public View getView(int id) {
        return entityManager.find(View.class, id);
    }

    /**
     * Ruft alle vorhandenen Ansichten ab.
     *
     * @return Liste aller gefundenen Ansichten
     */
    public List<View> getAllView() {
        return entityManager.createNamedQuery("View.findAll", View.class).getResultList();
    }

    /**
     * Erstellt eine neue Ansicht.
     *
     * @param view Die zu erstellende Ansicht
     */
    public void createView(View view) {
        if (view == null)
            throw new IllegalArgumentException("Parameter view darf nicht null sein");

        List<Integer> groups = view.getGroupSets().stream().map(GroupSet::getId).collect(Collectors.toList());
        List<Integer> crawlers = view.getCrawlers().stream().map(Crawler::getId).collect(Collectors.toList());

        view.setGroupSets(new HashSet<>());
        view.setCrawlers(new HashSet<>());

        syncView(view, groups, crawlers);

        entityManager.persist(view);
    }

    /**
     * Aktualisiert eine vorhandene Ansicht.
     *
     * @param view Die zu aktualisierende Ansicht
     * @return Die aktualisierte Ansicht
     */
    public View updateView(View view) {
        if (view == null)
            throw new IllegalArgumentException("Parameter view darf nicht null sein");

        View dbView = entityManager.find(View.class, view.getId());

        if (dbView == null)
            throw new IllegalArgumentException("View mit Id '" + view.getId() + "' ist nicht vorhanden!");

        List<Integer> groups = view.getGroupSets().stream().map(GroupSet::getId).collect(Collectors.toList());
        List<Integer> crawlers = view.getCrawlers().stream().map(Crawler::getId).collect(Collectors.toList());

        view.setGroupSets(new HashSet<>());
        view.setCrawlers(new HashSet<>());

        dbView.getGroupSets().stream().collect(Collectors.toList()).forEach(g -> dbView.removeGroupSet(g));
        dbView.getCrawlers().stream().collect(Collectors.toList()).forEach(c -> dbView.removeCrawler(c));

        view = entityManager.merge(view);

        syncView(view, groups, crawlers);

        return entityManager.merge(view);
    }

    /**
     * Löscht eine Ansicht anhand einer Id.
     *
     * @param id Die Id der zu löschenden Ansicht
     */
    public void deleteView(int id) {
        View view = getView(id);
        deleteView(view);
    }

    /**
     * Löscht eine vorhandene Ansicht.
     *
     * @param view Die zu löschende Ansicht
     */
    public void deleteView(View view) {
        if (view == null)
            throw new IllegalArgumentException("Parameter view darf nicht null sein");

        view.getGroupSets().stream().collect(Collectors.toList()).forEach(view::removeGroupSet);
        view.getCrawlers().stream().collect(Collectors.toList()).forEach(view::removeCrawler);

        entityManager.remove(view);
    }

    private void syncView(View view, List<Integer> groups, List<Integer> crawlers) {
        for (Integer gsId : groups) {
            GroupSet dbGS = entityManager.find(GroupSet.class, gsId);

            if (dbGS == null)
                throw new IllegalArgumentException("Gruppe '" + gsId + "' nicht gefunden.");

            view.addGroupSet(dbGS);
        }

        for (Integer cId : crawlers) {
            Crawler dbC = entityManager.find(Crawler.class, cId);

            if (dbC == null)
                throw new IllegalArgumentException("Crawler '" + cId + "' nicht gefunden.");

            view.addCrawler(dbC);
        }
    }

    //endregion

    //region Group

    /**
     * Ruft eine Gruppe anhand der Id ab.
     *
     * @param id Die Id der Gruppe, die abgeruft werden soll
     * @return Die Gruppe mit der entsprechenden Id
     */
    public GroupSet getGroupSet(int id) {
        return entityManager.find(GroupSet.class, id);
    }

    /**
     * Ruft alle vorhandenen Gruppen ab.
     *
     * @return Liste aller gefundenen Gruppen
     */
    public List<GroupSet> getAllGroupSets() {
        return entityManager.createNamedQuery("GroupSet.findAll", GroupSet.class).getResultList();
    }

    /**
     * Erstellt eine neue Gruppe.
     *
     * @param groupSet Die zu erstellende Gruppe
     */
    public void createGroupSet(GroupSet groupSet) {
        if (groupSet == null)
            throw new IllegalArgumentException("Parameter groupSet darf nicht null sein");

        entityManager.persist(groupSet);
    }

    /**
     * Aktualisiert eine bestehende Gruppe.
     *
     * @param groupSet Die zu aktualisierende Gruppe
     * @return Die aktualisierte Gruppe
     */
    public GroupSet updateGroupSet(GroupSet groupSet) {
        if (groupSet == null)
            throw new IllegalArgumentException("Parameter groupSet darf nicht null sein");

        return entityManager.merge(groupSet);
    }

    /**
     * Löscht eine Gruppe anhand der Id.
     *
     * @param id Die Id der zu löschenden Gruppe
     */
    public void deleteGroupSet(int id) {
        GroupSet groupSet = getGroupSet(id);
        deleteGroupSet(groupSet);
    }

    /**
     * Löscht eine vorhandene Gruppe.
     *
     * @param groupSet Die zu löschende Gruppe
     */
    public void deleteGroupSet(GroupSet groupSet) {
        if (groupSet == null)
            throw new IllegalArgumentException("Parameter groupSet darf nicht null sein");

        List<View> toRemove = groupSet.getViews().stream().collect(Collectors.toList());
        for (View v : toRemove)
            v.removeGroupSet(groupSet);

        entityManager.remove(groupSet);
    }

    //endregion

    //region NewsEntry

    /**
     * Ruft einen Nachrichteneintrag anhand der Id ab.
     *
     * @param id Die Id des Nachrichteneintrags, der abgeruft werden soll
     * @return Nachrichteneintrag mit entsprechender Id, sonst <i>null</i>
     */
    public NewsEntry getNewsEntry(String id) {
        if (id == null || id.isEmpty())
            throw new IllegalArgumentException("Parameter id darf nicht null oder leer sein");

        return entityManager.find(NewsEntry.class, id);
    }

    /**
     * Ruft alle vorhandenen Nachrichteneinträge ab.
     *
     * @return Alle gefundenen Nachrichteneinträge
     */
    public List<NewsEntry> getAllNewsEntry() {
        return entityManager.createNamedQuery("NewsEntry.findAll", NewsEntry.class).getResultList();
    }

    /**
     * Erstellt einen neuen Nachrichteneintrag.
     *
     * @param newsEntry Der zu erstellende Nachrichteneintrag
     */
    public void createNewsEntry(NewsEntry newsEntry) {
        if (newsEntry == null)
            throw new IllegalArgumentException("Parameter newsEntry darf nicht null sein");

        entityManager.persist(newsEntry);
    }

    /**
     * Aktualisiert einen bestehenden Nachrichteneintrag.
     *
     * @param newsEntry Der zu aktualisierende Nachrichteneintrag
     * @return Der aktualisierte Nachrichteneintrag
     */
    public NewsEntry updateNewsEntry(NewsEntry newsEntry) {
        if (newsEntry == null)
            throw new IllegalArgumentException("Parameter newsEntry darf nicht null sein");

        return entityManager.merge(newsEntry);
    }

    /**
     * Löscht einen Nachrichteneintrag anhand der Id.
     *
     * @param id Die Id des zu löschenden Nachrichteneintrages
     */
    public void deleteNewsEntry(String id) {
        if (id == null || id.isEmpty())
            throw new IllegalArgumentException("Parameter id darf nicht null oder leer sein");

        NewsEntry entry = getNewsEntry(id);
        deleteNewsEntry(entry);
    }

    /**
     * Löscht einen vorhandenen Nachrichteneintrag.
     *
     * @param newsEntry Der zu löschende Nachrichteneintrag
     */
    public void deleteNewsEntry(NewsEntry newsEntry) {
        if (newsEntry == null)
            throw new IllegalArgumentException("Parameter newsEntry darf nicht null sein");

        entityManager.remove(newsEntry);
    }

    //endregion

}
