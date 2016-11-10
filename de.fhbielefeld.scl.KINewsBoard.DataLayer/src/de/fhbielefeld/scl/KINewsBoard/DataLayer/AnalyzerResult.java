package de.fhbielefeld.scl.KINewsBoard.DataLayer;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by cem on 03.11.16.
 */
@Entity
public class AnalyzerResult {
    private Analyzer analyzer;
    private NewsEntry newsEntry;
    private Date date;
    private int value;

    @Id
    @OneToMany
    public Analyzer getAnalyzer() {
        return analyzer;
    }

    public void setAnalyzer(Analyzer analyzer) {
        this.analyzer = analyzer;
    }

    @Id
    @OneToMany
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
}
