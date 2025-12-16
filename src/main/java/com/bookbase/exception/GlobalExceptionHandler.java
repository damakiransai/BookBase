package com.bookbase.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException exception){
        return ResponseEntity.
                status(HttpStatus.NOT_FOUND).
                body(exception.getMessage());
    }

    @ExceptionHandler(APIException.class)
    public ResponseEntity<String> handleAPIException(APIException exception){
        return ResponseEntity.
                status(exception.getStatusCode()).
                body(exception.getMessage());
    }
}
