package com.mper.springboot.controller;

import com.mper.springboot.dto.BookDto;
import com.mper.springboot.mapperDto.BookMapper;
import com.mper.springboot.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/books")
public class BookController {

    private BookService bookService;

    private BookMapper bookMapper;

    @Autowired
    public BookController(BookService bookService, BookMapper bookMapper) {
        this.bookService = bookService;
        this.bookMapper = bookMapper;
    }

    @GetMapping
    public Collection<BookDto> findAllBooks() {
        return bookService.findAllBooks();
    }

    @GetMapping("/{bookId}")
    public BookDto findBookById(@PathVariable Long bookId) {
        return bookService.findBookById(bookId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookDto create(@RequestBody BookDto bookDto) {
        return bookService.createBook(bookDto);
    }

    @PutMapping
    public BookDto updateBook(@RequestBody BookDto bookDto) {
        return bookService.updateBook(bookDto);
    }

    @DeleteMapping("/{bookId}")
    public void delete(@PathVariable Long bookId) {
        bookService.deleteBookById(bookId);
    }
}
