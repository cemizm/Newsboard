package de.fhbielefeld.scl.KINewsBoard.BusinessLayer.Models;

import de.fhbielefeld.scl.KINewsBoard.DataLayer.DataModels.View;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by azad- on 17.11.2016.
 */
public class ViewModel {

    private int id;
    private String name;
    private String description;
    private int type;
    private int maxResults;
    private int lastDays;
    private boolean publicView;

    private List<GroupSetModel> groupSetModels;
    private List<CrawlerModel> crawlerModels;

    public ViewModel() {
        groupSetModels = new ArrayList<>();
        crawlerModels = new ArrayList<>();
    }

    public ViewModel(View view) {
        this(view, false);
    }

    public ViewModel(View view, boolean includeRelations) {
        this();

        id = view.getId();
        name = view.getName();
        description = view.getDescription();
        type = view.getType();
        maxResults = view.getMaxResults();
        lastDays = view.getLastDays();
        publicView = view.isPublicView();

        if (includeRelations) {
            groupSetModels.addAll(view.getGroupSets()
                    .stream()
                    .map(GroupSetModel::new)
                    .collect(Collectors.toList()));

            crawlerModels.addAll(view.getCrawlers()
                    .stream()
                    .map(CrawlerModel::new)
                    .collect(Collectors.toList()));
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getMaxResults() {
        return maxResults;
    }

    public void setMaxResults(int maxResults) {
        this.maxResults = maxResults;
    }

    public int getLastDays() {
        return lastDays;
    }

    public void setLastDays(int lastDays) {
        this.lastDays = lastDays;
    }

    public boolean isPublicView() {
        return publicView;
    }

    public void setPublicView(boolean publicView) {
        this.publicView = publicView;
    }

    public List<GroupSetModel> getGroupSetModels() {
        return groupSetModels;
    }

    public void setGroupSetModels(List<GroupSetModel> groupSetModels) {
        this.groupSetModels = groupSetModels;
    }

    public List<CrawlerModel> getCrawlerModels() {
        return crawlerModels;
    }

    public void setCrawlerModels(List<CrawlerModel> crawlerModels) {
        this.crawlerModels = crawlerModels;
    }

    public View getView() {
        View view = new View();
        view.setId(getId());
        view.setName(getName());
        view.setDescription(getDescription());
        view.setType(getType());
        view.setMaxResults(getMaxResults());
        view.setLastDays(getLastDays());
        view.setPublicView(isPublicView());

        return view;
    }
}
