package com.mper.springboot.service;

import com.mper.springboot.dao.Book;
import com.mper.springboot.dao.Status;
import com.mper.springboot.dto.BookDto;
import com.mper.springboot.dto.UserDto;
import com.mper.springboot.exception.BookNotFoundException;
import com.mper.springboot.mapperDto.BookMapper;
import com.mper.springboot.repository.BookRepository;
import com.mper.springboot.service.impl.BookServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;

class BookServiceImplTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookServiceImpl bookService;

    private BookDto bookDto;

    private Book book;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);

        UserDto userDto = new UserDto();
        userDto.setId(1L);
        userDto.setStatus(Status.ACTIVE);
        userDto.setFirstName("Maksym");
        userDto.setLastName("Perehinets");
        userDto.setEmail("mpereginec1@gmail.com");


        bookDto = new BookDto();
        bookDto.setId(1L);
        bookDto.setStatus(Status.ACTIVE);
        bookDto.setTitle("Game of thrones");
        bookDto.setAuthorDto(userDto);

        book = BookMapper.toDao(bookDto);
        bookService = new BookServiceImpl(bookRepository);
    }

    @Test
    public void testCreateBook() {
        doReturn(book).when(bookRepository).save(book);
        assertThat(bookService.createBook(bookDto)).isEqualTo(bookDto);
    }

    @Test
    public void testFindAllBooks() {
        doReturn(Collections.singletonList(book)).when(bookRepository).findAll();
        assertThat(bookService.findAllBooks()).isEqualTo(Collections.singletonList(bookDto));
    }

    @Test
    public void testFindBookById() {
        doReturn(Optional.of(book)).when(bookRepository).findById(book.getId());
        assertThat(bookService.findBookById(bookDto.getId())).isEqualTo(bookDto);
    }

    @Test
    public void testFindBookById_NonExistentIdGiven_ShouldThrowBookNotFoundException() {
        doReturn(Optional.empty()).when(bookRepository).findById(Long.MAX_VALUE);
        assertThrows(BookNotFoundException.class, () -> bookService.findBookById(Long.MAX_VALUE));
    }

    @Test
    public void testUpdateBook() {
        doReturn(book).when(bookRepository).save(book);
        doReturn(Optional.of(book)).when(bookRepository).findById(book.getId());
        assertThat(bookService.updateBook(bookDto)).isEqualTo(bookDto);
    }

    @Test
    public void testUpdateBook_NonExistentIdGiven_ShouldThrowBookNotFoundException() {
        doReturn(Optional.empty()).when(bookRepository).findById(Long.MAX_VALUE);
        bookDto.setId(Long.MAX_VALUE);
        assertThrows(BookNotFoundException.class, () -> bookService.updateBook(bookDto));
        bookDto.setId(1L);
    }

    @Test
    public void testDeleteBookById() {
        doReturn(Optional.of(book)).when(bookRepository).findById(book.getId());
        doNothing().when(bookRepository).deleteById(book.getId());
        bookService.deleteBookById(bookDto.getId());
    }

    @Test
    public void testDeleteBookById_NonexistentIdGiven_ShouldThrowBookNotFoundException() {
        doReturn(Optional.empty()).when(bookRepository).findById(Long.MAX_VALUE);
        assertThrows(BookNotFoundException.class, () -> bookService.deleteBookById(Long.MAX_VALUE));
    }

}
