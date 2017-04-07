package de.fhbielefeld.swl.KINewsBoard.WebService.Shared.ViewModels;

import de.fhbielefeld.swl.KINewsBoard.DataLayer.DataModels.Analyzer;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Die Klasse <i>AnalyzerBaseModel</i> stellt ein Datentransferobjekt dar und ruft die Basisinformationen eines Analyzers aus der Datenbank ab.
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

    /**
     * Ruft die Id des Analyzers ab.
     *
     * @return Die Id des Analyzers
     */
    public int getId() {
        return id;
    }

    /**
     * Legt die Id des Analyzers fest.
     *
     * @param id Die festzulegende Id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Ruft den Namen des Analyzers ab.
     *
     * @return Der Name des Analyzers
     */
    @Size(min = 3, max = 50)
    @NotNull
    public String getName() {
        return name;
    }

    /**
     * Legtn den Namen des Analyzers fest.
     *
     * @param name Der festzulegende Name
     */
    public void setName(String name) {
        this.name = name;
    }
}
