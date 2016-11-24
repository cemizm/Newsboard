package de.fhbielefeld.scl.KINewsBoard.WebService.Shared.ViewModels;

import de.fhbielefeld.scl.KINewsBoard.DataLayer.DataModels.Crawler;

/**
 * Created by cem on 21.11.16.
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
