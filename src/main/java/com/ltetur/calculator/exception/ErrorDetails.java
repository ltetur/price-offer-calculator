package com.ltetur.calculator.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Represents details of an error that occurs within the application.
 * This class is used for structuring error responses in a readable format.
 */
@Data
@AllArgsConstructor
public class ErrorDetails {

    private LocalDateTime timestamp;
    private String message;
    private String details;

}