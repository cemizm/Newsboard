package de.fhbielefeld.scl.KINewsBoard.WebService.Analyzer.ViewModels;

import de.fhbielefeld.scl.KINewsBoard.WebService.Shared.ViewModels.AnalyzerResultBaseModel;

/**
 * Created by cem on 24.11.16.
 */
public class AnalyzerResultVM extends AnalyzerResultBaseModel {
    private String newsId;

    public AnalyzerResultVM() {
        super();
    }

    public String getNewsId() {
        return newsId;
    }

    public void setNewsId(String newsId) {
        this.newsId = newsId;
    }
}
