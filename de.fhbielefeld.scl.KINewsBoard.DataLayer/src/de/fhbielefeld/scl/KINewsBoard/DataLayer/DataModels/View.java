package de.fhbielefeld.scl.KINewsBoard.DataLayer.DataModels;

import org.hibernate.search.annotations.*;
import org.hibernate.search.annotations.Index;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by cem on 03.11.16.
 */
@Indexed
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

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Field(name = "viewId", store = Store.YES, index = Index.YES)
    @NumericField
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(length = 50, nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(length = 1024)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    public Set<GroupSet> getGroupSets() {
        return groupSets;
    }

    public void setGroupSets(Set<GroupSet> groupSets) {
        this.groupSets = groupSets;
    }

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @ContainedIn
    public Set<Crawler> getCrawlers() {
        return crawlers;
    }

    public void setCrawlers(Set<Crawler> crawlers) {
        this.crawlers = crawlers;
    }

    public void addGroupSet(GroupSet groupSet) {
        groupSets.add(groupSet);
        groupSet.getViews().add(this);
    }

    public void removeGroupSet(GroupSet groupSet){
        groupSets.remove(groupSet);
        groupSet.getViews().remove(this);
    }

    public void addCrawler(Crawler crawler) {
        crawlers.add(crawler);
        crawler.getViews().add(this);
    }

    public void removeCrawler(Crawler crawler){
        crawlers.remove(crawler);
        crawler.getViews().remove(this);
    }
}
