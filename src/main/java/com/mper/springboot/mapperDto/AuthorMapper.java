package com.mper.springboot.mapperDto;

import com.mper.springboot.dto.AuthorDto;
import com.mper.springboot.dao.Author;
import org.springframework.stereotype.Component;

@Component
public class AuthorMapper {

    public Author toDao(AuthorDto authorDto) {
        Author author = new Author();
        author.setId(authorDto.getId());
        author.setName(authorDto.getName());
        return author;
    }

    public AuthorDto toDto(Author author) {
        AuthorDto authorDto = new AuthorDto();
        authorDto.setId(author.getId());
        authorDto.setName(author.getName());
        return authorDto;
    }

}
