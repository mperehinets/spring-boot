package com.mper.springboot.exception;

public class AuthorNotFoundException extends RuntimeException {

    public AuthorNotFoundException(Long authorId){
        super(String.format("Author is not found with id: '%s' ", authorId));
    }

}
