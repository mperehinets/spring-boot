package com.mper.springboot;

import com.mper.springboot.exception.BookNotFoundException;
import com.mper.springboot.exception.UserNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler({BookNotFoundException.class, UserNotFoundException.class})
    protected ResponseEntity<Object> handleNotFound(Exception ex, WebRequest request) {
        if (ex instanceof BookNotFoundException) {
            return new ResponseEntity<Object>("Book not found", new HttpHeaders(), HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<Object>("User not found", new HttpHeaders(), HttpStatus.NOT_FOUND);
        }

    }

}
