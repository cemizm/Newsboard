package de.fhbielefeld.swl.KINewsBoard.WebService.Backend.ViewModels;

import de.fhbielefeld.swl.KINewsBoard.DataLayer.DataModels.Crawler;
import de.fhbielefeld.swl.KINewsBoard.DataLayer.DataModels.GroupSet;
import de.fhbielefeld.swl.KINewsBoard.DataLayer.DataModels.View;
import de.fhbielefeld.swl.KINewsBoard.WebService.Shared.ViewModels.ViewBaseModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Die Klasse <i>ViewVM</i> stellt ein Datentransferobjekt dar und ruft die Daten einer Ansicht aus der Datenbank ab.
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

    /**
     * Ruft die der Ansicht zugeordneten Gruppen ab.
     *
     * @return Liste der Ids der zugeordneten Gruppen
     */
    public List<Integer> getGroups() {
        return groups;
    }

    /**
     * Legt die der Ansicht zugeordneten Gruppe. fest.
     *
     * @param groups Liste der Ids der zuzuordnenden Gruppen
     */
    public void setGroups(List<Integer> groups) {
        this.groups = groups;
    }

    /**
     * Ruft die der Ansicht zugeordneten Crawler ab.
     *
     * @return Liste der Ids der zugerdneten Crawler
     */
    public List<Integer> getCrawlers() {
        return crawlers;
    }

    /**
     * Legt die Crawler fest, die der Ansicht zugeordnet sind.
     *
     * @param crawlers Liste der Ids der zuzuordnenden Crawler
     */
    public void setCrawlers(List<Integer> crawlers) {
        this.crawlers = crawlers;
    }

    /**
     * Kopiert die Daten des Datentransferobjektes und erstellt anhand dessen eine neue Ansicht.
     *
     * @return Ansicht mit den kopierten Daten auf Basis des Datentransferobjektes
     */
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
