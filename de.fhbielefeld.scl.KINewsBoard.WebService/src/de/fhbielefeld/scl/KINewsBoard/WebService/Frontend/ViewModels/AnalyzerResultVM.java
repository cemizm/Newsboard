package de.fhbielefeld.scl.KINewsBoard.WebService.Frontend.ViewModels;

import de.fhbielefeld.scl.KINewsBoard.DataLayer.DataModels.AnalyzerResult;
import de.fhbielefeld.scl.KINewsBoard.WebService.Shared.ViewModels.AnalyzerResultBaseModel;
import de.fhbielefeld.scl.KINewsBoard.WebService.Shared.ViewModels.AnalyzerBaseModel;

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

    public AnalyzerBaseModel getAnalyzer() {
        return analyzer;
    }

    public void setAnalyzer(AnalyzerBaseModel analyzer) {
        this.analyzer = analyzer;
    }

}
