package de.fhbielefeld.scl.KINewsBoard.WebService.Frontend.ViewModels;

import de.fhbielefeld.scl.KINewsBoard.DataLayer.DataModels.AnalyzerResult;
import de.fhbielefeld.scl.KINewsBoard.WebService.Shared.ViewModels.AnalyzerBaseModel;
import de.fhbielefeld.scl.KINewsBoard.WebService.Shared.ViewModels.AnalyzerResultBaseModel;

/**
 * Created by cem on 21.11.16.
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
