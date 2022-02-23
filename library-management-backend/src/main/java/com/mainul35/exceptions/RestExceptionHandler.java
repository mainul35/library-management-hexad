package com.mainul35.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Arrays;

@Slf4j
@ControllerAdvice
public class RestExceptionHandler {

    public static final String LIMIT_REACHED_ERROR = "limit_reached_error";

    /**
     * @param ex      LimitReachedException.class
     * @return the ErrorResponse object
     */
    @ExceptionHandler(value = {LimitReachedException.class})
    protected ResponseEntity<?> handleLimitReached(LimitReachedException ex) {
        ErrorResponse response = new ErrorResponse(LIMIT_REACHED_ERROR, ex.getMessage());
        this.printStackTrace(ex);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private void printStackTrace(Exception ex) {
        StackTraceElement[] trace = ex.getStackTrace();
        StringBuilder traceLines = new StringBuilder();
        traceLines.append("Caused By: ").append(ex.fillInStackTrace()).append("\n");
        Arrays.stream(trace).filter(f -> f.getClassName().contains("com.mainul35"))
                .forEach(traceElement -> traceLines.append("\tat ").append(traceElement).append("\n"));
        log.error(traceLines.toString());
    }
}
