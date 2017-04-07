package de.fhbielefeld.swl.KINewsBoard.WebService.Shared.ViewModels;

import de.fhbielefeld.swl.KINewsBoard.DataLayer.DataModels.Crawler;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Die Klasse <i>CrawlerBaseModel</i> stellt ein Datentransferobjekt dar und ruft die Basisinformationen eines Crawlers aus der Datenbank ab.
 */
public class CrawlerBaseModel {
    private int id;
    private String name;

    public CrawlerBaseModel() {

    }

    public CrawlerBaseModel(Crawler crawler) {
        id = crawler.getId();
        name = crawler.getName();
    }

    /**
     * Ruft die Id des Crawlers ab.
     *
     * @return Die Id des Crawlers
     */
    public int getId() {
        return id;
    }

    /**
     * Legt die Id des Crawlers fest.
     *
     * @param id Die festzulegnde Id des Crawlers
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Ruft den Namen des Crawlers ab.
     *
     * @return Der Name des Crawlers
     */
    @Size(min = 3, max = 50)
    @NotNull
    public String getName() {
        return name;
    }

    /**
     * Legt den Namen des Crawlers fest.
     *
     * @param name Der festzulegende Name
     */
    public void setName(String name) {
        this.name = name;
    }
}
