package com.bookbase.exception;

import org.springframework.http.HttpStatus;

public class ResourceAlreadyExistsException extends APIException{
        public ResourceAlreadyExistsException(String message) {
            super(message, HttpStatus.CONFLICT.value());
        }

}
