package de.fhbielefeld.scl.KINewsBoard.WebService.Frontend.ViewModels;

import de.fhbielefeld.scl.KINewsBoard.DataLayer.DataModels.*;
import de.fhbielefeld.scl.KINewsBoard.WebService.Shared.ViewModels.CrawlerBaseModel;
import de.fhbielefeld.scl.KINewsBoard.WebService.Shared.ViewModels.NewsEntryBaseModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
        this(newsEntry, null);
    }

    public NewsEntryVM(NewsEntry newsEntry, View view) {
        super(newsEntry);

        crawler = new CrawlerBaseModel(newsEntry.getCrawler());

        List<Integer> filters = new ArrayList<>();

        if (view != null) {
            filters.addAll(view.getGroupSets()
                    .stream()
                    .map(GroupSet::getAnalyzers)
                    .flatMap(Set::stream)
                    .map(Analyzer::getId)
                    .collect(Collectors.toList()));
        }

        int count = 0;
        for (AnalyzerResult res : newsEntry.getAnalyzerResults()) {
            if (filters.isEmpty() || filters.contains(res.getAnalyzer().getId())) {
                analyzerResult += res.getValue();
                count++;
            }
        }

        if (count > 0)
            analyzerResult /= count;

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
