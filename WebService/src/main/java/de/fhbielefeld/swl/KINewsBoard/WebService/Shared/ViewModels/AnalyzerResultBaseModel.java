package de.fhbielefeld.swl.KINewsBoard.WebService.Shared.ViewModels;

import de.fhbielefeld.swl.KINewsBoard.DataLayer.DataModels.AnalyzerResult;
import de.fhbielefeld.swl.KINewsBoard.DataLayer.DataModels.AnalyzerSentenceResult;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.json.bind.annotation.JsonbTransient;
import javax.json.bind.annotation.JsonbDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Die Klasse <i>AnalyzerResultBaseModel</i> stellt ein Datentransferobjekt dar und ruft die Basisinformationen eines Analyseergebnisses aus der Datenbank ab.
 */
public class AnalyzerResultBaseModel {
    private Date date;
    private int value;
    private List<AnalyzerSentenceResultModel> sentenceResults;

    public AnalyzerResultBaseModel() {

    }

    public AnalyzerResultBaseModel(AnalyzerResult result) {
        date = result.getDate();
        value = result.getValue();
        sentenceResults = result.getAnalyzerSentenceResult().stream().map(AnalyzerSentenceResultModel::new).collect(Collectors.toList());
    }

    /**
     * Ruft das Datum des Analyseergebnisses ab.
     *
     * @return Das Datum des Analyseergebnisses
     */
    @JsonbDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
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
    public List<AnalyzerSentenceResultModel> getSentenceResults() {
        return sentenceResults;
    }

    /**
     * Legt die Analyseergebnisse zu den einzelnen Sätzen fest.
     *
     * @param sentenceResults Die festzulegenden Analyseergebnisse der einzelnen Sätzen
     */
    public void setSentenceResults(List<AnalyzerSentenceResultModel> sentenceResults) {
        this.sentenceResults = sentenceResults;
    }

    /**
     * Kopiert die Daten des Datentransferobjektes und erstellt anhand dessen ein neues Analyseergebnis.
     *
     * @return Analyseergebnis mit den kopierten Daten auf Basis des Datentransferobjektes
     */
    @JsonbTransient
    public AnalyzerResult getAnalyzerResult() {
        AnalyzerResult model = new AnalyzerResult();

        model.setDate(getDate());
        model.setValue(getValue());

        getSentenceResults().stream().map(AnalyzerSentenceResultModel::getSentenceResult).forEach(sentence -> model.addAnalyzerSentenceResult(sentence));

        return model;
    }
}
