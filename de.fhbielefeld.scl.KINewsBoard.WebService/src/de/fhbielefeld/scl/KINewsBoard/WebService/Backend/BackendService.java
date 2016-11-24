package de.fhbielefeld.scl.KINewsBoard.WebService.Backend;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by cem on 10.11.16.
 */

@ApplicationPath("/backend")
public class BackendService extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> s = new HashSet<>();

        s.add(CrawlerResource.class);
        s.add(AnalyzerResource.class);
        s.add(GroupResource.class);
        s.add(ViewResource.class);

        return s;
    }
}