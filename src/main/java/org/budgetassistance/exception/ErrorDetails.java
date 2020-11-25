package org.budgetassistance.exception;

public class ErrorDetails {

    private final String message;

    public ErrorDetails(RuntimeException exception) {
        message = exception.getMessage();
    }

    public String getMessage() {
        return message;
    }
}
