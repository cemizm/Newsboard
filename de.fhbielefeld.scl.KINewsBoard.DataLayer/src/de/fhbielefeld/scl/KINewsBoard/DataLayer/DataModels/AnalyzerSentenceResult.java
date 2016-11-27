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

    @Id
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    public AnalyzerResult getAnalyzerResult() {
        return analyzerResult;
    }

    public void setAnalyzerResult(AnalyzerResult analyzerResult) {
        this.analyzerResult = analyzerResult;
    }

    @Id
    public int getCharStart() {
        return charStart;
    }

    public void setCharStart(int charStart) {
        this.charStart = charStart;
    }

    public int getCharEnd() {
        return charEnd;
    }

    public void setCharEnd(int end) {
        this.charEnd = end;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
