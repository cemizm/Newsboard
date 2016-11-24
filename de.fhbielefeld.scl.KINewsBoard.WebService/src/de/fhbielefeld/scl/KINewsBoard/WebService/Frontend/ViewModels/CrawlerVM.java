package de.fhbielefeld.scl.KINewsBoard.WebService.Frontend.ViewModels;

import de.fhbielefeld.scl.KINewsBoard.BusinessLayer.Models.CrawlerModel;

/**
 * Created by cem on 21.11.16.
 */
public class CrawlerVM {
    private int id;
    private String name;

    public CrawlerVM() {

    }

    public CrawlerVM(CrawlerModel crawler) {
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
