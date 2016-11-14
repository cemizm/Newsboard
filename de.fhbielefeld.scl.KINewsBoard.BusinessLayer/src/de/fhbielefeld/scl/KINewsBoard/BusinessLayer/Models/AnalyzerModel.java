package de.fhbielefeld.scl.KINewsBoard.BusinessLayer.Models;

import de.fhbielefeld.scl.KINewsBoard.DataLayer.DataModels.Analyzer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by cem on 14.11.16.
 */
public class AnalyzerModel {

    private int id;
    private String token;
    private String name;
    private boolean disabled;
    private List<GroupSetModel> groupSets;

    public AnalyzerModel() {
        groupSets = new ArrayList<>();
    }

    public AnalyzerModel(Analyzer analyzer) {
        this(analyzer, false);
    }

    public AnalyzerModel(Analyzer analyzer, boolean includeRelations) {
        this();

        id = analyzer.getId();
        token = analyzer.getToken();
        name = analyzer.getName();
        disabled = analyzer.isDisabled();
        if (includeRelations) {
            groupSets.addAll(analyzer.getGroupSets()
                    .stream()
                    .map(GroupSetModel::new)
                    .collect(Collectors.toList()));
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public List<GroupSetModel> getGroupSets() {
        return groupSets;
    }

    public void setGroupSets(List<GroupSetModel> groupSets) {
        this.groupSets = groupSets;
    }
}
