package de.fhbielefeld.scl.KINewsBoard.WebService.Backend.ViewModels;

import de.fhbielefeld.scl.KINewsBoard.DataLayer.DataModels.GroupSet;

/**
 * Created by cem on 14.11.16.
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

    public GroupSet getGroupSet() {
        GroupSet groupSet = new GroupSet();
        groupSet.setId(getId());
        groupSet.setName(getName());
        return groupSet;
    }
}
