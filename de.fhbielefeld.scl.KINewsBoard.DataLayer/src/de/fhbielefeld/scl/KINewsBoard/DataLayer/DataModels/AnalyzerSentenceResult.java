package de.fhbielefeld.scl.KINewsBoard.DataLayer.DataModels;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.io.Serializable;

/**
 * Created by azad- on 10.11.2016.
 */
@Entity
public class AnalyzerSentenceResult implements Serializable{
    private AnalyzerResult analyzerResult;
    private int start;
    private int end;
    private int value;

    @Id
    @ManyToOne
    public AnalyzerResult getAnalyzerResult() {
        return analyzerResult;
    }

    public void setAnalyzerResult(AnalyzerResult analyzerResult) {
        this.analyzerResult = analyzerResult;
    }

    @Id
    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
