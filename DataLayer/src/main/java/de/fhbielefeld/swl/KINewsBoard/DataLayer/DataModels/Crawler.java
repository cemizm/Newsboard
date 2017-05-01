package de.fhbielefeld.swl.KINewsBoard.DataLayer.DataModels;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Die Klasse <i>Crawler</i> repräsentiert einen Crawler im NewsBoardService.
 */
@Entity
@NamedQueries
        ({
                @NamedQuery(name = "Crawler.findAll", query = "select c from Crawler c"),
                @NamedQuery(name = "Crawler.findByToken", query = "select c from Crawler c where c.token=:token")
        })
public class Crawler {
    private int id;
    private String token;
    private String name;
    private boolean disabled;
    private boolean ignoreAnalyzer;
    private Set<NewsEntry> entries;
    private Set<View> views;
    private Set<Analyzer> analyzers;

    public Crawler() {
        entries = new HashSet<>();
        views = new HashSet<>();
        analyzers = new HashSet<>();
    }

    /**
     * Ruft die Id des Crawlers ab.
     *
     * @return Die Id des Crawlers
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    /**
     * Legt die Id des Crawlers fest.
     *
     * @param id Die festzulegende Id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Ruft den Token des Crawlers ab.
     *
     * @return Der Token des Crawlers
     */
    @Column(unique = true, nullable = false)
    public String getToken() {
        return token;
    }

    /**
     * Legt den Token des Crawlers fest.
     *
     * @param token der festzulegende Token
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * Ruft den Namen des Crawlers ab.
     *
     * @return Der Name des Crawlers
     */
    @Column(length = 50, nullable = false)
    public String getName() {
        return name;
    }

    /**
     * Legt den Namen des Crawlers fest.
     *
     * @param name Der festzulegende Name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Prüft den Aktivitätsstatus des Crawlers.
     *
     * @return <i>true</i>, wenn der Crawler deaktiviert ist
     */
    public boolean isDisabled() {
        return disabled;
    }

    /**
     * Legt den Status des Crawlers fest.
     *
     * @param disabled Der festzulegende Status
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
     * Ruft die Nachrichteneinträge ab, die der Crawler geliefert hat.
     *
     * @return Liste der Nachrichteneinträge des Crawlers
     */
    @OneToMany(mappedBy = "crawler", cascade = {CascadeType.ALL})
    public Set<NewsEntry> getEntries() {
        return entries;
    }

    /**
     * Legt die Nachrichteneinträge des Crawlers fest.
     *
     * @param entries Liste der festzulegenden Nachrichteneinträge
     */
    public void setEntries(Set<NewsEntry> entries) {
        this.entries = entries;
    }

    /**
     * Ruft die Ansichten des Crawlers ab.
     *
     * @return Liste der Ansichten des Crawlers
     */
    @ManyToMany(mappedBy = "crawlers", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    public Set<View> getViews() {
        return views;
    }

    /**
     * Legt die Ansichten des Crawlers fest.
     *
     * @param views Liste festzulegender Ansichten
     */
    public void setViews(Set<View> views) {
        this.views = views;
    }

    /**
     * Ruft die zugehörigen Analyzer ab
     *
     * @return Liste der zugehörigen Analyzer
     */
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    public Set<Analyzer> getAnalyzers() {
        return analyzers;
    }

    /**
     * Legt die zugehörigen Analyzer fest
     *
     * @param analyzers Liste der zugehörigen Analyzer
     */
    public void setAnalyzers(Set<Analyzer> analyzers) {
        this.analyzers = analyzers;
    }

    /**
     * Fügt einen Analyzer dem Crawler hinzu.
     *
     * @param analyzer Der hinzuzufügende Analyzer
     */
    public void addAnalyzer(Analyzer analyzer) {
        analyzers.add(analyzer);
        analyzer.getCrawlers().add(this);
    }

    /**
     * Entfernt einen Analyzer aus dem Crawler.
     *
     * @param analyzer Der zu entfernende Analyzer
     */
    public void removeAnalyzer(Analyzer analyzer) {
        analyzers.remove(analyzer);
        analyzer.getCrawlers().remove(this);
    }
}
