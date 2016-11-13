package de.fhbielefeld.scl.KINewsBoard.DataLayer.DataModels;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by cem on 03.11.16.
 */
@Entity
public class Analyzer {
    private int id;
    private String token;
    private String name;
    private boolean disabled;
    private Set<GroupSet> groupSets;
    private Set<AnalyzerResult> results;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(unique = true)
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

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

    @ManyToMany(mappedBy = "analyzers")
    public Set<GroupSet> getGroupSets() {
        return groupSets;
    }

    public void setGroupSets(Set<GroupSet> groupSets) {
        this.groupSets = groupSets;
    }

    @OneToMany(mappedBy = "analyzer")
    public Set<AnalyzerResult> getResults() {
        return results;
    }

    public void setResults(Set<AnalyzerResult> results) {
        this.results = results;
    }
}
