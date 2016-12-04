package de.fhbielefeld.scl.KINewsBoard.WebService.Backend.ViewModels;

import de.fhbielefeld.scl.KINewsBoard.DataLayer.DataModels.View;
import de.fhbielefeld.scl.KINewsBoard.WebService.Shared.ViewModels.ViewBaseModel;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by azad- on 17.11.2016.
 */
public class ViewVM extends ViewBaseModel {

    private List<GroupSetVM> groupSetModels;
    private List<CrawlerVM> crawlerModels;

    public ViewVM() {
        groupSetModels = new ArrayList<>();
        crawlerModels = new ArrayList<>();
    }

    public ViewVM(View view) {
        super(view);

        groupSetModels.addAll(view.getGroupSets()
                .stream()
                .map(GroupSetVM::new)
                .collect(Collectors.toList()));

        crawlerModels.addAll(view.getCrawlers()
                .stream()
                .map(CrawlerVM::new)
                .collect(Collectors.toList()));
    }

    public List<GroupSetVM> getGroupSetModels() {
        return groupSetModels;
    }

    public void setGroupSetModels(List<GroupSetVM> groupSetModels) {
        this.groupSetModels = groupSetModels;
    }

    public List<CrawlerVM> getCrawlerModels() {
        return crawlerModels;
    }

    public void setCrawlerModels(List<CrawlerVM> crawlerModels) {
        this.crawlerModels = crawlerModels;
    }

    public View getView() {
        View view = new View();
        view.setId(getId());
        view.setName(getName());
        view.setDescription(getDescription());

        return view;
    }
}
