package de.fhbielefeld.scl.KINewsBoard.DataLayer.DataModels;

import com.sun.tracing.dtrace.ModuleName;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * Created by cem on 03.11.16.
 */
@Entity
@NamedQuery(name="AnalyzerResult.findAll", query = "select n from AnalyzerResult n")
public class AnalyzerResult implements Serializable {
    private Analyzer analyzer;
    private NewsEntry newsEntry;
    private Date date;
    private int value;
    private Set<AnalyzerSentenceResult> analyzerSentenceResult;

    @Id
    @ManyToOne
    public Analyzer getAnalyzer() {
        return analyzer;
    }

    public void setAnalyzer(Analyzer analyzer) {
        this.analyzer = analyzer;
    }

    @Id
    @ManyToOne
    public NewsEntry getNewsEntry() {
        return newsEntry;
    }

    public void setNewsEntry(NewsEntry newsEntry) {
        this.newsEntry = newsEntry;
    }

    @Temporal(TemporalType.DATE)
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

    @OneToMany(mappedBy = "analyzerResult")
    public Set<AnalyzerSentenceResult> getAnalyzerSentenceResult() {
        return analyzerSentenceResult;
    }

    public void setAnalyzerSentenceResult(Set<AnalyzerSentenceResult> analyzerSentenceResult) {
        this.analyzerSentenceResult = analyzerSentenceResult;
    }
}
