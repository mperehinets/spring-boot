package com.mper.springboot.service;

import com.mper.springboot.dto.BookDto;

import java.util.List;

public interface BookService {

    List<BookDto> findAll();

    BookDto findById(Long id);

    BookDto create(BookDto bookDto);

    BookDto update(BookDto bookDto, Long id);

    void delete(Long id);

}
