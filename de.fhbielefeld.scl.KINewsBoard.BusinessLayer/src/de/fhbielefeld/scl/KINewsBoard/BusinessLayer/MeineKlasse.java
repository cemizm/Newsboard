package de.fhbielefeld.scl.KINewsBoard.BusinessLayer;

import de.fhbielefeld.scl.KINewsBoard.DataLayer.NewsBoardDAO;
import de.fhbielefeld.scl.KINewsBoard.DataLayer.DataModels.NewsEntry;

import java.util.List;

/**
 * Created by cem on 28.10.16.
 */
public class MeineKlasse {
    private NewsBoardDAO mngr;

    public MeineKlasse() {
        mngr = new NewsBoardDAO();
    }

    public List<NewsEntry> Machwas() {
        return mngr.getNewsEntries();
    }
}
