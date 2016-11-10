package de.fhbielefeld.scl.KINewsBoard.DataLayer;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by cem on 03.11.16.
 */
@Entity
public class Group {
    private int id;
    private String name;
    private Set<View> views;
    private Set<Analyzer> analyzers;

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


    @ManyToMany
    public Set<View> getViews() {
        return views;
    }

    public void setViews(Set<View> views) {
        this.views = views;
    }

    @ManyToMany
    public Set<Analyzer> getAnalyzers() {
        return analyzers;
    }

    public void setAnalyzers(Set<Analyzer> analyzers) {
        this.analyzers = analyzers;
    }
}
