package de.fhbielefeld.scl.KINewsBoard.BusinessLayer.Models;

import de.fhbielefeld.scl.KINewsBoard.DataLayer.DataModels.NewsEntry;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by cem on 10.11.16.
 */
public class NewsEntryModel {
    private String id;
    private CrawlerModel crawler;
    private String title;
    private String image;
    private String content;
    private String source;
    private String url;
    private Date date;

    private List<AnalyzerResultModel> analyzerResultModels;

    public NewsEntryModel() {
        analyzerResultModels = new ArrayList<>();
    }

    public NewsEntryModel(NewsEntry entry) {
        this(entry, false);
    }

    public NewsEntryModel(NewsEntry entry, boolean includeRelations) {
        this();

        id = entry.getId();
        crawler = new CrawlerModel(entry.getCrawler());
        title = entry.getTitle();
        image = entry.getImage();
        content = entry.getContent();
        source = entry.getSource();
        url = entry.getUrl();
        date = entry.getDate();

        if (includeRelations) {
            analyzerResultModels.addAll(entry.getAnalyzerResults()
                    .stream()
                    .map(AnalyzerResultModel::new)
                    .collect(Collectors.toList()));
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public CrawlerModel getCrawler() {
        return crawler;
    }

    public void setCrawler(CrawlerModel crawler) {
        this.crawler = crawler;
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

    public List<AnalyzerResultModel> getAnalyzerResultModels() {
        return analyzerResultModels;
    }

    public void setAnalyzerResultModels(List<AnalyzerResultModel> analyzerResultModels) {
        this.analyzerResultModels = analyzerResultModels;
    }
}
