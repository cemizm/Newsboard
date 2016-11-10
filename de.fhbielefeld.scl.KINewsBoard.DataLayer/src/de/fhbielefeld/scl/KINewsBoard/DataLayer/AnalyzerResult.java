package de.fhbielefeld.scl.KINewsBoard.DataLayer;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by cem on 03.11.16.
 */
@Entity
public class AnalyzerResult {
    private int id;
    private Analyzer analyzer;
    private NewsEntry newsEntry;
    private Date date;
    private int value;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ManyToOne
    public Analyzer getAnalyzer() {
        return analyzer;
    }

    public void setAnalyzer(Analyzer analyzer) {
        this.analyzer = analyzer;
    }

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
}
