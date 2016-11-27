package de.fhbielefeld.scl.KINewsBoard.BusinessLayer;

import de.fhbielefeld.scl.KINewsBoard.DataLayer.DataModels.*;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by azad- on 17.11.2016.
 */
@Stateless
public class AdminService {


    @PersistenceContext(name = "NewsBoardPU")
    private EntityManager entityManager;

    public Analyzer getAnalyzer(int id) {
        return entityManager.find(Analyzer.class, id);
    }

    public Analyzer getAnalyzerByToken(String token) {
        return entityManager.createNamedQuery("Analyzer.findByToken", Analyzer.class)
                .setParameter("token", token)
                .getSingleResult();
    }

    public List<Analyzer> getAllAnalyzer() {
        return entityManager.createNamedQuery("Analyzer.findAll", Analyzer.class).getResultList();
    }

    public void createAnalyzer(Analyzer analyzer) {
        if (analyzer == null)
            throw new IllegalArgumentException("Parameter analyzer darf nicht null sein");

        Set<GroupSet> groups = analyzer.getGroupSets();
        analyzer.setGroupSets(new HashSet<>());
        for (GroupSet gs : groups) {
            GroupSet dbGS = entityManager.find(GroupSet.class, gs.getId());
            dbGS.addAnalyzer(analyzer);
        }

        entityManager.persist(analyzer);
    }

    public Analyzer updateAnalyzer(Analyzer analyzer) {
        if (analyzer == null)
            throw new IllegalArgumentException("Parameter analyzer darf nicht null sein");

        Analyzer dbAn = getAnalyzer(analyzer.getId());

        if (dbAn == null)
            throw new IllegalArgumentException("Analyzer existiert nicht!");

        List<Integer> groups = analyzer.getGroupSets().stream().map(GroupSet::getId).collect(Collectors.toList());

        analyzer.setGroupSets(new HashSet<>());

        //entferne alle Gruppen -> Analyzer Zuordnungen die nicht mehr existieren
        List<GroupSet> toRemove = dbAn.getGroupSets().stream().filter(gs -> !groups.contains(gs.getId())).collect(Collectors.toList());
        for (GroupSet gs : toRemove)
            gs.removeAnalyzer(dbAn);

        //Füge alle Gruppen -> Analyzer Zuordnungen hinzu die noch nicht existieren
        List<Integer> existing = dbAn.getGroupSets().stream().map(GroupSet::getId).collect(Collectors.toList());
        List<Integer> toAdd = groups.stream().filter(i -> !existing.contains(i)).collect(Collectors.toList());
        for(Integer gId: toAdd)
        {
            GroupSet gs = getGroupSet(gId);
            if(gs == null)
                throw new IllegalArgumentException("Gruppe existiert nicht!");

            gs.addAnalyzer(dbAn);
        }

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
        for(GroupSet gs : toRemove)
            gs.removeAnalyzer(analyzer);

        entityManager.remove(analyzer);
    }

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

    public Crawler getCrawler(int id) {
        return entityManager.find(Crawler.class, id);
    }

    public Crawler getCrawlerByToken(String token) {
        return entityManager.createNamedQuery("Crawler.findByToken", Crawler.class)
                .setParameter("token", token)
                .getSingleResult();
    }

    public List<Crawler> getAllCrawler() {
        return entityManager.createNamedQuery("Crawler.findAll", Crawler.class).getResultList();
    }

    public void createCrawler(Crawler crawler) {
        if (crawler == null)
            throw new IllegalArgumentException("Parameter crawler darf nicht null sein");

        entityManager.persist(crawler);
    }

    public Crawler updateCrawler(Crawler crawler) {
        if (crawler == null)
            throw new IllegalArgumentException("Parameter crawler darf nicht null sein");
        return entityManager.merge(crawler);
    }

    public void deleteCrawler(int id) {
        Crawler crawler = getCrawler(id);
        deleteCrawler(crawler);
    }

    public void deleteCrawler(Crawler crawler) {
        if (crawler == null)
            throw new IllegalArgumentException("Parameter crawler darf nicht null sein");

        entityManager.remove(crawler);
    }

    public View getView(int id) {
        return entityManager.find(View.class, id);
    }

    public List<View> getAllView() {
        return entityManager.createNamedQuery("View.findAll", View.class).getResultList();
    }

    public void createView(View view) {
        if (view == null)
            throw new IllegalArgumentException("Parameter view darf nicht null sein");

        entityManager.persist(view);
    }

    public View updateView(View view) {
        if (view == null)
            throw new IllegalArgumentException("Parameter view darf nicht null sein");

        return entityManager.merge(view);
    }

    public void deleteView(int id) {
        View view = getView(id);
        deleteView(view);
    }

    public void deleteView(View view) {
        if (view == null)
            throw new IllegalArgumentException("Parameter view darf nicht null sein");

        entityManager.remove(view);
    }

    public GroupSet getGroupSet(int id) {
        return entityManager.find(GroupSet.class, id);
    }

    public List<GroupSet> getGroupSets(List<Integer> ids) {
        return entityManager.createNamedQuery("GroupSet.findByIds", GroupSet.class).setParameter("ids", ids).getResultList();
    }

    public List<GroupSet> getAll() {
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

        entityManager.remove(groupSet);
    }

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
}
