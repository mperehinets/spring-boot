package com.mper.springboot.service.impl;

import com.mper.springboot.dao.Author;
import com.mper.springboot.dao.Role;
import com.mper.springboot.dao.User;
import com.mper.springboot.dto.AuthorDto;
import com.mper.springboot.exception.UserNotFoundException;
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
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository, UserRepository userRepository, RoleRepository roleRepository) {
        this.authorRepository = authorRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public AuthorDto create(AuthorDto authorDto) {
        Author author = AuthorMapper.toDao(authorDto);

        Role roleUser = roleRepository.findByName("ROLE_TEACHER");
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(roleUser);

        author.getUser().setRoles(userRoles);

        author.setUser(userRepository.save(author.getUser()));

        return AuthorMapper.toDto(authorRepository.save(author));
    }

    @Override
    public AuthorDto update(AuthorDto authorDto) {
        Author author = AuthorMapper.toDao(authorDto);

        author.getUser().setId(authorRepository.findUserIdByAuthorId(author.getId()));

        author.setUser(userRepository.save(author.getUser()));

        return AuthorMapper.toDto(authorRepository.save(author));
    }

    @Override
    public Collection<AuthorDto> findAll() {
        Collection<Author> authors = (Collection<Author>) authorRepository.findAll();
        return authors.stream().map(AuthorMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        findById(id);
        userRepository.deleteById(authorRepository.findUserIdByAuthorId(id));
    }

    @Override
    public AuthorDto findById(Long id) {
        return AuthorMapper.toDto(authorRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User was nor fount by id = " + id)));
    }
}
