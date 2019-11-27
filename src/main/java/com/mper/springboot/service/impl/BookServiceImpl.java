package com.mper.springboot.service.impl;

import com.mper.springboot.dao.Book;
import com.mper.springboot.dto.BookDto;
import com.mper.springboot.exception.BookNotFoundException;
import com.mper.springboot.mapperDto.BookMapper;
import com.mper.springboot.repository.BookRepository;
import com.mper.springboot.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public BookDto create(BookDto bookDto) {
        return BookMapper.toDto(bookRepository.save(BookMapper.toDao(bookDto)));
    }

    @Override
    public BookDto update(BookDto bookDto) {
        findById(bookDto.getId());
        return BookMapper.toDto(bookRepository.save(BookMapper.toDao(bookDto)));
    }

    @Override
    public BookDto findById(Long id) {
        return BookMapper.toDto(bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book was not found by id=" + id)));
    }

    @Override
    public Collection<BookDto> findAll() {
        Collection<Book> books = (Collection<Book>) bookRepository.findAll();
        return books.stream().map(BookMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        findById(id);
        bookRepository.deleteById(id);
    }
}
