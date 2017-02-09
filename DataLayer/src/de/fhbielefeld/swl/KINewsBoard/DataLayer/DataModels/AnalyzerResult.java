package de.fhbielefeld.swl.KINewsBoard.DataLayer.DataModels;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Die Klasse <i>AnalyzerResult</i> repräsentiert einen AnalyzerResult im NewsBoardService.
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

    /**
     * Ruft den Analyzer ab, der dieses Analyseergebnis geliefert hat.
     *
     * @return Der Analyzer des Analyseergebnisses
     */
    @Id
    @ManyToOne
    public Analyzer getAnalyzer() {
        return analyzer;
    }

    /**
     * Legt den Analyzer für das Analyseergebnis fest.
     *
     * @param analyzer Der festzulegende Analyzer für das Analyseergebnis
     */
    public void setAnalyzer(Analyzer analyzer) {
        this.analyzer = analyzer;
    }

    /**
     * Ruft den Nachrichteneintrag ab, dem dieses Analyseergebnis zugeordnet ist.
     *
     * @return Der Nachrichteneintrag für das Analyseergebnis
     */
    @Id
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    public NewsEntry getNewsEntry() {
        return newsEntry;
    }

    /**
     * Legt den Nachrichteneintrag für das Analyseergebnis fest.
     *
     * @param newsEntry Der festzulegende Nachrichteneintrag für das Analyseergebnis
     */
    public void setNewsEntry(NewsEntry newsEntry) {
        this.newsEntry = newsEntry;
    }

    /**
     * Ruft das Datum des Analyseergebnisses ab.
     *
     * @return Das Datum des Analyseergebnisses
     */
    @Temporal(TemporalType.TIMESTAMP)
    public Date getDate() {
        return date;
    }

    /**
     * Legt das Datum des Analyseergebnisses fest.
     *
     * @param date Das festzulegende Datum für das Analyseergebnis
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Ruft den Wert des Analyseergebnisses ab.
     *
     * @return Der Wert des Analyseergebnisses
     */
    public int getValue() {
        return value;
    }

    /**
     * Legt den Wert des Analyseergebnisses fest.
     *
     * @param value Der festzulegende Wert für das Analyseergebnis
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * Ruft die Ergebnisse der einzelnen Sätze des Analyseergebnisses ab.
     *
     * @return Liste der Analysesatzergebnisse des Analyseergebnisses
     */
    @OneToMany(mappedBy = "analyzerResult", fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    public Set<AnalyzerSentenceResult> getAnalyzerSentenceResult() {
        return analyzerSentenceResult;
    }

    /**
     * Legt die Ergebnisse der einzelnen Sätze des Analyseergebnisses fest.
     *
     * @param analyzerSentenceResult Liste der festzulegenden Analysesatzergebnisse für das Analyseergebnis
     */
    public void setAnalyzerSentenceResult(Set<AnalyzerSentenceResult> analyzerSentenceResult) {
        this.analyzerSentenceResult = analyzerSentenceResult;
    }

    /**
     * Fügt ein Analysesatzergebnis dem Analyseergebnis hinzu.
     *
     * @param sentence Das hinzuzufügende Analysesatzergebnis für das Analyseergebnis
     */
    public void addAnalyzerSentenceResult(AnalyzerSentenceResult sentence) {
        sentence.setAnalyzerResult(this);
        analyzerSentenceResult.add(sentence);
    }

    /**
     * Entfernt ein Analysesatzergebnis des Analyseergebnisses.
     *
     * @param sentence Das zu entfernende Analysesatzergebnis des Analyseergebnisses
     */
    public void removeAnalyzerSentenceResult(AnalyzerSentenceResult sentence) {
        sentence.setAnalyzerResult(null);
        analyzerSentenceResult.remove(sentence);
    }
}
