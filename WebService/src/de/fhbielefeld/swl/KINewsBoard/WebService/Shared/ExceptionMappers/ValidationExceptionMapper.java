package de.fhbielefeld.swl.KINewsBoard.WebService.Shared.ExceptionMappers;

import de.fhbielefeld.swl.KINewsBoard.WebService.Shared.ViewModels.ErrorModel;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Die Klasse <i>ValidationExceptionMapper</i> ist für das Zuordnen der Ausnahme <i>ConstraintViolationException</i> zu einer HTTP-Antwort zuständig.
 */
@Provider
public class ValidationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {

    /**
     * Ordnet eine Ausnahme einer HTTP-Antwort zu.
     *
     * @param e Die Ausnahme, die der HTTP-Antwort zuzuordnen ist
     * @return HTTP-Antwort, dem die Ausnahme zugeordnet ist
     */
    @Override
    public Response toResponse(ConstraintViolationException e) {
        String msg = "";
        for (ConstraintViolation<?> cv : e.getConstraintViolations()) {
            if (!msg.isEmpty())
                msg += "\n";

            msg += "Eigenschaft '" + cv.getPropertyPath().toString().replaceAll(".*?\\..*?\\.", "") + "': " + cv.getMessage();
        }
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(new ErrorModel(msg))
                .build();
    }

}
