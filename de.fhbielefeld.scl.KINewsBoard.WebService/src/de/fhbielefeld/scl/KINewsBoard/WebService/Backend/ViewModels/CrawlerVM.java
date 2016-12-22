package de.fhbielefeld.scl.KINewsBoard.WebService.Backend.ViewModels;

import de.fhbielefeld.scl.KINewsBoard.DataLayer.DataModels.Crawler;
import de.fhbielefeld.scl.KINewsBoard.WebService.Shared.ViewModels.CrawlerBaseModel;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Die Klasse <i>CrawlerVM</i> stellt ein Datentransferobjekt dar und ruft die Daten eines Crawlers aus der Datenbank ab.
 */
public class CrawlerVM extends CrawlerBaseModel {
    private String token;
    private boolean disabled;
    private boolean ignoreAnalyzer;

    public CrawlerVM() {

    }

    public CrawlerVM(Crawler crawler) {
        super(crawler);
        token = crawler.getToken();
        disabled = crawler.isDisabled();
        ignoreAnalyzer = crawler.isIgnoreAnalyzer();
    }

    /**
     * Ruft den Authentifizierungstoken des Crawlers ab.
     *
     * @return Der Authentifizierungstoken des Crawlers
     */
    @Size(min = 10, max = 256)
    @NotNull
    public String getToken() {
        return token;
    }

    /**
     * Legt den Authentifizierungstoken des Crawlers fest.
     *
     * @param token Der festzulegende Authentifizierungstoken des Crawlers
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * Ruft den Aktivitätsstatus des Crawlers ab.
     *
     * @return <i>true</i>, wenn der Crawler deaktiviert ist
     */
    public boolean isDisabled() {
        return disabled;
    }

    /**
     * Legt den Aktivitätszustand des Crawlers fest.
     *
     * @param disabled Der festzulegende Aktivitätszustand des Crawlers
     */
    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    /**
     * Prüft, ob die Nachrichteneinträge, die durch den Crawler geliefert werden, von Analyzern ignoriert werden.
     *
     * @return <i>true</i>, wenn der Analyzer die Einträge ignoriert
     */
    public boolean isIgnoreAnalyzer() {
        return ignoreAnalyzer;
    }

    /**
     * Legt fest, ob der Analyzer die Nachrichteneinträge, die durch den Crawler geliefert werden, ignorieren soll.
     *
     * @param ignoreAnalyzer <i>true</i> wenn der Analyzer die Einträge ignorieren soll
     */
    public void setIgnoreAnalyzer(boolean ignoreAnalyzer) {
        this.ignoreAnalyzer = ignoreAnalyzer;
    }

    /**
     * Kopiert die Daten des Datentransferobjektes und erstellt anhand dessen einen neuen Crawler.
     *
     * @return Crawler mit den kopierten Daten auf Basis des Datentransferobjektes
     */
    public Crawler getCrawler() {
        Crawler crawler = new Crawler();
        crawler.setId(getId());
        crawler.setToken(getToken());
        crawler.setName(getName());
        crawler.setDisabled(isDisabled());
        crawler.setIgnoreAnalyzer(isIgnoreAnalyzer());
        return crawler;
    }
}
