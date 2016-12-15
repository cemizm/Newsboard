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
                @NamedQuery(name = "View.findAll", query = "select n from View n")
        })
public class View {
    private int id;
    private String name;
    private String description;
    private Set<GroupSet> groupSets;
    private Set<Crawler> crawlers;

    public View() {
        groupSets = new HashSet<>();
        crawlers = new HashSet<>();
    }

    /**
     * Ruft die Id der Ansicht ab.
     *
     * @return Die Id der Ansicht
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    /**
     * Legt die Id der Ansicht fest.
     *
     * @param id Die fesetzulegende Id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Ruft den Namen der Ansicht ab.
     *
     * @return Der name der Ansicht
     */
    @Column(length = 50, nullable = false)
    public String getName() {
        return name;
    }

    /**
     * Legt den Namen der Ansicht fest.
     *
     * @param name Der Name der Ansicht
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Ruft die Beschreibung der Ansicht ab.
     *
     * @return Die Beschreibung der Ansicht
     */
    @Column(length = 1024)
    public String getDescription() {
        return description;
    }

    /**
     * Legt die Beschreibung der Ansicht fest.
     *
     * @param description Die festzulegende Beschreibung
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Ruft die Gruppen ab, die der Ansicht zugeordnet sind.
     *
     * @return List der zugeordneten Gruppen
     */
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    public Set<GroupSet> getGroupSets() {
        return groupSets;
    }

    /**
     * Legt die Gruppen fest, die der Ansicht zugeordnet werden sollen.
     *
     * @param groupSets Liste der zuzuordnenden Gruppen
     */
    public void setGroupSets(Set<GroupSet> groupSets) {
        this.groupSets = groupSets;
    }

    /**
     * Ruft die Crawler der Ansicht ab.
     *
     * @return Liste der Crawler der Ansicht
     */
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    public Set<Crawler> getCrawlers() {
        return crawlers;
    }

    /**
     * Legt die Crawler der Ansicht fest.
     *
     * @param crawlers Liste der festzulegenden Crawler
     */
    public void setCrawlers(Set<Crawler> crawlers) {
        this.crawlers = crawlers;
    }

    /**
     * Fügt der Ansicht eine Gruppe hinzu.
     *
     * @param groupSet Die hinzuzufügende Gruppe
     */
    public void addGroupSet(GroupSet groupSet) {
        groupSets.add(groupSet);
        groupSet.getViews().add(this);
    }

    /**
     * Entfernt eine Gruppe aus der Ansicht.
     *
     * @param groupSet Die zu löschende Gruppe
     */
    public void removeGroupSet(GroupSet groupSet) {
        groupSets.remove(groupSet);
        groupSet.getViews().remove(this);
    }

    /**
     * Fügt der Ansicht einen Crawler hinzu.
     *
     * @param crawler Der hinzuzufügende Crawler
     */
    public void addCrawler(Crawler crawler) {
        crawlers.add(crawler);
        crawler.getViews().add(this);
    }

    /**
     * Entfernt einen Crawler aus der Ansicht.
     *
     * @param crawler Der zu entfernende Crawler
     */
    public void removeCrawler(Crawler crawler) {
        crawlers.remove(crawler);
        crawler.getViews().remove(this);
    }
}
