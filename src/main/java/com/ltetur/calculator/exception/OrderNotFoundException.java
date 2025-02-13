package com.ltetur.calculator.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown when an order is not found in the database.
 * <p>
 * This exception is annotated with {@link ResponseStatus} to automatically
 * return a 404 NOT FOUND status when it is thrown.
 * </p>
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class OrderNotFoundException extends RuntimeException {

    /**
     * Constructs a new {@code OrderNotFoundException} with the specified detail message.
     *
     * @param message the detail message describing the reason for the exception
     */
    public OrderNotFoundException(String message) {
        super(message);
    }
}
