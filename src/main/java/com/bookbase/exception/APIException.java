package com.bookbase.exception;

//to handle generic API-Related exceptions
public class APIException extends RuntimeException{
    private int statusCode;
    public APIException(String message){
        super(message);
        this.statusCode=400;
    }

    public APIException(String message,int statusCode){
        super(message);
        this.statusCode=statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
