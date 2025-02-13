package com.ltetur.calculator.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown when a template is not found in the database.
 * <p>
 * This exception is annotated with {@link ResponseStatus} to automatically
 * return a 404 NOT FOUND status when it is thrown.
 * </p>
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class TemplateNotFoundException extends RuntimeException {

    /**
     * Constructs a new {@code TemplateNotFoundException} with the specified detail message.
     *
     * @param message the detail message describing the reason for the exception
     */
    public TemplateNotFoundException(String message) {
        super(message);
    }
}