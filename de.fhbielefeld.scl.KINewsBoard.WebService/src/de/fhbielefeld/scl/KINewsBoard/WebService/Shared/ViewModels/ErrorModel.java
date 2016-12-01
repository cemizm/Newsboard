package de.fhbielefeld.scl.KINewsBoard.WebService.Shared.ViewModels;

/**
 * Created by cem on 01.12.16.
 */
public class ErrorModel {
    private String message;

    public ErrorModel(){

    }

    public ErrorModel(String message){
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
