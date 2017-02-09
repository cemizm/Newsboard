package de.fhbielefeld.swl.KINewsBoard.WebService.Analyzer;

import de.fhbielefeld.swl.KINewsBoard.WebService.Shared.ExceptionMappers.AuthenticationExceptionMapper;
import de.fhbielefeld.swl.KINewsBoard.WebService.Shared.ExceptionMappers.DefaultExceptionMapper;
import de.fhbielefeld.swl.KINewsBoard.WebService.Shared.ExceptionMappers.IllegalArgumentExceptionMapper;
import de.fhbielefeld.swl.KINewsBoard.WebService.Shared.ExceptionMappers.ValidationExceptionMapper;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 * Die Klasse <i>AnalyzerService</i> regelt den Zugriff des Analyzer auf die registrierten Ressourcen.
 */
@ApplicationPath("/analyzer")
public class AnalyzerService extends Application {

    /**
     * Ruft die für den AnalyzerService registrierten Klassen ab.
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