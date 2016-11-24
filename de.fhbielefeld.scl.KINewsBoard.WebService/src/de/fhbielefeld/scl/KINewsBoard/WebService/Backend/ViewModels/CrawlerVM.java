package de.fhbielefeld.scl.KINewsBoard.WebService.Backend.ViewModels;

import de.fhbielefeld.scl.KINewsBoard.DataLayer.DataModels.Crawler;
import de.fhbielefeld.scl.KINewsBoard.WebService.Shared.ViewModels.CrawlerBaseModel;

/**
 * Created by cem on 14.11.16.
 */
public class CrawlerVM extends CrawlerBaseModel {
    private String token;
    private boolean disabled;
    private boolean ignoreAnalyzer;

    public CrawlerVM() {

    }

    public CrawlerVM(Crawler crawler) {
        super(crawler);
        token = crawler.getToken();
        disabled = crawler.isDisabled();
        ignoreAnalyzer = crawler.isIgnoreAnalyzer();
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
