package com.mper.springboot.dto;

import com.mper.springboot.dao.Author;

import java.util.Objects;

public class BookDto {

    private Long id;

    private String title;

    private AuthorDto authorDto;

    public BookDto(Long id, String title, AuthorDto authorDto) {
        this.id = id;
        this.title = title;
        this.authorDto = authorDto;
    }


    public BookDto() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public AuthorDto getAuthorDto() {
        return authorDto;
    }

    public void setAuthorDto(AuthorDto authorDto) {
        this.authorDto = authorDto;
    }

    @Override
    public String toString() {
        return "BookDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", authorDto=" + authorDto +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookDto bookDto = (BookDto) o;
        return Objects.equals(id, bookDto.id) &&
                Objects.equals(title, bookDto.title) &&
                Objects.equals(authorDto, bookDto.authorDto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, authorDto);
    }
}
