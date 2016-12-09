package de.fhbielefeld.scl.KINewsBoard.WebService.Shared.ExceptionMappers;

import de.fhbielefeld.scl.KINewsBoard.WebService.Shared.ViewModels.ErrorModel;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class DefaultExceptionMapper implements ExceptionMapper<Exception> {

    @Override
    public Response toResponse(Exception e) {
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(new ErrorModel(e))
                .build();
    }

}