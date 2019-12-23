package com.mper.springboot.service.impl;

import com.mper.springboot.dao.Author;
import com.mper.springboot.dao.Role;
import com.mper.springboot.dto.AuthorDto;
import com.mper.springboot.exception.AuthorNotFoundException;
import com.mper.springboot.mapperDto.AuthorMapper;
import com.mper.springboot.repository.AuthorRepository;
import com.mper.springboot.repository.RoleRepository;
import com.mper.springboot.repository.UserRepository;
import com.mper.springboot.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {
    private AuthorRepository authorRepository;
    private RoleRepository roleRepository;
    private UserRepository userRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository, RoleRepository roleRepository, UserRepository userRepository) {
        this.authorRepository = authorRepository;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    @Override
    public AuthorDto create(AuthorDto authorDto) {
        Role roleUser = roleRepository.findByName("ROLE_AUTHOR");
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(roleUser);

        authorDto.setRoles(userRoles);

        return AuthorMapper.toDto(authorRepository.save(AuthorMapper.toDao(authorDto)));
    }

    @Override
    public AuthorDto update(AuthorDto authorDto) {
        findById(authorDto.getId());
        return AuthorMapper.toDto(authorRepository.save(AuthorMapper.toDao(authorDto)));
    }

    @Override
    public Collection<AuthorDto> findAll() {
        Collection<Author> authors = (Collection<Author>) authorRepository.findAll();
        return authors.stream().map(AuthorMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        findById(id);
        userRepository.deleteById(id);
    }

    @Override
    public AuthorDto findById(Long id) {
        return AuthorMapper.toDto(authorRepository.findById(id)
                .orElseThrow(() -> new AuthorNotFoundException("Author was not fount by id = " + id)));
    }
}
