package de.fhbielefeld.scl.KINewsBoard.BusinessLayer;

import de.fhbielefeld.scl.KINewsBoard.DataLayer.NewsBoardManager;
import de.fhbielefeld.scl.KINewsBoard.DataLayer.NewsEntry;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cem on 28.10.16.
 */
public class MeineKlasse {
    private NewsBoardManager mngr;

    public MeineKlasse() {
        mngr = new NewsBoardManager();
    }

    public List<NewsEntry> Machwas() {
        return mngr.getNewsEntries();
    }
}
