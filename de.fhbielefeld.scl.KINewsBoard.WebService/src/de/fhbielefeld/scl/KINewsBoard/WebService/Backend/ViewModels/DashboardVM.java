package de.fhbielefeld.scl.KINewsBoard.WebService.Backend.ViewModels;

import de.fhbielefeld.scl.KINewsBoard.WebService.Shared.ViewModels.NewsEntryBaseModel;

import java.util.List;

/**
 * Created by cem on 27.11.16.
 */
public class DashboardVM {
    private int countNewsEntries;
    private int countAnalyzerEntries;
    private int countActiveAnalyzer;
    private int countActiveCrawler;

    private List<NewsEntryBaseModel> newsEntries;

    public DashboardVM()
    {

    }

    public int getCountNewsEntries() {
        return countNewsEntries;
    }

    public void setCountNewsEntries(int countNewsEntries) {
        this.countNewsEntries = countNewsEntries;
    }

    public int getCountAnalyzerEntries() {
        return countAnalyzerEntries;
    }

    public void setCountAnalyzerEntries(int countAnalyzerEntries) {
        this.countAnalyzerEntries = countAnalyzerEntries;
    }

    public int getCountActiveAnalyzer() {
        return countActiveAnalyzer;
    }

    public void setCountActiveAnalyzer(int countActiveAnalyzer) {
        this.countActiveAnalyzer = countActiveAnalyzer;
    }

    public int getCountActiveCrawler() {
        return countActiveCrawler;
    }

    public void setCountActiveCrawler(int countActiveCrawler) {
        this.countActiveCrawler = countActiveCrawler;
    }

    public List<NewsEntryBaseModel> getNewsEntries() {
        return newsEntries;
    }

    public void setNewsEntries(List<NewsEntryBaseModel> newsEntries) {
        this.newsEntries = newsEntries;
    }
}
