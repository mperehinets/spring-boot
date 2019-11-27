package com.mper.springboot.service;

import com.mper.springboot.dto.BookDto;

import java.util.Collection;

public interface BookService {

    BookDto create(BookDto bookDto);

    BookDto update(BookDto bookDto);

    BookDto findById(Long id);

    Collection<BookDto> findAll();

    void deleteById(Long id);

}
