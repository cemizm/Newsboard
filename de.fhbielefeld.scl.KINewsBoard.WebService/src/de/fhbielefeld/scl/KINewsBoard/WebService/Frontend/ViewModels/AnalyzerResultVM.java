package de.fhbielefeld.scl.KINewsBoard.WebService.Frontend.ViewModels;

import de.fhbielefeld.scl.KINewsBoard.BusinessLayer.Models.AnalyzerResultModel;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by cem on 21.11.16.
 */
public class AnalyzerResultVM {
    private AnalyzerVM analyzer;
    private Date date;
    private int value;
    private List<AnalyzerResultVM.SentenceResultVM> sentenceResults;

    public AnalyzerResultVM() {

    }

    public AnalyzerResultVM(AnalyzerResultModel model) {
        analyzer = new AnalyzerVM(model.getAnalyzer());
        date = model.getDate();
        value = model.getValue();
        sentenceResults = model.getSentenceResults().stream().map(SentenceResultVM::new).collect(Collectors.toList());
    }

    static class SentenceResultVM {
        private int charStart;
        private int charEnd;
        private int value;

        public SentenceResultVM() {

        }

        public SentenceResultVM(AnalyzerResultModel.SentenceResultModel result) {
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

    public AnalyzerVM getAnalyzer() {
        return analyzer;
    }

    public void setAnalyzer(AnalyzerVM analyzer) {
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

    public List<SentenceResultVM> getSentenceResults() {
        return sentenceResults;
    }

    public void setSentenceResults(List<SentenceResultVM> sentenceResults) {
        this.sentenceResults = sentenceResults;
    }
}
