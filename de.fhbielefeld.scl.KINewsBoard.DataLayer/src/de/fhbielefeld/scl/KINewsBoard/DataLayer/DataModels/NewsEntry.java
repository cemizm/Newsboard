package de.fhbielefeld.scl.KINewsBoard.DataLayer.DataModels;

import org.apache.lucene.analysis.core.LowerCaseFilterFactory;
import org.apache.lucene.analysis.snowball.SnowballPorterFilterFactory;
import org.apache.lucene.analysis.standard.StandardTokenizerFactory;
import org.hibernate.search.annotations.*;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Parameter;

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
        @NamedQuery(name = "NewsEntry.getNewsEntriesByViewId", query = "select n from NewsEntry n left join n.crawler c left join c.views v where v.id = :viewId order by n.date desc")
})
@AnalyzerDef(name = "newsanalyzer",
        tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class),
        filters = {
                @TokenFilterDef(factory = LowerCaseFilterFactory.class),
                @TokenFilterDef(factory = SnowballPorterFilterFactory.class, params = {
                        @Parameter(name = "language", value = "German")
                }),
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

    /**
     * Ruft die Id des Nachrichteneintrages ab.
     *
     * @return Die Id des Nachrichteneintrages
     */
    @Id
    public String getId() {
        return id;
    }

    /**
     * Legt die Id des Nachrichteneintrages fest.
     *
     * @param id Die festzulegende Id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Ruft den Crawler ab, der diesen Nachrichteneintrag geliefert hat.
     *
     * @return Der Crawler des Nachrichteneintrages
     */
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER, optional = false)
    public Crawler getCrawler() {
        return crawler;
    }

    /**
     * Legt den Crawler fest, die diesen Nachrichteneintrag geliefert hat.
     *
     * @param crawler Der festzulegende Crawler
     */
    public void setCrawler(Crawler crawler) {
        this.crawler = crawler;
    }

    /**
     * Ruft den Titel des Nachrichteneintrages ab.
     *
     * @return Der Titel des Nachrichteneintrages
     */
    @Column(length = 1024)
    @Field(index = Index.YES, analyze = Analyze.YES, store = Store.YES)
    @Analyzer(definition = "newsanalyzer")
    public String getTitle() {
        return title;
    }

    /**
     * Legt den Titel des Nachrichteneintrages fest.
     *
     * @param title Der festzulegende Nachrichteneintrag
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Ruft das Bild des Nachrichteneintrages ab.
     *
     * @return Das Bild des Nachrichteneintrages
     */
    @Column(length = 512)
    public String getImage() {
        return image;
    }

    /**
     * Legt das Bild des Nachrichteneintrages fest.
     *
     * @param image Das festzulegende Bild
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * Ruft den Inhalt des Nachrichteneintrages ab.
     *
     * @return Der Inhalt des Nachrichteneintrages
     */
    @Column(length = 10485760, nullable = false)
    @Field(index = Index.YES, analyze = Analyze.YES, store = Store.YES)
    @Analyzer(definition = "newsanalyzer")
    public String getContent() {
        return content;
    }

    /**
     * Legt den Inhalt des Nachrichteneintrages fest.
     *
     * @param content Der festzulegende Inhalt
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Ruft die Quelle des Nachrichteneintrages ab.
     *
     * @return Die Quelle des Nachrichteneintrages
     */
    public String getSource() {
        return source;
    }

    /**
     * Legt die Quelle des Nachrichteneintrages fest.
     *
     * @param source Die festzulegende Quelle
     */
    public void setSource(String source) {
        this.source = source;
    }

    /**
     * Ruft die URL des Nachrichteneintrages ab.
     *
     * @return Die URL des Nachrichteneintrages
     */
    @Column(length = 512)
    public String getUrl() {
        return url;
    }

    /**
     * Legt die URL des Nachrichteneintrages fest.
     *
     * @param url Die festzulegende URL
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Ruft das Datum des Nachrichteneintrages ab.
     *
     * @return Das Datum des Nachrichteneintrages
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Field(index = Index.YES, analyze = Analyze.NO, store = Store.YES)
    @SortableField
    @DateBridge(resolution = Resolution.DAY)
    public Date getDate() {
        return date;
    }

    /**
     * Legt das Datum des Nachrichteneintrages fest.
     *
     * @param date Das festzulegende Datum
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Ruft die Bewertung des Nachrichteneintrages ab.
     *
     * @return Die Bewertung des Nachrichteneintrages
     */
    @Field(index = Index.YES, analyze = Analyze.NO, store = Store.YES)
    @SortableField
    public int getRating() {
        return rating;
    }

    /**
     * Legt die Bewertung des Nachrichteneintrages fest.
     *
     * @param vote Die festzulegende Bewertung
     */
    public void setRating(int vote) {
        this.rating = vote;
    }

    /**
     * Ruft die Analyseergebnisse ab, die diesem Nachrichteneintrag zugeordnet sind.
     *
     * @return Liste der Analyseergebnisse
     */
    @OneToMany(mappedBy = "newsEntry", cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    public Set<AnalyzerResult> getAnalyzerResults() {
        return analyzerResults;
    }

    /**
     * Legt die Analyseergebnisse fest, die diesem Nachrichteneintrag zugeordnet werden sollen.
     *
     * @param analyzerResults Liste der zuzuordnenden Analyseergebnisse
     */
    public void setAnalyzerResults(Set<AnalyzerResult> analyzerResults) {
        this.analyzerResults = analyzerResults;
    }
}
