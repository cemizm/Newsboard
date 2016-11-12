package de.fhbielefeld.scl.KINewsBoard.WebService.Backend;

import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by cem on 10.11.16.
 */

@ApplicationPath("/crawler")
public class BackendService extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> s = new HashSet<>();


        return s;
    }
}