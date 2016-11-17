package de.fhbielefeld.scl.KINewsBoard.DataLayer.DataModels;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * Created by cem on 29.10.16.
 */
@Entity
@NamedQuery(name = "NewsEntry.findAll", query = "select n from NewsEntry n")
public class NewsEntry {
    private String id;
    private Crawler crawler;
    private String title;
    private String image;
    private String content;
    private String source;
    private String url;
    private Date date;
    private Set<AnalyzerResult> analyzerResults;

    @Id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    public Crawler getCrawler() {
        return crawler;
    }

    public void setCrawler(Crawler crawler) {
        this.crawler = crawler;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Temporal(TemporalType.TIME)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @OneToMany(mappedBy = "newsEntry")
    public Set<AnalyzerResult> getAnalyzerResults() {
        return analyzerResults;
    }

    public void setAnalyzerResults(Set<AnalyzerResult> analyzerResults) {
        this.analyzerResults = analyzerResults;
    }
}
