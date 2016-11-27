package de.fhbielefeld.scl.KINewsBoard.WebService.Shared.ViewModels;

import de.fhbielefeld.scl.KINewsBoard.DataLayer.DataModels.AnalyzerResult;
import de.fhbielefeld.scl.KINewsBoard.DataLayer.DataModels.AnalyzerSentenceResult;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by cem on 21.11.16.
 */
public class AnalyzerResultBaseModel {
    private Date date;
    private int value;
    private List<AnalyzerResultBaseModel.SentenceResultVM> sentenceResults;

    public AnalyzerResultBaseModel() {

    }

    public AnalyzerResultBaseModel(AnalyzerResult result) {
        date = result.getDate();
        value = result.getValue();
        sentenceResults = result.getAnalyzerSentenceResult().stream().map(SentenceResultVM::new).collect(Collectors.toList());
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

    public List<SentenceResultVM> getSentenceResults() {
        return sentenceResults;
    }

    public void setSentenceResults(List<SentenceResultVM> sentenceResults) {
        this.sentenceResults = sentenceResults;
    }

    public AnalyzerResult getAnalyzerResult() {
        AnalyzerResult model = new AnalyzerResult();

        model.setDate(getDate());
        model.setValue(getValue());

        getSentenceResults().stream().map(SentenceResultVM::getSentenceResult).forEach(sentence -> model.addAnalyzerSentenceResult(sentence));

        return model;
    }

    static class SentenceResultVM {
        private int charStart;
        private int charEnd;
        private int value;

        public SentenceResultVM() {

        }

        public SentenceResultVM(AnalyzerSentenceResult result) {
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

        public AnalyzerSentenceResult getSentenceResult() {
            AnalyzerSentenceResult model = new AnalyzerSentenceResult();
            model.setCharStart(getCharStart());
            model.setCharEnd(getCharEnd());
            model.setValue(getValue());
            return model;
        }
    }
}
