package com.mper.springboot;

import com.mper.springboot.exception.AuthorNotFoundException;
import com.mper.springboot.exception.BookIdMismatchException;
import com.mper.springboot.exception.BookNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler({BookNotFoundException.class, AuthorNotFoundException.class})
    protected ResponseEntity<Object> handleNotFound(Exception ex, WebRequest request) {
        if(ex instanceof BookNotFoundException){
            return new ResponseEntity<Object>("Book not found", new HttpHeaders(), HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<Object>("Author not found", new HttpHeaders(), HttpStatus.NOT_FOUND);
        }

    }

    @ExceptionHandler({BookIdMismatchException.class, ConstraintViolationException.class,
            DataIntegrityViolationException.class})
    public ResponseEntity<Object> handleBadRequest(Exception ex, WebRequest request) {
        return new ResponseEntity<Object>(new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
}
