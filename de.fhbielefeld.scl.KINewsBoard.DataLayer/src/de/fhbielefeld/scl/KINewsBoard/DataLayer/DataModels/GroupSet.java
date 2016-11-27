package de.fhbielefeld.scl.KINewsBoard.DataLayer.DataModels;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by cem on 03.11.16.
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "GroupSet.findAll", query = "select n from GroupSet n"),
        @NamedQuery(name = "GroupSet.findByIds", query = "select n from GroupSet n where n.id in :ids")
})
public class GroupSet {
    private int id;
    private String name;
    private Set<View> views;
    private Set<Analyzer> analyzers;

    public GroupSet() {
        views = new HashSet<>();
        analyzers = new HashSet<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToMany(mappedBy = "groupSets", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    public Set<View> getViews() {
        return views;
    }

    public void setViews(Set<View> views) {
        this.views = views;
    }

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    public Set<Analyzer> getAnalyzers() {
        return analyzers;
    }

    public void setAnalyzers(Set<Analyzer> analyzers) {
        this.analyzers = analyzers;
    }

    public void addAnalyzer(Analyzer analyzer) {
        analyzers.add(analyzer);
        analyzer.getGroupSets().add(this);
    }

    public void removeAnalyzer(Analyzer analyzer) {
        analyzers.remove(analyzer);
        analyzer.getGroupSets().remove(this);
    }
}
