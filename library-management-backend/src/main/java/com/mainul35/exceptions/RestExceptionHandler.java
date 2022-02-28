package com.mainul35.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@ControllerAdvice
public class RestExceptionHandler {

    public static final String LIMIT_REACHED_ERROR = "limit_reached_error";
    public static final String DUPLICATE_ENTRY = "duplicate_entry";
    public static final String NO_ELEMENT_PRESENT = "no_element_present";
    public static final String VALIDATION_ERROR = "validation_error";

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

    /**
     * @param ex      LimitReachedException.class
     * @return the ErrorResponse object
     */
    @ExceptionHandler(value = {NoSuchElementException.class})
    protected ResponseEntity<?> noSuchElement(NoSuchElementException ex) {
        ErrorResponse response = new ErrorResponse(NO_ELEMENT_PRESENT, ex.getMessage());
        this.printStackTrace(ex);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    /**
     * @param ex      LimitReachedException.class
     * @return the ErrorResponse object
     */
    @ExceptionHandler(value = {DuplicateEntryException.class})
    protected ResponseEntity<?> noSuchElement(DuplicateEntryException ex) {
        ErrorResponse response = new ErrorResponse(DUPLICATE_ENTRY, ex.getMessage());
        this.printStackTrace(ex);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Handle TypeMismatchException. Triggered when a 'required' request parameter is missing.
     *
     * @param ex      MethodArgumentNotValidException.class
     * @return the ErrorResponse object
     */
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex) {
        ErrorResponse response = new ErrorResponse(VALIDATION_ERROR, "Form is not filled properly");
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        List<ItemValidationError> validationErrors = new LinkedList<>();
        fieldErrors.forEach((v) -> {
            validationErrors.add(new ItemValidationError(v.getObjectName(), v.getField(), v.getRejectedValue(), v.getDefaultMessage()));
        });
        response.setErrorItems(validationErrors);

        this.printStackTrace(ex);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handle TypeMismatchException. Triggered when a 'required' request parameter is missing.
     *
     * @param ex      BindException.class
     * @param headers HttpHeaders
     * @param status  HttpStatus
     * @param request WebRequest
     * @return the ErrorResponse object
     */
    @ExceptionHandler(value = {BindException.class})
    protected ResponseEntity<Object> handleBindException(
            BindException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        ErrorResponse response = new ErrorResponse(VALIDATION_ERROR, "Request is not valid");
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        List<ItemValidationError> validationErrors = new LinkedList<> ();
        fieldErrors.forEach((v) -> {
            validationErrors.add(new ItemValidationError(v.getObjectName(), v.getField(), v.getRejectedValue(), v.getDefaultMessage()));
        });
        response.setErrorItems(validationErrors);

        this.printStackTrace(ex);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
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
