package com.mper.springboot.service;

import com.mper.springboot.dto.AuthorDto;

import java.util.List;

public interface AuthorService {
    List<AuthorDto> findAll();

    AuthorDto findById(Long id);

    AuthorDto create(AuthorDto authorDto);

    AuthorDto update(AuthorDto authorDto, Long id);

    void delete(Long id);
}
