package com.mper.springboot.service;

import com.mper.springboot.dao.Author;
import com.mper.springboot.dto.AuthorDto;

import java.util.Collection;

public interface AuthorService {

    AuthorDto create(AuthorDto authorDto);

    AuthorDto update(AuthorDto authorDto);

    Collection<AuthorDto> findAll();

    void deleteById(Long id);

    AuthorDto findById(Long id);

}
