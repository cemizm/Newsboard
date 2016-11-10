package de.fhbielefeld.scl.KINewsBoard.DataLayer;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by cem on 02.11.16.
 */
@Entity
public class Crawler {
    private int id;
    private String token;
    private String name;
    private boolean disabled;
    private Set<NewsEntry> entries;

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

    @OneToMany
    public Set<NewsEntry> getEntries() {
        return entries;
    }

    public void setEntries(Set<NewsEntry> entries) {
        this.entries = entries;
    }
}
