package de.fhbielefeld.swl.KINewsBoard.WebService.Frontend.ViewModels;

import de.fhbielefeld.swl.KINewsBoard.DataLayer.DataModels.Analyzer;
import de.fhbielefeld.swl.KINewsBoard.DataLayer.DataModels.GroupSet;
import de.fhbielefeld.swl.KINewsBoard.DataLayer.DataModels.NewsEntry;
import de.fhbielefeld.swl.KINewsBoard.DataLayer.DataModels.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Die Klasse <i>NewsEntryDetailVM</i> stellt ein Datentransferobjekt dar und ruft die Details zu den Analyseergebnissen eines Nachrichteneintrages aus der Datenbank ab.
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

    /**
     * Ruft die Analyseergebnisse zu dem Nachrichteneintrag ab.
     *
     * @return Liste der Analyseergebnisse zu dem Nachrichteneintrag
     */
    public List<AnalyzerResultVM> getAnalyzerResults() {
        return analyzerResults;
    }

    /**
     * Legt die Analyseergebnisse zu dem Nachrichteneintrag fest.
     *
     * @param analyzerResults Liste der festzulegenden Analyseergebnisse
     */
    public void setAnalyzerResults(List<AnalyzerResultVM> analyzerResults) {
        this.analyzerResults = analyzerResults;
    }
}
