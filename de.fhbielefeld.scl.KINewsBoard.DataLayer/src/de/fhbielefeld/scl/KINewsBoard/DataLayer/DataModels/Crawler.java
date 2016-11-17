package de.fhbielefeld.scl.KINewsBoard.DataLayer.DataModels;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by cem on 02.11.16.
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

    public boolean isIgnoreAnalyzer() {
        return ignoreAnalyzer;
    }

    public void setIgnoreAnalyzer(boolean ignoreAnalyzer) {
        this.ignoreAnalyzer = ignoreAnalyzer;
    }

    @OneToMany(mappedBy = "crawler")
    public Set<NewsEntry> getEntries() {
        return entries;
    }

    public void setEntries(Set<NewsEntry> entries) {
        this.entries = entries;
    }


    @ManyToMany(mappedBy = "crawlers")
    public Set<View> getViews() {
        return views;
    }

    public void setViews(Set<View> views) {
        this.views = views;
    }
}
