package de.fhbielefeld.scl.KINewsBoard.WebService.Frontend.ViewModels;

import de.fhbielefeld.scl.KINewsBoard.BusinessLayer.Models.AnalyzerModel;

/**
 * Created by cem on 21.11.16.
 */
public class AnalyzerVM {
    private int id;
    private String name;

    public AnalyzerVM() {

    }

    public AnalyzerVM(AnalyzerModel analyzer) {
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
