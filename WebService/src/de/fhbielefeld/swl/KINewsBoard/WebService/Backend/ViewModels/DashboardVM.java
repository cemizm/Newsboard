package de.fhbielefeld.swl.KINewsBoard.WebService.Backend.ViewModels;

import de.fhbielefeld.swl.KINewsBoard.WebService.Shared.ViewModels.NewsEntryBaseModel;

import java.util.List;

/**
 * Die Klasse <i>DashboardVM</i> stellt ein Datentransferobjekt dar und ruft die Daten des Dashboards aus der Datenbank ab.
 */
public class DashboardVM {
    private int countNewsEntries;
    private int countAnalyzerEntries;
    private int countActiveAnalyzer;
    private int countActiveCrawler;

    private List<NewsEntryBaseModel> newsEntries;

    public DashboardVM() {

    }

    /**
     * Ruft die Zahl der Nachrichteneinträge im Dashboard ab.
     *
     * @return Zahl der Nachrichteneinträge im Dashboard
     */
    public int getCountNewsEntries() {
        return countNewsEntries;
    }

    /**
     * Legt die Zahl der Nachrichteneinträge im Dashboard fest.
     *
     * @param countNewsEntries Die festzulegende Anzahl der Nachrichteneinträge
     */
    public void setCountNewsEntries(int countNewsEntries) {
        this.countNewsEntries = countNewsEntries;
    }

    /**
     * Ruft die Zahl der analysierten Nachrichteneinrtäge im Dashboard.
     *
     * @return Anzahl der analysierten Nachrichteneinrtäge im Dashbaord
     */
    public int getCountAnalyzerEntries() {
        return countAnalyzerEntries;
    }

    /**
     * Legt die Zahl der analysierten Nachrichteneinrtäge im Dashboard fest.
     *
     * @param countAnalyzerEntries Die festzulegende Anzahl der analysierten Nachrichteneinrtäge
     */
    public void setCountAnalyzerEntries(int countAnalyzerEntries) {
        this.countAnalyzerEntries = countAnalyzerEntries;
    }

    /**
     * Ruft die Zahl der aktiven Analyzer im Dashboard ab.
     *
     * @return Anzahl der aktiven Analyzer
     */
    public int getCountActiveAnalyzer() {
        return countActiveAnalyzer;
    }

    /**
     * Legt die Zahl der aktiven Analyzer im Dashboard fest.
     *
     * @param countActiveAnalyzer Die festzulegende Anzahl der aktiven Analyzer
     */
    public void setCountActiveAnalyzer(int countActiveAnalyzer) {
        this.countActiveAnalyzer = countActiveAnalyzer;
    }

    /**
     * Ruft die Zahl der aktiven Crawler im Dashboard ab.
     *
     * @return Anzahl der aktiven Crawler im Dashboard
     */
    public int getCountActiveCrawler() {
        return countActiveCrawler;
    }

    /**
     * Legt die Anzahl der aktiven Crawler im Dashboard fest.
     *
     * @param countActiveCrawler Die festzulegende Anzahl der aktiven Crawler
     */
    public void setCountActiveCrawler(int countActiveCrawler) {
        this.countActiveCrawler = countActiveCrawler;
    }

    /**
     * Ruft die Nachrichteneinträge im Dashbaord ab.
     *
     * @return Liste der Nachrichteneinträge im Dashboard
     */
    public List<NewsEntryBaseModel> getNewsEntries() {
        return newsEntries;
    }

    /**
     * Legt die Nachrichteneinträge im Dashboard fest.
     *
     * @param newsEntries Liste der festzulegenden Nachrichteneinträge im Dashboard
     */
    public void setNewsEntries(List<NewsEntryBaseModel> newsEntries) {
        this.newsEntries = newsEntries;
    }
}
