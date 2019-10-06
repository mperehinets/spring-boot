package com.mper.springboot.exception;

public class BookNotFoundException extends RuntimeException {

    public BookNotFoundException(Long bookId) {
        super(String.format("Book is not found with id: '%s' ", bookId));
    }

}
