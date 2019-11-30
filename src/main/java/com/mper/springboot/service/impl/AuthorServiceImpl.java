package com.mper.springboot.service.impl;

import com.mper.springboot.dao.Author;
import com.mper.springboot.dao.Role;
import com.mper.springboot.dao.User;
import com.mper.springboot.dto.AuthorDto;
import com.mper.springboot.exception.AuthorNotFoundException;
import com.mper.springboot.exception.UserNotFoundException;
import com.mper.springboot.mapperDto.AuthorMapper;
import com.mper.springboot.repository.AuthorRepository;
import com.mper.springboot.repository.RoleRepository;
import com.mper.springboot.repository.UserRepository;
import com.mper.springboot.service.AuthorService;
import com.mper.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {
    private AuthorRepository authorRepository;
    private UserService userService;
    private RoleRepository roleRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository, UserService userService, RoleRepository roleRepository) {
        this.authorRepository = authorRepository;
        this.userService = userService;
        this.roleRepository = roleRepository;
    }

    @Override
    public AuthorDto create(AuthorDto authorDto) {
        Role roleUser = roleRepository.findByName("ROLE_TEACHER");
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(roleUser);

        authorDto.getUser().setRoles(userRoles);

        authorDto.setUser(userService.register(authorDto.getUser()));

        return AuthorMapper.toDto(authorRepository.save(AuthorMapper.toDao(authorDto)));
    }

    @Override
    public AuthorDto update(AuthorDto authorDto) {
        findById(authorDto.getId());

        userService.update(authorDto.getUser());

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
        userService.deleteById(authorRepository.findUserIdByAuthorId(id));
    }

    @Override
    public AuthorDto findById(Long id) {
        return AuthorMapper.toDto(authorRepository.findById(id)
                .orElseThrow(() -> new AuthorNotFoundException("Author was not fount by id = " + id)));
    }
}
