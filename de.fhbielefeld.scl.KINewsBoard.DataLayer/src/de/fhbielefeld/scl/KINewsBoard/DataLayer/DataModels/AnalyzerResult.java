package de.fhbielefeld.scl.KINewsBoard.DataLayer.DataModels;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by cem on 03.11.16.
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "AnalyzerResult.findAll", query = "select n from AnalyzerResult n"),
        @NamedQuery(name = "AnalyzerResult.findByNewsToken", query = "select n from AnalyzerResult n where n.analyzer.id = :analyzerId and n.newsEntry.id = :newsId")
})
public class AnalyzerResult implements Serializable {
    private Analyzer analyzer;
    private NewsEntry newsEntry;
    private Date date;
    private int value;
    private Set<AnalyzerSentenceResult> analyzerSentenceResult;

    public AnalyzerResult() {
        analyzerSentenceResult = new HashSet<>();
    }

    @Id
    @ManyToOne
    public Analyzer getAnalyzer() {
        return analyzer;
    }

    public void setAnalyzer(Analyzer analyzer) {
        this.analyzer = analyzer;
    }

    @Id
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    public NewsEntry getNewsEntry() {
        return newsEntry;
    }

    public void setNewsEntry(NewsEntry newsEntry) {
        this.newsEntry = newsEntry;
    }

    @Temporal(TemporalType.TIMESTAMP)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @OneToMany(mappedBy = "analyzerResult", fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    public Set<AnalyzerSentenceResult> getAnalyzerSentenceResult() {
        return analyzerSentenceResult;
    }

    public void setAnalyzerSentenceResult(Set<AnalyzerSentenceResult> analyzerSentenceResult) {
        this.analyzerSentenceResult = analyzerSentenceResult;
    }

    public void addAnalyzerSentenceResult(AnalyzerSentenceResult sentence) {
        sentence.setAnalyzerResult(this);
        analyzerSentenceResult.add(sentence);
    }

    public void removeAnalyzerSentenceResult(AnalyzerSentenceResult sentence) {
        sentence.setAnalyzerResult(null);
        analyzerSentenceResult.remove(sentence);
    }
}
