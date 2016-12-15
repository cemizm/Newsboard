package de.fhbielefeld.scl.KINewsBoard.DataLayer.DataModels;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.io.Serializable;

/**
 * Created by azad- on 10.11.2016.
 */
@Entity
public class AnalyzerSentenceResult implements Serializable {
    private AnalyzerResult analyzerResult;
    private int charStart;
    private int charEnd;
    private int value;

    /**
     * Ruft das Analyseergebnis des Analysesatzergebnisses ab.
     *
     * @return Das Analyseergebnis des Analysesatzergebnisses
     */
    @Id
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    public AnalyzerResult getAnalyzerResult() {
        return analyzerResult;
    }

    /**
     * Legt das Analyseergebnis des Analysesatzergebnisses fest.
     *
     * @param analyzerResult Das festzulegende Analyseergebnis
     */
    public void setAnalyzerResult(AnalyzerResult analyzerResult) {
        this.analyzerResult = analyzerResult;
    }

    /**
     * Ruft den Startindex des Analysesatzergebnisses ab.
     *
     * @return Der Startindex des Analysesatzergebnisses
     */
    @Id
    public int getCharStart() {
        return charStart;
    }

    /**
     * Legt den Startindex des Analysesatzergebnisses fest.
     *
     * @param charStart Der festzulegende Startindex
     */
    public void setCharStart(int charStart) {
        this.charStart = charStart;
    }

    /**
     * Ruft den Endindex des Analysesatzergebnisses ab.
     *
     * @return Der Endindex des Analysesatzergebnisses
     */
    public int getCharEnd() {
        return charEnd;
    }

    /**
     * Legt den Endindex des Analysesatzergebnisses fest.
     *
     * @param end Der festzulegende Endindex
     */
    public void setCharEnd(int end) {
        this.charEnd = end;
    }

    /**
     * Ruft den Wert des Analysesatzergebnisses ab.
     *
     * @return Der Wert des Analysesatzergebnisses
     */
    public int getValue() {
        return value;
    }

    /**
     * Legt den Wert des Analysesatzergebnisses fest.
     *
     * @param value Der fesetzulegende Wert
     */
    public void setValue(int value) {
        this.value = value;
    }
}
