package de.fhbielefeld.scl.KINewsBoard.WebService.Frontend.ViewModels;

import de.fhbielefeld.scl.KINewsBoard.DataLayer.DataModels.View;
import de.fhbielefeld.scl.KINewsBoard.WebService.Shared.ViewModels.ViewBaseModel;

import java.util.List;

/**
 * Created by cem on 21.11.16.
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
