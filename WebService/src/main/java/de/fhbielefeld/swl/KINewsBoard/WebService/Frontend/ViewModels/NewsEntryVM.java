package de.fhbielefeld.swl.KINewsBoard.WebService.Frontend.ViewModels;

import de.fhbielefeld.swl.KINewsBoard.WebService.Shared.ViewModels.CrawlerBaseModel;
import de.fhbielefeld.swl.KINewsBoard.WebService.Shared.ViewModels.NewsEntryBaseModel;
import de.fhbielefeld.swl.KINewsBoard.DataLayer.DataModels.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Die Klasse <i>NewsEntryVM</i> stellt ein Datentransferobjekt dar und ruft die Daten eines Nachrichteneintrages aus der Datenbank ab.
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

    /**
     * Ruft den Crawler des Nachrichteneintrages ab.
     *
     * @return Der Crawler des Nachrichteneintrages
     */
    public CrawlerBaseModel getCrawler() {
        return crawler;
    }

    /**
     * Legt den Crawler des Nachrichteneintrages fest.
     *
     * @param crawler Der festzulegende Crawler
     */
    public void setCrawler(CrawlerBaseModel crawler) {
        this.crawler = crawler;
    }

    /**
     * Ruft das Analyseergebnis des Nachrichteneintrages ab.
     *
     * @return Das Analyseergebnis des Nachrichteneintrages
     */
    public int getAnalyzerResult() {
        return analyzerResult;
    }

    /**
     * Legt das Analyseergebnis zu dem Nachrichteneintrag fest.
     *
     * @param analyzerResult Das festzulegende Analyseergebnis
     */
    public void setAnalyzerResult(int analyzerResult) {
        this.analyzerResult = analyzerResult;
    }

    /**
     * Ruft die Bewertung des Nachrichteneintrages ab.
     *
     * @return Bewertung des Nachrichteneintrages
     */
    public int getRating() {
        return rating;
    }

    /**
     * Legt die Bewertung zu diesem Nachrichteneintrag fest.
     *
     * @param rating Die festzulegende Bewertung
     */
    public void setRating(int rating) {
        this.rating = rating;
    }
}
