package de.fhbielefeld.scl.KINewsBoard.BusinessLayer.Models;

import de.fhbielefeld.scl.KINewsBoard.DataLayer.DataModels.Crawler;

/**
 * Created by cem on 14.11.16.
 */
public class CrawlerModel {
    private int id;
    private String name;
    private String token;
    private boolean disabled;
    private boolean ignoreAnalyzer;

    public CrawlerModel() {

    }

    public CrawlerModel(Crawler crawler) {
        id = crawler.getId();
        name = crawler.getName();
        token = crawler.getToken();
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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

    public Crawler getCrawler(){
        Crawler crawler = new Crawler();
        crawler.setId(getId());
        crawler.setToken(getToken());
        crawler.setName(getName());
        crawler.setDisabled(isDisabled());
        crawler.setIgnoreAnalyzer(isIgnoreAnalyzer());
        return crawler;
    }
}
