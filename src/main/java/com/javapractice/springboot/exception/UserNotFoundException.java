package com.javapractice.springboot.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {
    private String resourceName;
    private String fieldName;
    private String fieldValue;

    public  UserNotFoundException(String resourceName,String fieldName,String fieldValue){
        super(String.format("%s not found with %s : %s", resourceName, fieldName, fieldValue));
        this.fieldName=fieldName;
        this.resourceName=resourceName;
        this.fieldValue=fieldValue;

    }
}
