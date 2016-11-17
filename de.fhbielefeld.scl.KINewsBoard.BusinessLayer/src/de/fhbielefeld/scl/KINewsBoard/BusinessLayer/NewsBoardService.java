package de.fhbielefeld.scl.KINewsBoard.BusinessLayer;

import de.fhbielefeld.scl.KINewsBoard.BusinessLayer.Models.AnalyzerResultModel;
import de.fhbielefeld.scl.KINewsBoard.BusinessLayer.Models.NewsModel;
import de.fhbielefeld.scl.KINewsBoard.DataLayer.DataModels.Crawler;
import de.fhbielefeld.scl.KINewsBoard.DataLayer.DataModels.NewsEntry;
import de.fhbielefeld.scl.KINewsBoard.DataLayer.NewsBoardManager;

import javax.ejb.Stateless;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Klasse für den Zugriff auf die Daten in der News Board Datenbank.
 */
@Stateless
public class NewsBoardService {

    private NewsBoardManager mngr;

    public NewsBoardService() {
        this.mngr = new NewsBoardManager();
    }

    /**
     * Ermitellt <code>limit</code> definierten Anzahl von News Einträge die in der Web/Mobile Ansicht angezeit werden können, beginnend mit der <code>start</code> angegeben Seite.
     *
     * @param start   Die Seite ab der die nächsten <code>limit</code> News Einträge ermitellt werden sollen.
     * @param keyword Das Keyword nach dem die News Einträge gefilter werden sollen.
     * @return Liste der News Einträge die in der Web/Mobile Ansicht angezeigt werden.
     */
    public List<NewsModel> getPublicNewsEntries(int start, String keyword) {
        return getNewsModels(mngr.getNewsEntryDAO().getAll());
    }

    /**
     * Ermitellt ab Seite <code>start</code>, die in der mit <code>viewId</code> angegeben View definierte Anzahl von News Einträge.
     *
     * @param viewId die Id der View zu dem die News Einträge ermitellt werden sollen.
     * @param start  die Seite ab der Die News Einträge ermitellt werden sollen.
     * @return Liste der News Einträge die der View zugeordnet sind.
     * @throws IllegalArgumentException wenn View mit <code>viewId</code> nicht existiert.
     */
    public List<NewsModel> getViewNewsEntries(int viewId, int start) {
        return getNewsModels(mngr.getNewsEntryDAO().getAll());
    }

    /**
     * Lädt die Details zu der angebgen News Id.
     *
     * @param newsId Die Id der News dessen Details geladen werden sollen.
     * @return Die Detail Ansicht zu der News.
     */
    public NewsModel getNewsEntryDetails(String newsId) {
        return new NewsModel(mngr.getNewsEntryDAO().get(newsId), true);
    }

    /**
     * Veröffentlicht einen News Eintrag.
     *
     * @param token Der Authentifizierungs-Token für einen Crawler.
     * @param model Der News Eintrag der veröffentlicht werden soll.
     * @return Der veröffentlichte News Eintrag
     */
    public NewsModel publishNewsEntry(String token, NewsModel model) {

        Crawler crawler = mngr.getCrawlerDAO().getByToken(token);

        if (crawler == null)
            throw new IllegalArgumentException("Token nicht gültig");

        NewsEntry entry = new NewsEntry();
        entry.setId(model.getId());
        entry.setCrawler(crawler);
        entry.setTitle(model.getTitle());
        entry.setImage(model.getImage());
        entry.setContent(model.getContent());
        entry.setSource(model.getSource());
        entry.setUrl(model.getUrl());
        entry.setDate(model.getDate());

        mngr.getNewsEntryDAO().create(entry);

        return model;
    }

    /**
     * Liefert alle noch nicht analysierten News Einträge für einen Ananlyzer.
     *
     * @param token der Auth-Token für einen AnalyzerModel.
     * @return Liste der News Einträge.
     */
    public List<NewsModel> getAnalyzerNewsEntries(String token) {
        return getNewsModels(mngr.getNewsEntryDAO().getAll());
    }

    /**
     * Veröffentlicht ein Analyse Ergebnis zu einem News Eintrag.
     *
     * @param token der Auth-Token für einen AnalyzerModel.
     * @param model das Analyse Ergebnis.
     * @return Das veröffentlichte Analyse Ergebnis.
     */
    public AnalyzerResultModel publishAnalyzerResult(String token, AnalyzerResultModel model) {
        return model;
    }


    private List<NewsModel> getNewsModels(List<NewsEntry> entries) {
        return entries.stream().map(NewsModel::new).collect(Collectors.toList());
    }

    public void Close() {
        mngr.Close();
    }
}
