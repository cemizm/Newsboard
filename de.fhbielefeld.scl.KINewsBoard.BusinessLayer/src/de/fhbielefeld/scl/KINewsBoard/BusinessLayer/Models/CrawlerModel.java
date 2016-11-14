package de.fhbielefeld.scl.KINewsBoard.BusinessLayer.Models;

import de.fhbielefeld.scl.KINewsBoard.DataLayer.DataModels.Crawler;

/**
 * Created by cem on 14.11.16.
 */
public class CrawlerModel {
    private int id;
    private String name;
    private boolean disabled;
    private boolean ignoreAnalyzer;

    public CrawlerModel() {

    }

    public CrawlerModel(Crawler crawler) {
        id = crawler.getId();
        name = crawler.getName();
        disabled = crawler.isDisabled();
        ignoreAnalyzer = crawler.isIgnoreAnalyzer();
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

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public boolean isIgnoreAnalyzer() {
        return ignoreAnalyzer;
    }

    public void setIgnoreAnalyzer(boolean ignoreAnalyzer) {
        this.ignoreAnalyzer = ignoreAnalyzer;
    }
}
