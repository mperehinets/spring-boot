package com.mper.springboot.mapperDto;

import com.mper.springboot.dao.Author;
import com.mper.springboot.dto.BookDto;
import com.mper.springboot.dao.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {

    private AuthorMapper authorMapper;

    @Autowired
    public BookMapper(AuthorMapper authorMapper){
        this.authorMapper = authorMapper;
    }

    public Book toDao(BookDto bookDto) {
        Book book = new Book();
        book.setId(bookDto.getId());
        book.setTitle(bookDto.getTitle());
        book.setAuthor(authorMapper.toDao(bookDto.getAuthorDto()));
        return book;
    }

    public BookDto toDto(Book book){
        BookDto bookDto = new BookDto();
        bookDto.setId(book.getId());
        bookDto.setTitle(book.getTitle());
        bookDto.setAuthorDto(authorMapper.toDto(book.getAuthor()));
        return bookDto;
    }

}
