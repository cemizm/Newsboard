package de.fhbielefeld.scl.KINewsBoard.BusinessLayer;

/**
 * Created by cem on 28.10.16.
 */
public class Customer {

    private String name;
    private int alter;

    public Customer() {
    }

    public Customer(String name, int alter) {
        this.name = name;
        this.alter = alter;
    }

    public int getAlter() {
        return alter;
    }

    public void setAlter(int alter) {
        this.alter = alter;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
