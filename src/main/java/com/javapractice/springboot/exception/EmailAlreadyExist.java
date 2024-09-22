package com.javapractice.springboot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EmailAlreadyExist extends RuntimeException{
    private String message;

    public EmailAlreadyExist(String message) {
        super(message);
        this.message = message;

    }
}
