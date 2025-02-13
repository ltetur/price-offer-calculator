package com.ltetur.calculator.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

/**
 * Global exception handler for the application.
 * This class handles various exceptions and returns appropriate error responses.
 */
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Handles {@link OrderNotFoundException} and returns a 404 NOT FOUND response.
     *
     * @param ex      the exception instance
     * @param request the web request context
     * @return a {@link ResponseEntity} containing error details
     * @throws Exception if an error occurs while handling the exception
     */
    @ExceptionHandler(OrderNotFoundException.class)
    public final ResponseEntity<ErrorDetails> handleOrderNotFoundException(OrderNotFoundException ex, WebRequest request) throws Exception {
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),
                ex.getMessage(), request.getDescription(false));

        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    /**
     * Handles {@link TemplateNotFoundException} and returns a 404 NOT FOUND response.
     *
     * @param ex      the exception instance
     * @param request the web request context
     * @return a {@link ResponseEntity} containing error details
     * @throws Exception if an error occurs while handling the exception
     */
    @ExceptionHandler(TemplateNotFoundException.class)
    public final ResponseEntity<ErrorDetails> handleTemplateNotFoundException(TemplateNotFoundException ex, WebRequest request) throws Exception {
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),
                ex.getMessage(), request.getDescription(false));

        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    /**
     * Handles {@link EntityNotFoundException} and returns a 404 NOT FOUND response.
     *
     * @param ex      the exception instance
     * @param request the web request context
     * @return a {@link ResponseEntity} containing error details
     * @throws Exception if an error occurs while handling the exception
     */
    @ExceptionHandler(EntityNotFoundException.class)
    public final ResponseEntity<ErrorDetails> handleEntityNotFoundException(EntityNotFoundException ex, WebRequest request) throws Exception {
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),
                ex.getMessage(), request.getDescription(false));

        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    /**
     * Handles validation errors thrown when request parameters fail validation.
     * Returns a 400 BAD REQUEST response with details of the validation errors.
     *
     * @param ex      the exception instance
     * @param headers the HTTP headers
     * @param status  the HTTP status code
     * @param request the web request context
     * @return a {@link ResponseEntity} containing error details
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        StringBuilder errorMessages = new StringBuilder();
        ex.getFieldErrors().forEach(error -> errorMessages.append(error.getDefaultMessage()).append("; "));

        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),
                "Validation errors: " + errorMessages, request.getDescription(false));

        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
}
