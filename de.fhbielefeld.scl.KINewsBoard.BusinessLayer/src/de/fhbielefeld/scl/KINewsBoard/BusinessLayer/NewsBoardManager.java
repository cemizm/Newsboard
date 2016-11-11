package de.fhbielefeld.scl.KINewsBoard.BusinessLayer;

import de.fhbielefeld.scl.KINewsBoard.BusinessLayer.Models.AnalyzerResultModel;
import de.fhbielefeld.scl.KINewsBoard.BusinessLayer.Models.NewsModel;
import de.fhbielefeld.scl.KINewsBoard.DataLayer.NewsEntry;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cem on 10.11.16.
 */
public class NewsBoardManager implements Closeable {
    private de.fhbielefeld.scl.KINewsBoard.DataLayer.NewsBoardManager mngr;

    public NewsBoardManager() {
        mngr = new de.fhbielefeld.scl.KINewsBoard.DataLayer.NewsBoardManager();
    }

    public List<NewsModel> getPublicNewsEntries() {
        return getNewsModels(mngr.getNewsEntries());
    }

    public List<NewsModel> getViewNewsEntries(int viewId) {
        return getNewsModels(mngr.getNewsEntries());
    }

    public NewsModel getNewsEntryDetails(int viewId) {
        List<NewsModel> models = getNewsModels(mngr.getNewsEntries());
        return models.size() > 0 ? models.get(0) : null;
    }

    public NewsModel publishNewsEntry(String token, NewsModel model) {
        return model;
    }

    public List<NewsModel> getAnalyzerEntries(String token) {
        return getNewsModels(mngr.getNewsEntries());
    }

    private List<NewsModel> getNewsModels(List<NewsEntry> entries) {
        List<NewsModel> models = new ArrayList<>();
        for (NewsEntry entry : entries) {
            models.add(new NewsModel(entry));
        }
        return models;
    }

    public AnalyzerResultModel publishAnalyzerResult(String token, AnalyzerResultModel model) {
        return model;
    }

    @Override
    public void close() throws IOException {
        mngr.close();
    }

}
