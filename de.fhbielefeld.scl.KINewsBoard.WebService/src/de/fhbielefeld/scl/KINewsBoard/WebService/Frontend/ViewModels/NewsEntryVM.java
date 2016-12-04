package de.fhbielefeld.scl.KINewsBoard.WebService.Frontend.ViewModels;

import de.fhbielefeld.scl.KINewsBoard.DataLayer.DataModels.AnalyzerResult;
import de.fhbielefeld.scl.KINewsBoard.DataLayer.DataModels.NewsEntry;
import de.fhbielefeld.scl.KINewsBoard.WebService.Shared.ViewModels.CrawlerBaseModel;
import de.fhbielefeld.scl.KINewsBoard.WebService.Shared.ViewModels.NewsEntryBaseModel;

/**
 * Created by cem on 21.11.16.
 */
public class NewsEntryVM extends NewsEntryBaseModel {
    private CrawlerBaseModel crawler;
    private int analyzerResult;
    private int rating;


    public NewsEntryVM() {

    }

    public NewsEntryVM(NewsEntry newsEntry) {
        super(newsEntry);

        crawler = new CrawlerBaseModel(newsEntry.getCrawler());

        for (AnalyzerResult res :
                newsEntry.getAnalyzerResults()) {
            analyzerResult += res.getValue();
        }

        if (newsEntry.getAnalyzerResults().size() > 0)
            analyzerResult /= newsEntry.getAnalyzerResults().size();

        rating = newsEntry.getRating();
    }

    public CrawlerBaseModel getCrawler() {
        return crawler;
    }

    public void setCrawler(CrawlerBaseModel crawler) {
        this.crawler = crawler;
    }

    public int getAnalyzerResult() {
        return analyzerResult;
    }

    public void setAnalyzerResult(int analyzerResult) {
        this.analyzerResult = analyzerResult;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
