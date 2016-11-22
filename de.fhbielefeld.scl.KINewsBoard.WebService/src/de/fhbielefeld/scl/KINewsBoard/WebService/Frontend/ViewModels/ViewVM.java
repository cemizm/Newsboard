package de.fhbielefeld.scl.KINewsBoard.WebService.Frontend.ViewModels;

import de.fhbielefeld.scl.KINewsBoard.BusinessLayer.Models.ViewModel;

import java.util.List;

/**
 * Created by cem on 21.11.16.
 */
public class ViewVM {

    private int id;
    private String name;
    private List<NewsEntryVM> newsEntries;

    public ViewVM() {

    }

    public ViewVM(ViewModel viewModel, List<NewsEntryVM> newsEntries) {
        this.id = viewModel.getId();
        this.name = viewModel.getName();
        this.newsEntries = newsEntries;
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

    public List<NewsEntryVM> getNewsEntries() {
        return newsEntries;
    }

    public void setNewsEntries(List<NewsEntryVM> newsEntries) {
        this.newsEntries = newsEntries;
    }
}
