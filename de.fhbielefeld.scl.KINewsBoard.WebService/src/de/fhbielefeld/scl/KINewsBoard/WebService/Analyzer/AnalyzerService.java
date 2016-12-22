package de.fhbielefeld.scl.KINewsBoard.WebService.Analyzer;

import de.fhbielefeld.scl.KINewsBoard.WebService.Shared.ExceptionMappers.AuthenticationExceptionMapper;
import de.fhbielefeld.scl.KINewsBoard.WebService.Shared.ExceptionMappers.DefaultExceptionMapper;
import de.fhbielefeld.scl.KINewsBoard.WebService.Shared.ExceptionMappers.IllegalArgumentExceptionMapper;
import de.fhbielefeld.scl.KINewsBoard.WebService.Shared.ExceptionMappers.ValidationExceptionMapper;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by cem on 10.11.16.
 */

@ApplicationPath("/analyzer")
public class AnalyzerService extends Application {

    /**
     * Ruft die für die AnalyzerService registrierten Klassen ab.
     *
     * @return Liste der registrierten Klassen für den Service
     */
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> s = new HashSet<>();

        s.add(NewsResource.class);

        s.add(AuthenticationExceptionMapper.class);
        s.add(ValidationExceptionMapper.class);
        s.add(IllegalArgumentExceptionMapper.class);
        s.add(DefaultExceptionMapper.class);

        return s;
    }
}