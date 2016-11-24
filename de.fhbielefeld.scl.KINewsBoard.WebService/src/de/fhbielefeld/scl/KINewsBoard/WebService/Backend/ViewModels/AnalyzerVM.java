package de.fhbielefeld.scl.KINewsBoard.WebService.Backend.ViewModels;

import de.fhbielefeld.scl.KINewsBoard.DataLayer.DataModels.Analyzer;
import de.fhbielefeld.scl.KINewsBoard.WebService.Shared.ViewModels.AnalyzerBaseModel;

/**
 * Created by cem on 14.11.16.
 */
public class AnalyzerVM extends AnalyzerBaseModel {

    private String token;
    private boolean disabled;

    public AnalyzerVM() {

    }

    public AnalyzerVM(Analyzer analyzer) {
        super(analyzer);

        token = analyzer.getToken();
        disabled = analyzer.isDisabled();
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public Analyzer getAnalyzer() {
        Analyzer analyzer = new Analyzer();

        analyzer.setId(getId());
        analyzer.setToken(getToken());
        analyzer.setName(getName());
        analyzer.setDisabled(isDisabled());

        return analyzer;
    }
}
