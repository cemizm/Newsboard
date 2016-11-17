package de.fhbielefeld.scl.KINewsBoard.BusinessLayer;

import de.fhbielefeld.scl.KINewsBoard.BusinessLayer.Models.CrawlerModel;
import de.fhbielefeld.scl.KINewsBoard.DataLayer.DataModels.*;
import de.fhbielefeld.scl.KINewsBoard.DataLayer.NewsBoardManager;

import javax.ejb.Stateless;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by azad- on 17.11.2016.
 */
@Stateless
public class AdminService {

    private NewsBoardManager mngr;

    public AdminService() {
        this.mngr = new NewsBoardManager();
    }

    public Analyzer getAnalyzer(int id) {
        return mngr.getAnalyzerDAO().get(id);
    }

    public List<Analyzer> getAllAnalyzers() {
        return mngr.getAnalyzerDAO().getAll();
    }

    public void createAnalyzer(Analyzer analyzer) {
        mngr.getAnalyzerDAO().create(analyzer);
    }

    public Analyzer updateAnalyzer(Analyzer analyzer) {
        return mngr.getAnalyzerDAO().update(analyzer);
    }

    public void deleteAnalyzer(int id) {
        Analyzer analyzer = getAnalyzer(id);
        deleteAnalyzer(analyzer);
    }

    public void deleteAnalyzer(Analyzer analyzer) {
        mngr.getAnalyzerDAO().delete(analyzer);
    }

    public List<AnalyzerResult> getAllAnalyzerResults() {
        return mngr.getAnalyzerResultDAO().getAll();
    }

    public void create(AnalyzerResult analyzerResult) {
        mngr.getAnalyzerResultDAO().create(analyzerResult);
    }

    public AnalyzerResult updateAnalyzerResult(AnalyzerResult analyzerResult) {
        return mngr.getAnalyzerResultDAO().update(analyzerResult);
    }

    public void deleteAnalyzerResult(AnalyzerResult analyzerResult) {
        mngr.getAnalyzerResultDAO().delete(analyzerResult);
    }


    public CrawlerModel getCrawler(int id) {
        return new CrawlerModel(mngr.getCrawlerDAO().get(id));
    }

    public List<CrawlerModel> getAllCrawler() {
        return mngr.getCrawlerDAO()
                .getAll()
                .stream()
                .map(CrawlerModel::new)
                .collect(Collectors.toList());
    }

    public void createCrawler(CrawlerModel model) {
        mngr.getCrawlerDAO().create(model.getCrawler());
    }

    public CrawlerModel updateCrawler(CrawlerModel model) {
        return new CrawlerModel(mngr.getCrawlerDAO().update(model.getCrawler()));
    }

    public void deleteCrawler(int id) {
        mngr.getCrawlerDAO().delete(id);
    }

    public View getView(int id) {
        return mngr.getViewDAO().get(id);
    }

    public List<View> getAllViews() {
        return mngr.getViewDAO().getAll();
    }

    public void createView(View view) {
        mngr.getViewDAO().create(view);
    }

    public View updateView(View view) {
        return mngr.getViewDAO().update(view);
    }

    public void deleteView(int id) {
        View view = getView(id);
        deleteView(view);
    }

    public void deleteView(View view) {
        mngr.getViewDAO().delete(view);
    }

    public GroupSet getGroupSet(int id) {
        return mngr.getGroupSetDAO().get(id);
    }

    public List<GroupSet> getAllGroupSets() {
        return mngr.getGroupSetDAO().getAll();
    }

    public void createGroupSet(GroupSet groupSet) {
        mngr.getGroupSetDAO().create(groupSet);
    }

    public GroupSet updateGroupSet(GroupSet groupSet) {
        return mngr.getGroupSetDAO().update(groupSet);
    }

    public void deleteGroupSet(int id) {
        GroupSet groupSet = getGroupSet(id);
        deleteGroupSet(groupSet);
    }

    public void deleteGroupSet(GroupSet groupSet) {
        mngr.getGroupSetDAO().delete(groupSet);
    }

    public NewsEntry getNewsEntry(String id) {
        return mngr.getNewsEntryDAO().get(id);
    }

    public List<NewsEntry> getAllNewsEntries() {
        return mngr.getNewsEntryDAO().getAll();
    }

    public void createNewsEntry(NewsEntry newsEntry) {
        mngr.getNewsEntryDAO().create(newsEntry);
    }

    public NewsEntry updateNewsEntry(NewsEntry newsEntry) {
        return mngr.getNewsEntryDAO().update(newsEntry);
    }

    public void deleteNewsEntry(String id) {
        NewsEntry newsEntry = getNewsEntry(id);
        deleteNewsEntry(newsEntry);

    }

    public void deleteNewsEntry(NewsEntry newsEntry) {
        mngr.getNewsEntryDAO().delete(newsEntry);
    }

}
