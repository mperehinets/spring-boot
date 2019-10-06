package com.mper.springboot.service;

import com.mper.springboot.dao.Author;
import com.mper.springboot.dto.AuthorDto;
import com.mper.springboot.exception.AuthorIdMismatchException;
import com.mper.springboot.exception.AuthorNotFoundException;
import com.mper.springboot.mapperDto.AuthorMapper;
import com.mper.springboot.repository.AuthorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;

public class AuthorServiceImplTest {
    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private AuthorServiceImpl authorService;

    private AuthorMapper authorMapper;

    private AuthorDto authorDto;

    private Author author;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
        authorMapper = new AuthorMapper();
        authorDto = new AuthorDto(1L, "Daniel Brawn");
        author = authorMapper.toDao(authorDto);
        authorService = new AuthorServiceImpl(authorRepository, authorMapper);
    }

    @Test
    public void testCreateAuthor() {
        doReturn(author).when(authorRepository).save(author);
        assertThat(authorService.create(authorDto)).isEqualTo(authorDto);
    }

    @Test
    public void testFindAuthorById() {
        doReturn(Optional.of(author)).when(authorRepository).findById(author.getId());
        assertThat(authorService.findById(author.getId())).isEqualTo(authorDto);
    }

    @Test
    public void testFindAuthorById_NonexistentIdGiven_ShouldThrowAuthorNotFoundException() {
        doReturn(Optional.empty()).when(authorRepository).findById(Long.MAX_VALUE);
        assertThrows(AuthorNotFoundException.class, () -> authorService.findById(Long.MAX_VALUE));
    }

    @Test
    public void testFindAllAuthors() {
        doReturn(Collections.singletonList(author)).when(authorRepository).findAll();
        assertThat(authorService.findAll()).isEqualTo(Collections.singletonList(authorDto));
    }

    @Test
    public void testUpdateAuthor() {
        doReturn(author).when(authorRepository).save(author);
        doReturn(Optional.of(author)).when(authorRepository).findById(author.getId());
        assertThat(authorService.update(authorDto, authorDto.getId())).isEqualTo(authorDto);
    }

    @Test
    public void testUpdateAuthor_IdFromPathNotEqualIdFromEntity_ShouldThrowAuthorIdMismatchException() {
        assertThrows(AuthorIdMismatchException.class, () -> authorService.update(authorDto, Long.MAX_VALUE));
    }

    @Test
    public void testUpdateAuthor_NonexistentIdGiven_ShouldThrowAuthorNotFoundException() {
        doReturn(Optional.empty()).when(authorRepository).findById(Long.MAX_VALUE);
        assertThrows(AuthorNotFoundException.class, () -> authorService.update(authorDto, authorDto.getId()));
    }

    @Test
    public void testDeleteAuthorById() {
        doReturn(Optional.of(author)).when(authorRepository).findById(author.getId());
        doNothing().when(authorRepository).deleteById(author.getId());
        authorService.delete(authorDto.getId());
    }

    @Test
    public void testDeleteAuthorById_NonexistentIdGiven_ShouldThrowAuthorNotFoundException(){
        doReturn(Optional.empty()).when(authorRepository).findById(Long.MAX_VALUE);
        assertThrows(AuthorNotFoundException.class, () -> authorService.update(authorDto, authorDto.getId()));
    }
}
