package de.fhbielefeld.scl.KINewsBoard.WebService.Shared.ViewModels;

/**
 * Created by cem on 01.12.16.
 */
public class ErrorModel {
    private String message;

    public ErrorModel() {

    }

    public ErrorModel(String message) {
        this.message = message;
    }

    public ErrorModel(Throwable ex) {
        this.message = "";
        do {

            this.message = ex.getMessage();

            if (ex.getCause() == ex)
                ex = null;
            else
                ex = ex.getCause();
        } while ((this.message == null || this.message.isEmpty()) && ex != null);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
