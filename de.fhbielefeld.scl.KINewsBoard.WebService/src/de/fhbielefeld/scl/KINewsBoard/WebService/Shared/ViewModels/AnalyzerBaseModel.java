package de.fhbielefeld.scl.KINewsBoard.WebService.Shared.ViewModels;


import de.fhbielefeld.scl.KINewsBoard.DataLayer.DataModels.Analyzer;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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

    @Size(min = 3, max = 50)
    @NotNull
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
