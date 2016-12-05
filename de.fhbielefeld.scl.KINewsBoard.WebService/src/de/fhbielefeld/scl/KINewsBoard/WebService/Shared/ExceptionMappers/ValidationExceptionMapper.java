package de.fhbielefeld.scl.KINewsBoard.WebService.Shared.ExceptionMappers;

import de.fhbielefeld.scl.KINewsBoard.WebService.Shared.ViewModels.ErrorModel;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Created by cem on 05.12.16.
 */
@Provider
public class ValidationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {

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
