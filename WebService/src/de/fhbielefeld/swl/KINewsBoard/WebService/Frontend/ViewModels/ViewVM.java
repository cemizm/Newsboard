package de.fhbielefeld.swl.KINewsBoard.WebService.Frontend.ViewModels;

import de.fhbielefeld.swl.KINewsBoard.DataLayer.DataModels.View;
import de.fhbielefeld.swl.KINewsBoard.WebService.Shared.ViewModels.ViewBaseModel;

import java.util.List;

/**
 * Die Klasse <i>ViewVM</i> stellt ein Datentransferobjekt dar und ruft die Daten ein Ansicht aus der Datenbank ab.
 */
public class ViewVM extends ViewBaseModel {

    private List<NewsEntryVM> newsEntries;

    public ViewVM() {

    }

    public ViewVM(View view, List<NewsEntryVM> newsEntries) {
        super(view);
        this.newsEntries = newsEntries;
    }

    /**
     * Ruft die Nachrichteneinträge der Ansicht ab.
     *
     * @return Liste der Nachrichteneinträge
     */
    public List<NewsEntryVM> getNewsEntries() {
        return newsEntries;
    }

    /**
     * Legt die Nachrichteneinträge der Ansicht fest.
     *
     * @param newsEntries Liste der Nachrichteneinträge
     */
    public void setNewsEntries(List<NewsEntryVM> newsEntries) {
        this.newsEntries = newsEntries;
    }
}
