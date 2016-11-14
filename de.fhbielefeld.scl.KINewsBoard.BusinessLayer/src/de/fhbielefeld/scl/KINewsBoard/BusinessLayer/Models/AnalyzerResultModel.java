package de.fhbielefeld.scl.KINewsBoard.BusinessLayer.Models;

import de.fhbielefeld.scl.KINewsBoard.DataLayer.DataModels.AnalyzerResult;
import de.fhbielefeld.scl.KINewsBoard.DataLayer.DataModels.AnalyzerSentenceResult;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by cem on 10.11.16.
 */
public class AnalyzerResultModel {
    private AnalyzerModel analyzer;
    private Date date;
    private int value;
    private List<SentenceResultModel> sentenceResults;

    public AnalyzerResultModel() {
        sentenceResults = new ArrayList<>();
    }

    public AnalyzerResultModel(AnalyzerResult result) {
        this();

        analyzer = new AnalyzerModel(result.getAnalyzer());
        date = result.getDate();
        value = result.getValue();

        sentenceResults.addAll(result.getAnalyzerSentenceResult()
                .stream()
                .map(SentenceResultModel::new)
                .collect(Collectors.toList()));
    }

    public AnalyzerModel getAnalyzer() {
        return analyzer;
    }

    public void setAnalyzer(AnalyzerModel analyzer) {
        this.analyzer = analyzer;
    }

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

    static class SentenceResultModel {
        private int charStart;
        private int charEnd;
        private int value;

        public SentenceResultModel() {

        }

        public SentenceResultModel(AnalyzerSentenceResult result) {
            charStart = result.getCharStart();
            charEnd = result.getCharEnd();
            value = result.getValue();
        }

        public int getCharStart() {
            return charStart;
        }

        public void setCharStart(int charStart) {
            this.charStart = charStart;
        }

        public int getCharEnd() {
            return charEnd;
        }

        public void setCharEnd(int charEnd) {
            this.charEnd = charEnd;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }
}
