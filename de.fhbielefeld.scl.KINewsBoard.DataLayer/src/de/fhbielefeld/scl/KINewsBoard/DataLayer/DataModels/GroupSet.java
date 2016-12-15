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

    /**
     * Ruft die Id der Gruppe ab.
     *
     * @return Die Id der Gruppe
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    /**
     * Legt die Id der Gruppe fest.
     *
     * @param id Die festzulegende Id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Ruft den Namen der Gruppe ab.
     *
     * @return Der Name der Gruppe
     */
    @Column(length = 50)
    public String getName() {
        return name;
    }

    /**
     * Legt den Namen der Ggruppe fest.
     *
     * @param name Der festzulegende Name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Ruft die Ansichten ab, denen die Gruppe zugeordnet ist.
     *
     * @return Liste der zugeordneten Ansichten
     */
    @ManyToMany(mappedBy = "groupSets", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    public Set<View> getViews() {
        return views;
    }

    /**
     * Legt die Ansichten fest, denen die Gruppe zugeordnet ist.
     *
     * @param views liste der Ansichten, die zugeordnet werden sollen
     */
    public void setViews(Set<View> views) {
        this.views = views;
    }

    /**
     * Ruft die der Gruppe zugeordneten Analyzer ab.
     *
     * @return Liste der zugewiesenen Analyzer
     */
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.EAGER)
    public Set<Analyzer> getAnalyzers() {
        return analyzers;
    }

    /**
     * Legt die Analyzer fest, die der Gruppe zugeordnet werden sollen.
     *
     * @param analyzers Liste der zuzuordnenden Analyzer
     */
    public void setAnalyzers(Set<Analyzer> analyzers) {
        this.analyzers = analyzers;
    }

    /**
     * Fügt einen Analyzer der Gruppe hinzu.
     *
     * @param analyzer Der hinzuzufügende Analyzer
     */
    public void addAnalyzer(Analyzer analyzer) {
        analyzers.add(analyzer);
        analyzer.getGroupSets().add(this);
    }

    /**
     * Entfernt einen Analyzer aus der Gruppe.
     *
     * @param analyzer Der zu entfernende Analyzer
     */
    public void removeAnalyzer(Analyzer analyzer) {
        analyzers.remove(analyzer);
        analyzer.getGroupSets().remove(this);
    }
}
