package de.fhbielefeld.swl.KINewsBoard.WebService.Shared.ViewModels;

import de.fhbielefeld.swl.KINewsBoard.DataLayer.DataModels.NewsEntry;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.json.bind.annotation.JsonbTransient;
import javax.json.bind.annotation.JsonbDateFormat;
import java.util.Date;

/**
 * Die Klasse <i>NewsEntryBaseModel</i> stellt ein Datentransferobjekt dar und ruft die Basisinformationen eines Nachrichteneintrages aus der Datenbank ab.
 */
public class NewsEntryBaseModel {
    private String id;
    private String title;
    private String image;
    private String excerpt;
    private String content;
    private String source;
    private String url;
    private Date date;

    public NewsEntryBaseModel() {

    }

    public NewsEntryBaseModel(NewsEntry entry) {
        id = entry.getId();
        title = entry.getTitle();
        image = entry.getImage();
        excerpt = entry.getExcerpt();
        content = entry.getContent();
        source = entry.getSource();
        url = entry.getUrl();
        date = entry.getDate();
    }

    /**
     * Ruft die Id des Nachrichteneintrages ab.
     *
     * @return Die Id des Nachrichteneintrages
     */
    @NotNull
    @Size(min = 3, max = 255)
    public String getId() {
        return id;
    }

    /**
     * Legt die Id des Nachrichteneintrages fest.
     *
     * @param id Die festzulegende Id des Nachrichteneintrages
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Ruft den Titel des Nachrichteneintrages ab.
     *
     * @return Der Titel des Nachrichteneintrages
     */
    @Size(max = 1024)
    public String getTitle() {
        return title;
    }

    /**
     * Legt den Titel des Nachrichteneintrages fest.
     *
     * @param title Der festzulegende Titel des Nachrichteneintrages
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Ruft das Bild des Nachrichteneintrages ab.
     *
     * @return Das Bild des Nachrichteneintrages
     */
    public String getImage() {
        return image;
    }

    /**
     * Legt das Bild des Nachrichteneintrages fest.
     *
     * @param image Das festzulegende Bild des Nachrichteneintrages
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * Ruft die Zusammenfassung des Nachrichteneintrages ab.
     *
     * @return Die Zusammenfassung des Nachrichteneintrages.
     */
    @Size(max = 1024)
    public String getExcerpt() {
        return excerpt;
    }

    /**
     * Legt die Zusammenfassung des Nachrichteneintrages fest.
     *
     * @param excerpt Die festzulegende Zusammenfassung
     */
    public void setExcerpt(String excerpt) {
        this.excerpt = excerpt;
    }
    /**
     * Ruft den Inhalt des Nachrichteneintrages ab.
     *
     * @return Der Inhalt des Nachrichteneintrages
     */
    @NotNull
    @Size(min = 10, max = 10485760)
    public String getContent() {
        return content;
    }

    /**
     * Legt den Inhalt des Nachrichteneintrages fest.
     *
     * @param content Der festzulegende Inhalt des Nachrichteneintrages
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Ruft die Quelle des Nachrichteneintrages ab.
     *
     * @return Die Quelle des Nachrichteneintrages
     */
    @NotNull
    @Size(min = 3, max = 255)
    public String getSource() {
        return source;
    }

    /**
     * Legt die Quelle des Nachrichteneintrages fest.
     *
     * @param source Die festzulegende Quelle des Nachrichteneintrages
     */
    public void setSource(String source) {
        this.source = source;
    }

    /**
     * Ruft die URL des Nachrichteneintrages ab.
     *
     * @return Die URL des Nachrichteneintrages
     */
    @NotNull
    @Size(min = 10, max = 512)
    public String getUrl() {
        return url;
    }

    /**
     * Legt die URL des Nachrichteneintrages fest.
     *
     * @param url Die festzulegende URL des Nachrichteneintrages
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Ruft das Datum des Nachrichteneintrages ab.
     *
     * @return Das Datum des Nachrichteneintrages
     */
    @JsonbDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
    public Date getDate() {
        return date;
    }

    /**
     * Legt das Datum des Nachrichteneintrages fest.
     *
     * @param date Das festzulegende Datum des Nachrichteneintrages
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Kopiert die Daten des Datentransferobjektes und erstellt anhand dessen einen neuen Nachrichteneintrag.
     *
     * @return Nachrichteneintrag mit den kopierten Daten auf Basis des Datentransferobjektes
     */
    @JsonbTransient
    public NewsEntry getNewsEntryModel() {
        NewsEntry entry = new NewsEntry();
        entry.setId(getId());
        entry.setTitle(getTitle());
        entry.setImage(getImage());
        entry.setExcerpt(getExcerpt());
        entry.setContent(getContent());
        entry.setSource(getSource());
        entry.setUrl(getUrl());
        entry.setDate(getDate());
        return entry;
    }
}
