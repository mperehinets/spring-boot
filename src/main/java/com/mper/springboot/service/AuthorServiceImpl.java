package com.mper.springboot.service;

import com.mper.springboot.dao.Author;
import com.mper.springboot.dto.AuthorDto;
import com.mper.springboot.exception.AuthorIdMismatchException;
import com.mper.springboot.exception.AuthorNotFoundException;
import com.mper.springboot.mapperDto.AuthorMapper;
import com.mper.springboot.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {

    private AuthorRepository authorRepository;

    private AuthorMapper authorMapper;

    @Autowired
    AuthorServiceImpl(AuthorRepository authorRepository, AuthorMapper authorMapper) {
        this.authorRepository = authorRepository;
        this.authorMapper = authorMapper;
    }

    @Override
    public List<AuthorDto> findAll() {
        List<Author> authors = (List<Author>) authorRepository.findAll();
        return authors.stream().map(authorMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public AuthorDto findById(Long id) {
        return authorMapper.toDto(authorRepository.findById(id).orElseThrow(() -> new AuthorNotFoundException(id)));
    }

    @Override
    public AuthorDto create(AuthorDto authorDto) {
        return authorMapper.toDto(authorRepository.save(authorMapper.toDao(authorDto)));
    }

    @Override
    public AuthorDto update(AuthorDto authorDto, Long id) {
        if (authorDto.getId() != id)
            throw new AuthorIdMismatchException(authorDto, id);
        findById(id);
        return authorMapper.toDto(authorRepository.save(authorMapper.toDao(authorDto)));
    }

    @Override
    public void delete(Long id) {
        findById(id);
        authorRepository.deleteById(id);
    }
}
