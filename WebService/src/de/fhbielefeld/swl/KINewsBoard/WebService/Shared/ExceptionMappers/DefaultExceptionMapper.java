package de.fhbielefeld.swl.KINewsBoard.WebService.Shared.ExceptionMappers;

import de.fhbielefeld.swl.KINewsBoard.WebService.Shared.ViewModels.ErrorModel;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Die Klasse <i>DefaultExceptionMapper</i> ist für das Zuordnen der Ausnahme <i>Exception</i> zu einer HTTP-Antwort zuständig.
 */
@Provider
public class DefaultExceptionMapper implements ExceptionMapper<Exception> {

    /**
     * Ordnet eine Ausnahme einer HTTP-Antwort zu.
     *
     * @param e Die Ausnahme, die der HTTP-Antwort zuzuordnen ist
     * @return HTTP-Antwort, dem die Ausnahme zugeordnet ist
     */
    @Override
    public Response toResponse(Exception e) {
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(new ErrorModel(e))
                .build();
    }

}