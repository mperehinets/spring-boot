package com.mper.springboot.service;

import com.mper.springboot.dto.BookDto;

import java.util.Collection;

public interface BookService {

    BookDto createBook(BookDto bookDto);

    BookDto updateBook(BookDto bookDto);

    BookDto findBookById(Long bookId);

    Collection<BookDto> findAllBooks();

    void deleteBookById(Long bookId);

}
