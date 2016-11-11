import de.fhbielefeld.scl.KINewsBoard.WebService.AnalyzerService;
import de.fhbielefeld.scl.KINewsBoard.WebService.BackendService;
import de.fhbielefeld.scl.KINewsBoard.WebService.CrawlerService;
import de.fhbielefeld.scl.KINewsBoard.WebService.FrontendService;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by cem on 29.10.16.
 */
@ApplicationPath("/")
public class App extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> s = new HashSet<>();

        s.add(AnalyzerService.class);
        s.add(CrawlerService.class);
        s.add(BackendService.class);
        s.add(FrontendService.class);

        return s;
    }
}