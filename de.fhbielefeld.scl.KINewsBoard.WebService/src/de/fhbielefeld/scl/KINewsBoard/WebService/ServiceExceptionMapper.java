package de.fhbielefeld.scl.KINewsBoard.WebService;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by cem on 22.11.16.
 */
@Provider
public class ServiceExceptionMapper
        implements ExceptionMapper<Throwable> {
    @Override
    public Response toResponse(Throwable e) {

        ErrorMessage msg = new ErrorMessage(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), e);

        return Response
                .status(msg.getErrorCode())
                .entity(msg)
                .build();
    }

    static class ErrorMessage {

        private int errorCode;
        private String errorMessage;
        private String stackTrace;

        public ErrorMessage(int errorCode, Throwable throwable) {
            this.errorCode = errorCode;
            errorMessage = throwable.getMessage();
            StringWriter errorStackTrace = new StringWriter();
            throwable.printStackTrace(new PrintWriter(errorStackTrace));
            stackTrace = errorStackTrace.toString();
        }

        public int getErrorCode() {
            return errorCode;
        }

        public void setErrorCode(int errorCode) {
            this.errorCode = errorCode;
        }

        public String getErrorMessage() {
            return errorMessage;
        }

        public void setErrorMessage(String errorMessage) {
            this.errorMessage = errorMessage;
        }

        public String getStackTrace() {
            return stackTrace;
        }

        public void setStackTrace(String stackTrace) {
            this.stackTrace = stackTrace;
        }
    }
}