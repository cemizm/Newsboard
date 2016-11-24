package de.fhbielefeld.scl.KINewsBoard.WebService.Frontend.ViewModels;

import de.fhbielefeld.scl.KINewsBoard.BusinessLayer.Models.AnalyzerResultModel;
import de.fhbielefeld.scl.KINewsBoard.BusinessLayer.Models.NewsEntryModel;

import java.util.Date;

/**
 * Created by cem on 21.11.16.
 */
public class NewsEntryVM {
    private String id;
    private CrawlerVM crawler;
    private String title;
    private String image;
    private String content;
    private String source;
    private String url;
    private Date date;
    private int analyzerResult;

    public NewsEntryVM() {

    }

    public NewsEntryVM(NewsEntryModel model) {
        id = model.getId();
        crawler = new CrawlerVM(model.getCrawler());
        title = model.getTitle();
        image = model.getImage();
        content = model.getContent();
        source = model.getSource();
        url = model.getUrl();
        date = model.getDate();

        for (AnalyzerResultModel res :
                model.getAnalyzerResultModels()) {
            analyzerResult += res.getValue();
        }

        if (model.getAnalyzerResultModels().size() > 0)
            analyzerResult /= model.getAnalyzerResultModels().size();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public CrawlerVM getCrawler() {
        return crawler;
    }

    public void setCrawler(CrawlerVM crawler) {
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

    public int getAnalyzerResult() {
        return analyzerResult;
    }

    public void setAnalyzerResult(int analyzerResult) {
        this.analyzerResult = analyzerResult;
    }
}
