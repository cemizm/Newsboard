package de.fhbielefeld.scl.KINewsBoard.WebService.Shared.ExceptionMappers;

import de.fhbielefeld.scl.KINewsBoard.WebService.Shared.ViewModels.ErrorModel;

import javax.naming.AuthenticationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Die Klasse <i>AuthenticationExceptionMapper</i> ist für das Zuordnen einer Ausnahme <i>AuthenticationException</i> zu einer HTTP-Antwort zuständig.
 */
@Provider
public class AuthenticationExceptionMapper implements ExceptionMapper<AuthenticationException> {

    /**
     * Ordnet eine Ausnahme einer HTTP-Antwort zu.
     *
     * @param e Die Ausnahme, die der HTTP-Antwort zuzuordnen ist
     * @return HTTP-Antwort, dem die Ausnahme zugeordnet ist
     */
    @Override
    public Response toResponse(AuthenticationException e) {
        return Response.status(Response.Status.UNAUTHORIZED)
                .entity(new ErrorModel(e.getMessage()))
                .build();
    }

}