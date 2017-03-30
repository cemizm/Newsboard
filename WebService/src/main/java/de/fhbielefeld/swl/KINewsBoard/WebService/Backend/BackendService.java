package de.fhbielefeld.swl.KINewsBoard.WebService.Backend;

import de.fhbielefeld.swl.KINewsBoard.WebService.Backend.Filters.AuthenticationFilter;
import de.fhbielefeld.swl.KINewsBoard.WebService.Shared.ExceptionMappers.AuthenticationExceptionMapper;
import de.fhbielefeld.swl.KINewsBoard.WebService.Shared.ExceptionMappers.DefaultExceptionMapper;
import de.fhbielefeld.swl.KINewsBoard.WebService.Shared.ExceptionMappers.IllegalArgumentExceptionMapper;
import de.fhbielefeld.swl.KINewsBoard.WebService.Shared.ExceptionMappers.ValidationExceptionMapper;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 * Die Klasse <i>BackendService</i> regelt den Zugriff des Backends auf die registrierten Ressourcen.
 */
@ApplicationPath("/backend")
public class BackendService extends Application {

    /**
     * Ruft die für den BackendService registrierten Klassen ab.
     *
     * @return Liste der registrierten Klassen für den Service
     */
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
        s.add(ValidationExceptionMapper.class);
        s.add(IllegalArgumentExceptionMapper.class);
        s.add(DefaultExceptionMapper.class);

        return s;
    }
}