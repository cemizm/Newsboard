package de.fhbielefeld.scl.KINewsBoard.WebService.Shared.ExceptionMappers;

import de.fhbielefeld.scl.KINewsBoard.WebService.Shared.ViewModels.ErrorModel;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Die Klasse <i>IllegalArgumentExceptionMapper</i> ist für das Zuordnen der Ausnahme <i>IllegalArgumentException</i> zu einer HTTP-Antwort zuständig.
 */
@Provider
public class IllegalArgumentExceptionMapper implements ExceptionMapper<IllegalArgumentException> {

    /**
     * Ordnet eine Ausnahme einer HTTP-Antwort zu.
     *
     * @param e Die Ausnahme, die der HTTP-Antwort zuzuordnen ist
     * @return HTTP-Antwort, dem die Ausnahme zugeordnet ist
     */
    @Override
    public Response toResponse(IllegalArgumentException e) {
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(new ErrorModel(e.getMessage()))
                .build();
    }

}