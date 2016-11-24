package de.fhbielefeld.scl.KINewsBoard.BusinessLayer.Models;

import de.fhbielefeld.scl.KINewsBoard.DataLayer.DataModels.Analyzer;

/**
 * Created by cem on 14.11.16.
 */
public class AnalyzerModel {

    private int id;
    private String token;
    private String name;
    private boolean disabled;

    public AnalyzerModel(Analyzer analyzer) {
        id = analyzer.getId();
        token = analyzer.getToken();
        name = analyzer.getName();
        disabled = analyzer.isDisabled();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
