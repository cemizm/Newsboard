package de.fhbielefeld.scl.KINewsBoard.WebService.Shared.ViewModels;

import de.fhbielefeld.scl.KINewsBoard.DataLayer.DataModels.NewsEntry;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * Created by cem on 21.11.16.
 */
public class NewsEntryBaseModel {
    private String id;
    private String title;
    private String image;
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
        content = entry.getContent();
        source = entry.getSource();
        url = entry.getUrl();
        date = entry.getDate();
    }

    @NotNull
    @Size(min = 3, max = 255)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    @Size(max = 1024)
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

    @NotNull
    @Size(min = 10, max = 10485760)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @NotNull
    @Size(min = 3, max = 255)
    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @NotNull
    @Size(min = 10, max = 512)
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

    public NewsEntry getNewsEntryModel() {
        NewsEntry entry = new NewsEntry();
        entry.setId(getId());
        entry.setTitle(getTitle());
        entry.setImage(getImage());
        entry.setContent(getContent());
        entry.setSource(getSource());
        entry.setUrl(getUrl());
        entry.setDate(getDate());
        return entry;
    }
}
