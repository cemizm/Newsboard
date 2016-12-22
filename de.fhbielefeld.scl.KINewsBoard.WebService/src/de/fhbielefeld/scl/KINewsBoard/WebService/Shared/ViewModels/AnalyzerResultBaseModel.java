package de.fhbielefeld.scl.KINewsBoard.WebService.Shared.ViewModels;

import de.fhbielefeld.scl.KINewsBoard.DataLayer.DataModels.AnalyzerResult;
import de.fhbielefeld.scl.KINewsBoard.DataLayer.DataModels.AnalyzerSentenceResult;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Die Klasse <i>AnalyzerResultBaseModel</i> stellt ein Datentransferobjekt dar und ruft die Basisinformationen eines Analyseergebnisses aus der Datenbank ab.
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

    /**
     * Ruft das Datum des Analyseergebnisses ab.
     *
     * @return Das Datum des Analyseergebnisses
     */
    public Date getDate() {
        return date;
    }

    /**
     * Legt das Datum des Analyseergebnisses fest.
     *
     * @param date Das festzulegende Datum des Analyseergebnisses
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Ruft die Bewertung des Analyseergebnisses ab.
     *
     * @return Die Bewertung des Analyseergebnisses
     */
    @Min(-100)
    @Max(100)
    @NotNull
    public int getValue() {
        return value;
    }

    /**
     * Legt die Bewertung des Analyseergebnisses fest.
     *
     * @param value Die festzulegende Bewertung des Analyseergebnisses
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * Ruft die Analyseergebnisse zu den einzelnen Sätzen ab.
     *
     * @return Liste der Analyseergebnisse der einzelnen Sätze
     */
    @Valid
    public List<SentenceResultVM> getSentenceResults() {
        return sentenceResults;
    }

    /**
     * Legt die Analyseergebnisse zu den einzelnen Sätzen fest.
     *
     * @param sentenceResults Die festzulegenden Analyseergebnisse der einzelnen Sätzen
     */
    public void setSentenceResults(List<SentenceResultVM> sentenceResults) {
        this.sentenceResults = sentenceResults;
    }

    /**
     * Kopiert die Daten des Datentransferobjektes und erstellt anhand dessen ein neues Analyseergebnis.
     *
     * @return Analyseergebnis mit den kopierten Daten auf Basis des Datentransferobjektes
     */
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

        public AnalyzerSentenceResult getSentenceResult() {
            AnalyzerSentenceResult model = new AnalyzerSentenceResult();
            model.setCharStart(getCharStart());
            model.setCharEnd(getCharEnd());
            model.setValue(getValue());
            return model;
        }
    }
}
