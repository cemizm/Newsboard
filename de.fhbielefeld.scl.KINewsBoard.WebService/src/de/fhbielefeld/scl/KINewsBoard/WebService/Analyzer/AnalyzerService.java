package de.fhbielefeld.scl.KINewsBoard.WebService.Analyzer;

import javax.ws.rs.*;

/**
 * Created by cem on 10.11.16.
 */


import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by cem on 10.11.16.
 */

@ApplicationPath("/analyzer")
public class AnalyzerService extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> s = new HashSet<>();

        s.add(NewsResource.class);

        return s;
    }
}