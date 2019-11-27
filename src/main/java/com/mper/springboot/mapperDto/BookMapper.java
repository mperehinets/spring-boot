package com.mper.springboot.mapperDto;

import com.mper.springboot.dao.Book;
import com.mper.springboot.dto.BookDto;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {

    public static Book toDao(BookDto bookDto) {
        Book book = new Book();
        book.setId(bookDto.getId());
        book.setTitle(bookDto.getTitle());
        book.setAuthor(AuthorMapper.toDao(bookDto.getAuthorDto()));
        book.setStatus(bookDto.getStatus());
        return book;
    }

    public static BookDto toDto(Book book) {
        BookDto bookDto = new BookDto();
        bookDto.setId(book.getId());
        bookDto.setTitle(book.getTitle());
        bookDto.setAuthorDto(AuthorMapper.toDto(book.getAuthor()));
        bookDto.setStatus(book.getStatus());
        return bookDto;
    }

}
