package de.fhbielefeld.swl.KINewsBoard.WebService.Shared.ViewModels;

import de.fhbielefeld.swl.KINewsBoard.DataLayer.DataModels.AnalyzerSentenceResult;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.json.bind.annotation.JsonbTransient;

public class AnalyzerSentenceResultModel {
        private int charStart;
        private int charEnd;
        private int value;

        public AnalyzerSentenceResultModel() {

        }

        public AnalyzerSentenceResultModel(AnalyzerSentenceResult result) {
            charStart = result.getCharStart();
            charEnd = result.getCharEnd();
            value = result.getValue();
        }

        @Min(0)
        public int getCharStart() {
            return charStart;
        }

        public void setCharStart(int charStart) {
            this.charStart = charStart;
        }

        @Min(0)
        public int getCharEnd() {
            return charEnd;
        }

        public void setCharEnd(int charEnd) {
            this.charEnd = charEnd;
        }

        @Min(-100)
        @Max(100)
        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        @JsonbTransient
        public AnalyzerSentenceResult getSentenceResult() {
            AnalyzerSentenceResult model = new AnalyzerSentenceResult();
            model.setCharStart(getCharStart());
            model.setCharEnd(getCharEnd());
            model.setValue(getValue());
            return model;
        }
    }