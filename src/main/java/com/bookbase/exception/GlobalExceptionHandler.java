package com.bookbase.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public String handleResourceNotFoundException(ResourceNotFoundException exception){
        return exception.getMessage();
    }

    @ExceptionHandler(APIException.class)
    public String handleAPIException(APIException exception){
        return exception.getMessage();
    }
}
