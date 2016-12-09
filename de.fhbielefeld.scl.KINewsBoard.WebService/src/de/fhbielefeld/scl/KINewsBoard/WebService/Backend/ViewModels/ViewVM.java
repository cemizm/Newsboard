package de.fhbielefeld.scl.KINewsBoard.WebService.Backend.ViewModels;

import de.fhbielefeld.scl.KINewsBoard.DataLayer.DataModels.Crawler;
import de.fhbielefeld.scl.KINewsBoard.DataLayer.DataModels.GroupSet;
import de.fhbielefeld.scl.KINewsBoard.DataLayer.DataModels.View;
import de.fhbielefeld.scl.KINewsBoard.WebService.Shared.ViewModels.ViewBaseModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by azad- on 17.11.2016.
 */
public class ViewVM extends ViewBaseModel {

    private List<Integer> groups;
    private List<Integer> crawlers;

    public ViewVM() {
        groups = new ArrayList<>();
        crawlers = new ArrayList<>();
    }

    public ViewVM(View view) {
        super(view);

        groups = view.getGroupSets().stream().map(GroupSet::getId).collect(Collectors.toList());
        crawlers = view.getCrawlers().stream().map(Crawler::getId).collect(Collectors.toList());
    }

    public List<Integer> getGroups() {
        return groups;
    }

    public void setGroups(List<Integer> groups) {
        this.groups = groups;
    }

    public List<Integer> getCrawlers() {
        return crawlers;
    }

    public void setCrawlers(List<Integer> crawlers) {
        this.crawlers = crawlers;
    }

    public View getView() {
        View view = new View();
        view.setId(getId());
        view.setName(getName());
        view.setDescription(getDescription());

        Set<GroupSet> groups = view.getGroupSets();
        for (Integer i : this.groups) {
            GroupSet gs = new GroupSet();
            gs.setId(i);
            groups.add(gs);
        }
        view.setGroupSets(groups);

        Set<Crawler> crawlers = view.getCrawlers();
        for (Integer i : this.crawlers) {
            Crawler c = new Crawler();
            c.setId(i);
            crawlers.add(c);
        }
        view.setCrawlers(crawlers);

        return view;
    }
}
