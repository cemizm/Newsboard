package de.fhbielefeld.scl.KINewsBoard.WebService.Shared.ViewModels;

/**
 * Die Klasse <i>ErrorModel</i> stellt eine Fehlermeldung dar.
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

    /**
     * Ruft die Fehlermeldung ab.
     *
     * @return Die Fehlermeldung
     */
    public String getMessage() {
        return message;
    }

    /**
     * Legt die Fehlermeldung fest.
     *
     * @param message Die festzulegende Fehlermeldung
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
