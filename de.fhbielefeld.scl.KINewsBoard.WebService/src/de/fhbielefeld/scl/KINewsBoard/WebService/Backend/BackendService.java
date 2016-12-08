package de.fhbielefeld.scl.KINewsBoard.WebService.Backend;

import de.fhbielefeld.scl.KINewsBoard.WebService.Backend.Filters.AuthenticationFilter;
import de.fhbielefeld.scl.KINewsBoard.WebService.Shared.ExceptionMappers.AuthenticationExceptionMapper;
import de.fhbielefeld.scl.KINewsBoard.WebService.Shared.ExceptionMappers.DefaultExceptionMapper;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import javax.xml.bind.ValidationException;
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

        s.add(AuthenticationFilter.class);
        s.add(CrawlerResource.class);
        s.add(AnalyzerResource.class);
        s.add(GroupResource.class);
        s.add(ViewResource.class);
        s.add(DashboardResource.class);
        s.add(AuthResource.class);

        s.add(AuthenticationExceptionMapper.class);
        s.add(DefaultExceptionMapper.class);
        s.add(IllegalArgumentException.class);
        s.add(ValidationException.class);

        return s;
    }
}