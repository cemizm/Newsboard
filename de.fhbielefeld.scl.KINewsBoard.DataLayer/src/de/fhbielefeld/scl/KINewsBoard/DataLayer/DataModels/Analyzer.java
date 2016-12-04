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

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(unique = true, nullable = false)
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Column(length = 50, nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    @ManyToMany(mappedBy = "analyzers", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    public Set<GroupSet> getGroupSets() {
        return groupSets;
    }

    public void setGroupSets(Set<GroupSet> groupSets) {
        this.groupSets = groupSets;
    }

    @OneToMany(mappedBy = "analyzer", cascade = {CascadeType.ALL})
    public Set<AnalyzerResult> getResults() {
        return results;
    }

    public void setResults(Set<AnalyzerResult> results) {
        this.results = results;
    }
}
