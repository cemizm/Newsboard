package de.fhbielefeld.scl.KINewsBoard.BusinessLayer;

import de.fhbielefeld.scl.KINewsBoard.BusinessLayer.Models.*;
import de.fhbielefeld.scl.KINewsBoard.DataLayer.DataModels.Analyzer;
import de.fhbielefeld.scl.KINewsBoard.DataLayer.DataModels.AnalyzerResult;
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

    public AnalyzerModel getAnalyzer(int id) {
        return new AnalyzerModel(mngr.getAnalyzerDAO().get(id));
    }

    public List<AnalyzerModel> getAllAnalyzers() {
        return mngr.getAnalyzerDAO()
                .getAll()
                .stream()
                .map(AnalyzerModel::new)
                .collect(Collectors.toList());
    }

    public void createAnalyzer(AnalyzerModel analyzerModel) {
        mngr.getAnalyzerDAO().create(analyzerModel.getAnalyzer());
    }

    public AnalyzerModel updateAnalyzer(Analyzer analyzer) {
        return new AnalyzerModel(mngr.getAnalyzerDAO().update(analyzer));
    }

    public void deleteAnalyzer(int id) {
        mngr.getAnalyzerDAO().delete(id);
    }

    public List<AnalyzerResultModel> getAllAnalyzerResults() {
        return mngr.getAnalyzerResultDAO()
                .getAll()
                .stream()
                .map(AnalyzerResultModel::new)
                .collect(Collectors.toList());
    }

    public void create(AnalyzerResultModel analyzerResultModel) {
        mngr.getAnalyzerResultDAO()
                .create(analyzerResultModel.getAnalyzerResult());
    }

    public AnalyzerResultModel updateAnalyzerResult(AnalyzerResultModel analyzerResultModel) {
        return new AnalyzerResultModel(mngr.getAnalyzerResultDAO()
                .update(analyzerResultModel.getAnalyzerResult()));
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
        return new CrawlerModel(mngr.getCrawlerDAO()
                .update(model.getCrawler()));
    }

    public void deleteCrawler(int id) {
        mngr.getCrawlerDAO().delete(id);
    }

    public ViewModel getView(int id) {
        return new ViewModel(mngr.getViewDAO().get(id));
    }

    public List<ViewModel> getAllViews() {
        return mngr.getViewDAO()
                .getAll()
                .stream()
                .map(ViewModel::new)
                .collect(Collectors.toList());
    }

    public void createView(ViewModel viewModel) {
        mngr.getViewDAO().create(viewModel.getView());
    }

    public ViewModel updateView(ViewModel viewModel) {
        return new ViewModel(mngr.getViewDAO()
                .update(viewModel.getView()));
    }

    public void deleteView(int id) {
        mngr.getViewDAO().delete(id);
    }

    public GroupSetModel getGroupSet(int id) {
        return new GroupSetModel(mngr.getGroupSetDAO().get(id));
    }

    public List<GroupSetModel> getAllGroupSets() {
        return mngr.getGroupSetDAO()
                .getAll()
                .stream()
                .map(GroupSetModel::new)
                .collect(Collectors.toList());
    }

    public void createGroupSet(GroupSetModel groupSetModel) {
        mngr.getGroupSetDAO().create(groupSetModel.getGroupSet());
    }

    public GroupSetModel updateGroupSet(GroupSetModel groupSetModel) {
        return new GroupSetModel(mngr.getGroupSetDAO()
                .update(groupSetModel.getGroupSet()));
    }

    public void deleteGroupSet(int id) {
        mngr.getGroupSetDAO().delete(id);
    }

    public NewsEntryModel getNewsEntry(String id) {
        return new NewsEntryModel(mngr.getNewsEntryDAO().get(id));
    }

    public List<NewsEntryModel> getAllNewsEntries() {
        return mngr.getNewsEntryDAO()
                .getAll()
                .stream()
                .map(NewsEntryModel::new)
                .collect(Collectors.toList());
    }

    public void createNewsEntry(NewsEntryModel newsEntryModel) {
        mngr.getNewsEntryDAO().create(newsEntryModel.getNewsEntry());
    }

    public NewsEntryModel updateNewsEntry(NewsEntryModel newsEntryModel) {
        return new NewsEntryModel(mngr.getNewsEntryDAO().update(newsEntryModel.getNewsEntry()));
    }

    public void deleteNewsEntry(String id) {
        mngr.getNewsEntryDAO().delete(id);
    }

}
