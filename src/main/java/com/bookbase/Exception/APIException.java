package com.bookbase.Exception;


//to handle generic API-Related exceptions
public class APIException extends RuntimeException{
    private int statusCode;
    APIException(String message){
        super(message);
        this.statusCode=400;
    }

    APIException(String message,int statusCode){
        super(message);
        this.statusCode=statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
