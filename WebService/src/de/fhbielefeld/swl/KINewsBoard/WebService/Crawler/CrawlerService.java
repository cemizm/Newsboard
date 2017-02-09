package de.fhbielefeld.swl.KINewsBoard.WebService.Crawler;

import de.fhbielefeld.swl.KINewsBoard.WebService.Shared.ExceptionMappers.AuthenticationExceptionMapper;
import de.fhbielefeld.swl.KINewsBoard.WebService.Shared.ExceptionMappers.DefaultExceptionMapper;
import de.fhbielefeld.swl.KINewsBoard.WebService.Shared.ExceptionMappers.IllegalArgumentExceptionMapper;
import de.fhbielefeld.swl.KINewsBoard.WebService.Shared.ExceptionMappers.ValidationExceptionMapper;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 * Die Klasse <i>CrawlerService</i> regelt den Zugriff des Crawlers auf die registrierten Ressourcen.
 */
@ApplicationPath("/crawler")
public class CrawlerService extends Application {

    /**
     * Ruft die für den CrawlerService registrierten Klassen ab.
     *
     * @return Liste der registrierten Klassen für den Service
     */
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> s = new HashSet<>();

        s.add(NewsResource.class);

        s.add(AuthenticationExceptionMapper.class);
        s.add(DefaultExceptionMapper.class);
        s.add(IllegalArgumentExceptionMapper.class);
        s.add(ValidationExceptionMapper.class);

        return s;
    }
}