package de.fhbielefeld.scl.KINewsBoard.WebService.Backend.ViewModels;

import de.fhbielefeld.scl.KINewsBoard.DataLayer.DataModels.Analyzer;
import de.fhbielefeld.scl.KINewsBoard.DataLayer.DataModels.GroupSet;
import de.fhbielefeld.scl.KINewsBoard.WebService.Shared.ViewModels.AnalyzerBaseModel;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by cem on 14.11.16.
 */
public class AnalyzerVM extends AnalyzerBaseModel {

    private String token;
    private boolean disabled;
    private List<Integer> groups;

    public AnalyzerVM() {
        groups = new ArrayList<>();
    }

    public AnalyzerVM(Analyzer analyzer) {
        super(analyzer);

        token = analyzer.getToken();
        disabled = analyzer.isDisabled();
        groups = analyzer.getGroupSets().stream().map(GroupSet::getId).collect(Collectors.toList());
    }

    @Size(min = 10, max = 256)
    @NotNull
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public List<Integer> getGroups() {
        return groups;
    }

    public void setGroups(List<Integer> groups) {
        this.groups = groups;
    }

    public Analyzer getAnalyzer() {
        Analyzer analyzer = new Analyzer();

        analyzer.setId(getId());
        analyzer.setToken(getToken());
        analyzer.setName(getName());
        analyzer.setDisabled(isDisabled());

        Set<GroupSet> groups = analyzer.getGroupSets();
        for (Integer i : this.groups) {
            GroupSet gs = new GroupSet();
            gs.setId(i);
            groups.add(gs);
        }
        analyzer.setGroupSets(groups);

        return analyzer;
    }
}
