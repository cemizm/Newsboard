package de.fhbielefeld.scl.KINewsBoard.WebService.Shared.ViewModels;

import de.fhbielefeld.scl.KINewsBoard.DataLayer.DataModels.View;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Die Klasse <i>ViewBaseModel</i> stellt ein Datentransferobjekt dar und ruft die Basisinformationen einer Ansicht aus der Datenbank ab.
 */
public class ViewBaseModel {

    private int id;
    private String name;
    private String description;

    public ViewBaseModel() {

    }

    public ViewBaseModel(View view) {
        this.id = view.getId();
        this.name = view.getName();
        this.description = view.getDescription();
    }

    /**
     * Ruft die Id der Ansicht ab.
     *
     * @return Die Id der Ansicht
     */
    public int getId() {
        return id;
    }

    /**
     * Legt die Id der Ansicht fest.
     *
     * @param id Die festzulegende Id der Ansicht
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Ruft den Namen der Ansicht ab.
     *
     * @return Der Name der Ansicht
     */
    @Size(min = 3, max = 50)
    @NotNull
    public String getName() {
        return name;
    }

    /**
     * Legt den Namen der Ansicht fest.
     *
     * @param name Der festzulegende Name der Ansicht
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Ruft die Beschreibung der Ansicht ab.
     *
     * @return Die Beschreibung der Ansicht
     */
    @Size(max = 1024)
    public String getDescription() {
        return description;
    }

    /**
     * Legt die Beschreibung der Ansicht fest.
     *
     * @param description Die festzulegende Beschreibung der Ansicht
     */
    public void setDescription(String description) {
        this.description = description;
    }

}
