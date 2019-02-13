package de.fhbielefeld.swl.KINewsBoard.WebService.Backend;

import de.fhbielefeld.swl.KINewsBoard.BusinessLayer.AdminService;
import de.fhbielefeld.swl.KINewsBoard.DataLayer.DataModels.NewsEntry;
import de.fhbielefeld.swl.KINewsBoard.WebService.Backend.ViewModels.DashboardVM;
import de.fhbielefeld.swl.KINewsBoard.WebService.Shared.ViewModels.NewsEntryBaseModel;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Die Klasse <i>DashboardResource</i> stellt dem Backend den Zugriff auf das Dashboard bereit.
 */
@Path("/dashboard")
@Produces(MediaType.APPLICATION_JSON)
public class DashboardResource {

    @EJB
    private AdminService adminService;

    /**
     * Ruft maximal 15 Nachrichteneinträge und die Anzahl der Analyzer, Crawler und Analyseergebnisse im Dashboard ab.
     *
     * @return Dashboard mit Nachrichteneinträgen und Anzahl der Analyer, Crawler und Analyseergebnisse
     */
    @GET
    @PermitAll
    public DashboardVM get() {
        DashboardVM model = new DashboardVM();

        List<NewsEntry> entries = adminService.getAllNewsEntry();
        List<NewsEntryBaseModel> newsEntries = entries.stream().limit(15).map(NewsEntryBaseModel::new).collect(Collectors.toList());
        model.setNewsEntries(newsEntries);

        model.setCountNewsEntries(entries.size());
        model.setCountAnalyzerEntries(adminService.getAllAnalyzerResult().size());
        model.setCountActiveAnalyzer(adminService.getAllAnalyzer().size());
        model.setCountActiveCrawler(adminService.getAllCrawler().size());

        return model;
    }
}
