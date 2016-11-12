package de.fhbielefeld.scl.KINewsBoard.WebService.Frontend;


import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by cem on 10.11.16.
 */

@ApplicationPath("/frontend")
public class FrontendService extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> s = new HashSet<>();

        s.add(NewsResource.class);

        return s;
    }
}
