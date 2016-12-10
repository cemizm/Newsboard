package de.fhbielefeld.scl.KINewsBoard.BusinessLayer;

import de.fhbielefeld.scl.KINewsBoard.DataLayer.DataModels.*;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by azad- on 17.11.2016.
 */
@Stateless
public class AdminService {


    @PersistenceContext(name = "NewsBoardPU")
    private EntityManager entityManager;

    //region Analyzer

    public Analyzer getAnalyzer(int id) {
        return entityManager.find(Analyzer.class, id);
    }

    public List<Analyzer> getAllAnalyzer() {
        return entityManager.createNamedQuery("Analyzer.findAll", Analyzer.class).getResultList();
    }

    public void createAnalyzer(Analyzer analyzer) {
        checkAnalyzer(analyzer);

        List<Integer> groups = analyzer.getGroupSets().stream().map(GroupSet::getId).collect(Collectors.toList());

        analyzer.setGroupSets(new HashSet<>());
        syncAnalyzer(analyzer, groups);

        entityManager.persist(analyzer);
    }

    public Analyzer updateAnalyzer(Analyzer analyzer) {
        checkAnalyzer(analyzer);

        Analyzer dbAnalyzer = entityManager.find(Analyzer.class, analyzer.getId());

        if (dbAnalyzer == null)
            throw new IllegalArgumentException("Analyzer mit Id '" + analyzer.getId() + "' ist nicht vorhanden!");


        List<Integer> groups = analyzer.getGroupSets().stream().map(GroupSet::getId).collect(Collectors.toList());
        analyzer.setGroupSets(new HashSet<>());

        dbAnalyzer.getGroupSets().stream().collect(Collectors.toList()).forEach(g -> g.removeAnalyzer(dbAnalyzer));

        analyzer = entityManager.merge(analyzer);

        syncAnalyzer(analyzer, groups);

        return entityManager.merge(analyzer);
    }

    public void deleteAnalyzer(int id) {
        Analyzer analyzer = getAnalyzer(id);
        deleteAnalyzer(analyzer);
    }

    public void deleteAnalyzer(Analyzer analyzer) {
        if (analyzer == null)
            throw new IllegalArgumentException("Parameter analyzer darf nicht null sein");

        List<GroupSet> toRemove = analyzer.getGroupSets().stream().collect(Collectors.toList());
        for (GroupSet gs : toRemove)
            gs.removeAnalyzer(analyzer);

        entityManager.remove(analyzer);
    }

    private void syncAnalyzer(Analyzer analyzer, List<Integer> groups) {
        for (Integer gsId : groups) {
            GroupSet dbGS = entityManager.find(GroupSet.class, gsId);

            if (dbGS == null)
                throw new IllegalArgumentException("Gruppe '" + gsId + "' nicht gefunden.");

            dbGS.addAnalyzer(analyzer);
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

    public List<AnalyzerResult> getAllAnalyzerResult() {
        return entityManager.createNamedQuery("AnalyzerResult.findAll", AnalyzerResult.class).getResultList();
    }

    public AnalyzerResult updateAnalyzerResult(AnalyzerResult analyzerResult) {
        if (analyzerResult == null)
            throw new IllegalArgumentException("Parameter analyzerResult darf nicht null sein");

        return entityManager.merge(analyzerResult);
    }

    public void createAnalyzerResult(AnalyzerResult analyzerResult) {
        if (analyzerResult == null)
            throw new IllegalArgumentException("Parameter analyzerResult darf nicht null sein");

        entityManager.persist(analyzerResult);
    }

    public void deleteAnalyzerResult(AnalyzerResult analyzerResult) {
        if (analyzerResult == null)
            throw new IllegalArgumentException("Parameter analyzerResult darf nicht null sein");

        entityManager.remove(analyzerResult);
    }

    //endregion

    //region Crawler

    public Crawler getCrawler(int id) {
        return entityManager.find(Crawler.class, id);
    }

    public List<Crawler> getAllCrawler() {
        return entityManager.createNamedQuery("Crawler.findAll", Crawler.class).getResultList();
    }

    public void createCrawler(Crawler crawler) {
        checkCrawler(crawler);

        entityManager.persist(crawler);
    }

    public Crawler updateCrawler(Crawler crawler) {
        checkCrawler(crawler);

        return entityManager.merge(crawler);
    }

    public void deleteCrawler(int id) {
        Crawler crawler = getCrawler(id);
        deleteCrawler(crawler);
    }

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

    public View getView(int id) {
        return entityManager.find(View.class, id);
    }

    public List<View> getAllView() {
        return entityManager.createNamedQuery("View.findAll", View.class).getResultList();
    }

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

    public void deleteView(int id) {
        View view = getView(id);
        deleteView(view);
    }

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
                throw new IllegalArgumentException("Gruppe '" + cId + "' nicht gefunden.");

            view.addCrawler(dbC);
        }
    }

    //endregion

    //region Group

    public GroupSet getGroupSet(int id) {
        return entityManager.find(GroupSet.class, id);
    }

    public List<GroupSet> getAllGroupSets() {
        return entityManager.createNamedQuery("GroupSet.findAll", GroupSet.class).getResultList();
    }

    public void createGroupSet(GroupSet groupSet) {
        if (groupSet == null)
            throw new IllegalArgumentException("Parameter groupSet darf nicht null sein");

        entityManager.persist(groupSet);
    }

    public GroupSet updateGroupSet(GroupSet groupSet) {
        if (groupSet == null)
            throw new IllegalArgumentException("Parameter groupSet darf nicht null sein");

        return entityManager.merge(groupSet);
    }

    public void deleteGroupSet(int id) {
        GroupSet groupSet = getGroupSet(id);
        deleteGroupSet(groupSet);
    }

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

    public NewsEntry getNewsEntry(String id) {
        if (id == null || id.isEmpty())
            throw new IllegalArgumentException("Parameter id darf nicht null oder leer sein");

        return entityManager.find(NewsEntry.class, id);
    }

    public List<NewsEntry> getAllNewsEntry() {
        return entityManager.createNamedQuery("NewsEntry.findAll", NewsEntry.class).getResultList();
    }

    public void createNewsEntry(NewsEntry newsEntry) {
        if (newsEntry == null)
            throw new IllegalArgumentException("Parameter newsEntry darf nicht null sein");

        entityManager.persist(newsEntry);
    }

    public NewsEntry updateNewsEntry(NewsEntry newsEntry) {
        if (newsEntry == null)
            throw new IllegalArgumentException("Parameter newsEntry darf nicht null sein");

        return entityManager.merge(newsEntry);
    }

    public void deleteNewsEntry(String id) {
        if (id == null || id.isEmpty())
            throw new IllegalArgumentException("Parameter id darf nicht null oder leer sein");

        NewsEntry entry = getNewsEntry(id);
        deleteNewsEntry(entry);
    }

    public void deleteNewsEntry(NewsEntry newsEntry) {
        if (newsEntry == null)
            throw new IllegalArgumentException("Parameter newsEntry darf nicht null sein");

        entityManager.remove(newsEntry);
    }

    //endregion

}
