package de.fhbielefeld.scl.KINewsBoard.WebService.Frontend.ViewModels;

import de.fhbielefeld.scl.KINewsBoard.DataLayer.DataModels.NewsEntry;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by cem on 21.11.16.
 */
public class NewsEntryDetailVM extends NewsEntryVM {
    private List<AnalyzerResultVM> analyzerResults;

    NewsEntryDetailVM() {
        super();
        analyzerResults = new ArrayList<>();
    }

    public NewsEntryDetailVM(NewsEntry newsEntry) {
        super(newsEntry);

        analyzerResults = newsEntry.getAnalyzerResults().stream().map(AnalyzerResultVM::new).collect(Collectors.toList());
    }

    public List<AnalyzerResultVM> getAnalyzerResults() {
        return analyzerResults;
    }

    public void setAnalyzerResults(List<AnalyzerResultVM> analyzerResults) {
        this.analyzerResults = analyzerResults;
    }
}
