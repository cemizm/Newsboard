package de.fhbielefeld.scl.KINewsBoard.DataLayer.DataModels;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by cem on 03.11.16.
 */
@Entity
public class View {
    private int id;
    private String name;
    private int type;
    private int maxResults;
    private int lastDays;
    private Set<Group> groups;
    private Set<Crawler> crawlers;

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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getMaxResults() {
        return maxResults;
    }

    public void setMaxResults(int maxResults) {
        this.maxResults = maxResults;
    }

    public int getLastDays() {
        return lastDays;
    }

    public void setLastDays(int lastDays) {
        this.lastDays = lastDays;
    }

    @ManyToMany
    public Set<Group> getGroups() {
        return groups;
    }

    public void setGroups(Set<Group> groups) {
        this.groups = groups;
    }

    @ManyToMany
    public Set<Crawler> getCrawlers() {
        return crawlers;
    }

    public void setCrawlers(Set<Crawler> crawlers) {
        this.crawlers = crawlers;
    }
}
