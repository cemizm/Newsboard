package de.fhbielefeld.scl.KINewsBoard.WebService.Backend.ViewModels;

import de.fhbielefeld.scl.KINewsBoard.DataLayer.DataModels.GroupSet;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Die Klasse <i>GroupSetVM</i> stellt ein Datentransferobjekt dar und ruft die Daten einer Gruppe aus der Datenbank ab.
 */
public class GroupSetVM {
    private int id;
    private String name;

    public GroupSetVM() {

    }

    public GroupSetVM(GroupSet groupSet) {
        id = groupSet.getId();
        name = groupSet.getName();
    }

    /**
     * Rfut die Id der Gruppe ab.
     *
     * @return Die Id der Gruppe
     */
    public int getId() {
        return id;
    }

    /**
     * Legt die Id der Gruppe fest.
     *
     * @param id Die festzulegende Id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Ruft den Namen der Gruppe ab.
     *
     * @return Der Name der Gruppe
     */
    @Size(min = 3, max = 50)
    @NotNull
    public String getName() {
        return name;
    }

    /**
     * Legt den Namen der Gruppe fest.
     *
     * @param name Der festzulegende Name der Gruppe
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Kopiert die Daten des Datentransferobjektes und erstellt anhand dessen eine neue Gruppe.
     *
     * @return Gruppe mit den kopierten Daten auf Basis des Datentransferobjektes
     */
    public GroupSet getGroupSet() {
        GroupSet groupSet = new GroupSet();
        groupSet.setId(getId());
        groupSet.setName(getName());
        return groupSet;
    }
}
