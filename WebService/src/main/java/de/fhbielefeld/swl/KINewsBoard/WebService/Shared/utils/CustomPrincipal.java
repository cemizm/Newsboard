package de.fhbielefeld.swl.KINewsBoard.WebService.Shared.utils;

import java.security.Principal;

public class CustomPrincipal<T> implements Principal {
    private T value;

    public CustomPrincipal(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    @Override
    public String getName() {
        return null;
    }
}
