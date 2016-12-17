package de.fhbielefeld.scl.KINewsBoard.WebService.Frontend.ViewModels;

import de.fhbielefeld.scl.KINewsBoard.DataLayer.DataModels.Analyzer;
import de.fhbielefeld.scl.KINewsBoard.DataLayer.DataModels.GroupSet;
import de.fhbielefeld.scl.KINewsBoard.DataLayer.DataModels.NewsEntry;
import de.fhbielefeld.scl.KINewsBoard.DataLayer.DataModels.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
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
        this(newsEntry, null);
    }

    public NewsEntryDetailVM(NewsEntry newsEntry, View view) {
        super(newsEntry, view);

        List<Integer> filters = new ArrayList<>();

        if (view != null) {
            filters.addAll(view.getGroupSets()
                    .stream()
                    .map(GroupSet::getAnalyzers)
                    .flatMap(Set::stream)
                    .map(Analyzer::getId)
                    .collect(Collectors.toList()));
        }

        analyzerResults = newsEntry.getAnalyzerResults()
                .stream()
                .filter(a -> view == null || view.getGroupSets().size() == 0 || filters.contains(a.getAnalyzer().getId()))
                .map(AnalyzerResultVM::new)
                .collect(Collectors.toList());
    }

    public List<AnalyzerResultVM> getAnalyzerResults() {
        return analyzerResults;
    }

    public void setAnalyzerResults(List<AnalyzerResultVM> analyzerResults) {
        this.analyzerResults = analyzerResults;
    }
}
