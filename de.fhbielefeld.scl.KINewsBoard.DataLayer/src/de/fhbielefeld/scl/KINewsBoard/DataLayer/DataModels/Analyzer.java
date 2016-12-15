package de.fhbielefeld.scl.KINewsBoard.DataLayer.DataModels;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by cem on 03.11.16.
 */
@Entity
@NamedQueries
        ({
                @NamedQuery(name = "Analyzer.findAll", query = "select n from Analyzer n"),
                @NamedQuery(name = "Analyzer.findByToken", query = "select n from Analyzer n where n.token=:token")
        })
public class Analyzer {
    private int id;
    private String token;
    private String name;
    private boolean disabled;
    private Set<GroupSet> groupSets;
    private Set<AnalyzerResult> results;

    public Analyzer() {
        groupSets = new HashSet<>();
        results = new HashSet<>();
    }

    /**
     * Ruft die Id des Analyzers ab.
     *
     * @return Die Id des Analyzers
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    /**
     * Legt die Id des Analyzers fest.
     *
     * @param id Die festzulegende Id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Ruft den Token des Analyzers ab.
     *
     * @return Der Token des Analyzers
     */
    @Column(unique = true, nullable = false)
    public String getToken() {
        return token;
    }

    /**
     * Legt den Token des Analyzer fest.
     *
     * @param token Der festzulegende Token
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * Ruft den Namen des Analyzers ab.
     *
     * @return Der Name des Analyzers
     */
    @Column(length = 50, nullable = false)
    public String getName() {
        return name;
    }

    /**
     * Legt den Namen des Analyzers fest.
     *
     * @param name Der festzulegende Name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Prüft den Aktivitätsstatus des Analyzers.
     *
     * @return <i>true</i>, wenn Analyzer deaktiviert ist
     */
    public boolean isDisabled() {
        return disabled;
    }

    /**
     * Legt den Aktivitätsstatus des Analyzers fest.
     *
     * @param disabled Der festzulegende Status
     */
    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    /**
     * Ruft die Gruppen ab, in denen sich der Analyzer befindet.
     *
     * @return Liste der Gruppen des Analyzers
     */
    @ManyToMany(mappedBy = "analyzers", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    public Set<GroupSet> getGroupSets() {
        return groupSets;
    }

    /**
     * Legt die Gruppen fest, in denen sich der Analyzer befinden soll.
     *
     * @param groupSets Liste der festzulegenden Gruppen
     */
    public void setGroupSets(Set<GroupSet> groupSets) {
        this.groupSets = groupSets;
    }

    /**
     * Ruft die Analyseergebnisse des Analyzers ab.
     *
     * @return Liste der Analyseergebnisse des Analyzers
     */
    @OneToMany(mappedBy = "analyzer", cascade = {CascadeType.ALL})
    public Set<AnalyzerResult> getResults() {
        return results;
    }

    /**
     * Legt die Analyseergebnisse des Analyzers fest.
     *
     * @param results Liste der festzulegenden Analyseergebnisse
     */
    public void setResults(Set<AnalyzerResult> results) {
        this.results = results;
    }
}
