package de.fhbielefeld.scl.KINewsBoard.WebService.Shared.ViewModels;

import de.fhbielefeld.scl.KINewsBoard.DataLayer.DataModels.View;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by cem on 21.11.16.
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

    @Size(max = 1024)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
