package com.mper.springboot.mapperDto;

import com.mper.springboot.dao.Author;
import com.mper.springboot.dto.AuthorDto;

public class AuthorMapper {

    public static Author toDao(AuthorDto authorDto) {
        Author author = new Author();
        author.setId(authorDto.getId());
        author.setEducation(authorDto.getEducation());
        author.setFirstName(authorDto.getFirstName());
        author.setLastName(authorDto.getLastName());
        author.setEmail(authorDto.getEmail());
        author.setPassword(authorDto.getPassword());
        author.setStatus(authorDto.getStatus());
        return author;
    }

    public static AuthorDto toDto(Author author) {
        AuthorDto authorDto = new AuthorDto();
        authorDto.setId(author.getId());
        authorDto.setEducation(author.getEducation());
        authorDto.setFirstName(author.getFirstName());
        authorDto.setLastName(author.getLastName());
        authorDto.setEmail(author.getEmail());
        authorDto.setPassword(author.getPassword());
        authorDto.setStatus(author.getStatus());
        return authorDto;
    }

}
