package de.fhbielefeld.scl.KINewsBoard.DataLayer.DataModels;

import org.hibernate.search.annotations.*;
import org.hibernate.search.annotations.Index;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by cem on 29.10.16.
 */
@Indexed
@Entity
@NamedQueries({
        @NamedQuery(name = "NewsEntry.findAll", query = "select n from NewsEntry n order by n.date desc"),
        @NamedQuery(name = "NewsEntry.getNotAnalyzedNewsEntries", query = "select n from NewsEntry n left join n.analyzerResults ar on ar.analyzer.id = :analyzer where ar.newsEntry.id is null order by n.date desc"),
        @NamedQuery(name = "NewsEntry.getNewsEntriesByViewId", query = "select n from NewsEntry n left join n.crawler c left join c.views cv where cv.id = :viewId order by n.date desc")
})
public class NewsEntry {
    private String id;
    private Crawler crawler;
    private String title;
    private String image;
    private String content;
    private String source;
    private String url;
    private Date date;
    private int rating;
    private Set<AnalyzerResult> analyzerResults;

    public NewsEntry() {
        analyzerResults = new HashSet<>();
    }

    @Id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER, optional = false)
    public Crawler getCrawler() {
        return crawler;
    }

    public void setCrawler(Crawler crawler) {
        this.crawler = crawler;
    }

    @Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
    @Column(length = 1024)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(length = 512)
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Column(length = 10485760, nullable = false)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @Column(length = 512)
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Temporal(TemporalType.TIMESTAMP)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int vote) {
        this.rating = vote;
    }

    @OneToMany(mappedBy = "newsEntry", cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    public Set<AnalyzerResult> getAnalyzerResults() {
        return analyzerResults;
    }

    public void setAnalyzerResults(Set<AnalyzerResult> analyzerResults) {
        this.analyzerResults = analyzerResults;
    }
}
