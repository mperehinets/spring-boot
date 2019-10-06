package com.mper.springboot.service;

import com.mper.springboot.dao.Book;
import com.mper.springboot.dto.AuthorDto;
import com.mper.springboot.dto.BookDto;
import com.mper.springboot.exception.BookIdMismatchException;
import com.mper.springboot.exception.BookNotFoundException;
import com.mper.springboot.mapperDto.AuthorMapper;
import com.mper.springboot.mapperDto.BookMapper;
import com.mper.springboot.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;

class BookServiceImplTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookServiceImpl bookService;

    private BookMapper bookMapper;

    private BookDto bookDto;

    private Book book;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
        bookMapper = new BookMapper(new AuthorMapper());
        bookDto = new BookDto(1L, "Game of thrones", new AuthorDto(1L, "Daniel Brawn"));
        book = bookMapper.toDao(bookDto);
        bookService = new BookServiceImpl(bookRepository, bookMapper);
    }

    @Test
    public void testCreateBook() {
        doReturn(book).when(bookRepository).save(book);
        assertThat(bookService.create(bookDto)).isEqualTo(bookDto);
    }

    @Test
    public void testFindAllBooks() {
        doReturn(Collections.singletonList(book)).when(bookRepository).findAll();
        assertThat(bookService.findAll()).isEqualTo(Collections.singletonList(bookDto));
    }

    @Test
    public void testFindBookById() {
        doReturn(Optional.of(book)).when(bookRepository).findById(book.getId());
        assertThat(bookService.findById(bookDto.getId())).isEqualTo(bookDto);
    }

    @Test
    public void testFindBookById_NonexistentIdGiven_ShouldThrowBookNotFoundException() {
        doReturn(Optional.empty()).when(bookRepository).findById(Long.MAX_VALUE);
        assertThrows(BookNotFoundException.class, () -> bookService.findById(Long.MAX_VALUE));
    }

    @Test
    public void testUpdateBook() {
        doReturn(book).when(bookRepository).save(book);
        doReturn(Optional.of(book)).when(bookRepository).findById(book.getId());
        assertThat(bookService.update(bookDto, bookDto.getId())).isEqualTo(bookDto);
    }

    @Test
    public void testUpdateBook_IdFromPathNotEqualIdFromEntity_ShouldThrowBookIdMismatchException() {
        assertThrows(BookIdMismatchException.class, () -> bookService.update(bookDto, Long.MAX_VALUE));
    }

    @Test
    public void testUpdateBook_NonexistentIdGiven_ShouldThrowBookNotFoundException() {
        doReturn(Optional.empty()).when(bookRepository).findById(Long.MAX_VALUE);
        assertThrows(BookNotFoundException.class, () -> bookService.update(bookDto, bookDto.getId()));
    }

    @Test
    public void testDeleteBookById() {
        doReturn(Optional.of(book)).when(bookRepository).findById(book.getId());
        doNothing().when(bookRepository).deleteById(book.getId());
        bookService.delete(bookDto.getId());
    }

    @Test
    public void testDeleteBookById_NonexistentIdGiven_ShouldThrowBookNotFoundException(){
        doReturn(Optional.empty()).when(bookRepository).findById(Long.MAX_VALUE);
        assertThrows(BookNotFoundException.class, () -> bookService.update(bookDto, bookDto.getId()));
    }

}
