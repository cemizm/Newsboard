package de.fhbielefeld.swl.KINewsBoard.WebService.Frontend.ViewModels;

import de.fhbielefeld.swl.KINewsBoard.DataLayer.DataModels.AnalyzerResult;
import de.fhbielefeld.swl.KINewsBoard.WebService.Shared.ViewModels.AnalyzerBaseModel;
import de.fhbielefeld.swl.KINewsBoard.WebService.Shared.ViewModels.AnalyzerResultBaseModel;

/**
 * Die Klasse <i>AnalyzerResultVM</i> stellt ein Datentransferobjekt dar und ruft die Daten eines Analyseergebnisses aus der Datenbank ab.
 */
public class AnalyzerResultVM extends AnalyzerResultBaseModel {
    private AnalyzerBaseModel analyzer;

    public AnalyzerResultVM() {

    }

    public AnalyzerResultVM(AnalyzerResult result) {
        super(result);

        analyzer = new AnalyzerBaseModel(result.getAnalyzer());
    }

    /**
     * Ruft den Analyzer zu dem Analyseergebnis ab.
     *
     * @return Der Analyzer zu dem Analyseergebnis
     */
    public AnalyzerBaseModel getAnalyzer() {
        return analyzer;
    }

    /**
     * Legt den Analyzer des Analyseergebnisses fest.
     *
     * @param analyzer Der festzulegende Analyzer
     */
    public void setAnalyzer(AnalyzerBaseModel analyzer) {
        this.analyzer = analyzer;
    }

}
