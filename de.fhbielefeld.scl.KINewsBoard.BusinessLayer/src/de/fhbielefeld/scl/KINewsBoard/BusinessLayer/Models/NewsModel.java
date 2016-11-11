package de.fhbielefeld.scl.KINewsBoard.BusinessLayer.Models;


import de.fhbielefeld.scl.KINewsBoard.DataLayer.DataModels.NewsEntry;

import java.util.Date;

/**
 * Created by cem on 10.11.16.
 */
public class NewsModel {
    private String id;
    private int crawler_id;
    private String title;
    private String image;
    private String content;
    private String source;
    private String url;
    private Date date;

    public NewsModel(NewsEntry entry) {
        id = entry.getId();
        crawler_id = entry.getCrawler().getId();
        title = entry.getTitle();
        image = entry.getImage();
        content = entry.getContent();
        source = entry.getSource();
        url = entry.getUrl();
        date = entry.getDate();
    }

    public NewsModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCrawler_id() {
        return crawler_id;
    }

    public void setCrawler_id(int crawler_id) {
        this.crawler_id = crawler_id;
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

}
