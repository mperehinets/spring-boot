package com.mper.springboot.mapperDto;

import com.mper.springboot.dao.Author;
import com.mper.springboot.dto.AuthorDto;

public class AuthorMapper {

    public static Author toDao(AuthorDto authorDto) {
        Author author = new Author();
        author.setId(authorDto.getId());
        author.setEducation(authorDto.getEducation());
        author.setUser(UserMapper.toDao(authorDto.getUser()));
        return author;
    }

    public static AuthorDto toDto(Author author) {
        AuthorDto authorDto = new AuthorDto();
        authorDto.setId(author.getId());
        authorDto.setEducation(author.getEducation());
        authorDto.setUser(UserMapper.toDto(author.getUser()));
        return authorDto;
    }

}
