package org.budgetassistance.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(value = {ResourceNotFoundException.class})
    public ResponseEntity<ErrorDetails> handleResourceNotFound(ResourceNotFoundException exception) {
        return new ResponseEntity(new ErrorDetails(exception), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {InvalidParameterException.class})
    public ResponseEntity<ErrorDetails> handleInvalidParameter(InvalidParameterException exception) {
        return new ResponseEntity(new ErrorDetails(exception), HttpStatus.BAD_REQUEST);
    }
}
