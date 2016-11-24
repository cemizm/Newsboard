package de.fhbielefeld.scl.KINewsBoard.WebService.Shared.ViewModels;


import de.fhbielefeld.scl.KINewsBoard.DataLayer.DataModels.Analyzer;

/**
 * Created by cem on 21.11.16.
 */
public class AnalyzerBaseModel {
    private int id;
    private String name;

    public AnalyzerBaseModel() {

    }

    public AnalyzerBaseModel(Analyzer analyzer) {
        id = analyzer.getId();
        name = analyzer.getName();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
