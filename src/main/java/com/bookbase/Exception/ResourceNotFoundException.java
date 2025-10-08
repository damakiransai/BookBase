package com.bookbase.Exception;

import lombok.Getter;

@Getter
public class ResourceNotFoundException extends RuntimeException{
    private String resourceName;
    private String field;
    private Object fieldValue;// because field value might be a number or a string as shown below

    //    throw new ResourceNotFoundException("Book", "id", 101L);
    // Message: Resource 'Book' Not Found with id:101

    //throw new ResourceNotFoundException("Book", "name", "Java Basics");
    // Message: Resource 'Book' Not Found with name:Java Basics




    public ResourceNotFoundException(String resourceName, String field, Object fieldValue) {
        super(String.format("Resource '%s' Not Found with %s:%s", resourceName, field, fieldValue));
        this.resourceName = resourceName;
        this.field = field;
        this.fieldValue = fieldValue;
    }
}
