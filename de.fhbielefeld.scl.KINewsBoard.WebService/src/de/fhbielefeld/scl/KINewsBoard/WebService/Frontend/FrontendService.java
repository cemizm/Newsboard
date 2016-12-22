package de.fhbielefeld.scl.KINewsBoard.WebService.Frontend;

import de.fhbielefeld.scl.KINewsBoard.WebService.Shared.ExceptionMappers.AuthenticationExceptionMapper;
import de.fhbielefeld.scl.KINewsBoard.WebService.Shared.ExceptionMappers.DefaultExceptionMapper;
import de.fhbielefeld.scl.KINewsBoard.WebService.Shared.ExceptionMappers.IllegalArgumentExceptionMapper;
import de.fhbielefeld.scl.KINewsBoard.WebService.Shared.ExceptionMappers.ValidationExceptionMapper;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 * Die Klasse <i>FrontendService</i> regelt den Zugriff des Frontends auf die registrierten Ressourcen.
 */
@ApplicationPath("/frontend")
public class FrontendService extends Application {

    /**
     * Ruft die für den FrontendService registrierten Klassen ab.
     *
     * @return Liste der registrierten Klassen für den Sevice
     */
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> s = new HashSet<>();

        s.add(NewsResource.class);

        s.add(AuthenticationExceptionMapper.class);
        s.add(IllegalArgumentExceptionMapper.class);
        s.add(ValidationExceptionMapper.class);
        s.add(DefaultExceptionMapper.class);

        return s;
    }
}
