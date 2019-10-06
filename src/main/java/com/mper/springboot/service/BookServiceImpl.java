package com.mper.springboot.service;

import com.mper.springboot.dto.BookDto;
import com.mper.springboot.exception.BookIdMismatchException;
import com.mper.springboot.exception.BookNotFoundException;
import com.mper.springboot.dao.Book;
import com.mper.springboot.mapperDto.BookMapper;
import com.mper.springboot.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;

    private BookMapper bookMapper;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    @Override
    public List<BookDto> findAll() {
        List<Book> books = (List<Book>) bookRepository.findAll();
        return books.stream().map(bookMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public BookDto findById(Long id) {
        return bookMapper.toDto(bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id)));
    }

    @Override
    public BookDto create(BookDto bookDto) {
        return bookMapper.toDto(bookRepository.save(bookMapper.toDao(bookDto)));
    }

    @Override
    public BookDto update(BookDto bookDto, Long id) {
        if (bookDto.getId() != id)
            throw new BookIdMismatchException(bookDto, id);
        findById(id);
        return bookMapper.toDto(bookRepository.save(bookMapper.toDao(bookDto)));
    }

    @Override
    public void delete(Long id) {
        bookRepository.deleteById(id);
    }
}
