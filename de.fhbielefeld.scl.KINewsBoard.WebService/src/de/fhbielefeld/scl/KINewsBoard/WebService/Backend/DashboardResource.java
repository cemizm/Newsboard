package de.fhbielefeld.scl.KINewsBoard.WebService.Backend;

import de.fhbielefeld.scl.KINewsBoard.BusinessLayer.AdminService;
import de.fhbielefeld.scl.KINewsBoard.DataLayer.DataModels.NewsEntry;
import de.fhbielefeld.scl.KINewsBoard.WebService.Backend.ViewModels.DashboardVM;
import de.fhbielefeld.scl.KINewsBoard.WebService.Shared.ViewModels.NewsEntryBaseModel;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by cem on 27.11.16.
 */
@Path("/dashboard")
@Produces(MediaType.APPLICATION_JSON)
public class DashboardResource {

    @EJB
    private AdminService adminService;

    @GET
    @Path("/")
    @RolesAllowed({})
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
