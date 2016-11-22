package de.fhbielefeld.scl.KINewsBoard.WebService.Crawler.ViewModels;

import de.fhbielefeld.scl.KINewsBoard.BusinessLayer.Models.AnalyzerResultModel;
import de.fhbielefeld.scl.KINewsBoard.BusinessLayer.Models.NewsEntryModel;
import de.fhbielefeld.scl.KINewsBoard.WebService.Frontend.ViewModels.CrawlerVM;

import java.util.Date;

/**
 * Created by cem on 21.11.16.
 */
public class NewsEntryVM {
    private String id;
    private String title;
    private String image;
    private String content;
    private String source;
    private String url;
    private Date date;

    public NewsEntryVM() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    public NewsEntryModel getNewsEntryModel() {
        NewsEntryModel model = new NewsEntryModel();
        model.setId(getId());
        model.setTitle(getTitle());
        model.setImage(getImage());
        model.setContent(getContent());
        model.setSource(getSource());
        model.setUrl(getUrl());
        model.setDate(getDate());
        return model;
    }
}
