package de.fhbielefeld.swl.KINewsBoard.WebService.Backend.ViewModels;

import de.fhbielefeld.swl.KINewsBoard.DataLayer.DataModels.Analyzer;
import de.fhbielefeld.swl.KINewsBoard.DataLayer.DataModels.Crawler;
import de.fhbielefeld.swl.KINewsBoard.DataLayer.DataModels.GroupSet;
import de.fhbielefeld.swl.KINewsBoard.WebService.Shared.ViewModels.AnalyzerBaseModel;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Die Klasse <i>AnalyzerVM</i> stellt ein Datentransferobjekt dar und ruft die Daten eines Analyzers aus der Datenbank ab.
 */
public class AnalyzerVM extends AnalyzerBaseModel {

    private String token;
    private boolean disabled;
    private List<Integer> groups;
    private List<Integer> crawler;

    public AnalyzerVM() {
        groups = new ArrayList<>();
    }

    public AnalyzerVM(Analyzer analyzer) {
        super(analyzer);

        token = analyzer.getToken();
        disabled = analyzer.isDisabled();
        groups = analyzer.getGroupSets().stream().map(GroupSet::getId).collect(Collectors.toList());
        crawler = analyzer.getCrawlers().stream().map(Crawler::getId).collect(Collectors.toList());
    }

    /**
     * Ruft den Authentifizierungstoken des Analyzers ab.
     *
     * @return Der Authentifizierungstoken des Analyzers
     */
    @Size(min = 10, max = 256)
    @NotNull
    public String getToken() {
        return token;
    }

    /**
     * Legt den Authentifizierungstoken des Analyzers fest.
     *
     * @param token Der festzulegende Authentifizierungstoken des Analyzers
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * Ruft den Aktivitätsstatus des Analyzers ab.
     *
     * @return <i>true</i>, wenn der Analyzer deaktiviert ist
     */
    public boolean isDisabled() {
        return disabled;
    }

    /**
     * Legt den Aktivitätsstatus des Analyzers fest.
     *
     * @param disabled Der festzulegende Aktivitätszustand des Analyzers
     */
    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    /**
     * Ruft die Ids der Gruppen ab, denen der Analyzer zugeordnet ist.
     *
     * @return Liste der Gruppenids, denen der Analyzer zugeordnet ist
     */
    public List<Integer> getGroups() {
        return groups;
    }

    /**
     * Legt die Gruppen fest, denen der Analyzer zugeordnet ist.
     *
     * @param groups Liste der Gruppenids, denen der Analyzer zugeordnet werden soll
     */
    public void setGroups(List<Integer> groups) {
        this.groups = groups;
    }

    /**
     * Ruft die Crawler ab, denen der Analyzer zugeordnet ist.
     *
     * @return Liste der Crawler, denen der Analyzer zugeordnet ist
     */
    public List<Integer> getCrawler() {
        return crawler;
    }

    /**
     * Legt die Crawler fest, denen der Analyzer zugeordnet ist.
     *
     * @param crawler Liste der Crawler, denen der Analyzer zugeordnet werden soll
     */
    public void setCrawler(List<Integer> crawler) {
        this.crawler = crawler;
    }

    /**
     * Kopiert die Daten des Datentransferobjektes und erstellt anhand dessen einen neuen Analyzer.
     *
     * @return Analyzer mit den kopierten Daten auf Basis des Datentransferobjektes
     */
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

        Set<Crawler> crawlers = analyzer.getCrawlers();
        for (Integer i: this.crawler) {
            Crawler c = new Crawler();
            c.setId(i);
            crawlers.add(c);
        }
        analyzer.setCrawlers(crawlers);

        return analyzer;
    }
}
