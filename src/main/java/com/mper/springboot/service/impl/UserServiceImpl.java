package com.mper.springboot.service.impl;

import com.mper.springboot.dao.Status;
import com.mper.springboot.dao.User;
import com.mper.springboot.dto.UserDto;
import com.mper.springboot.exception.UserNotFoundException;
import com.mper.springboot.mapperDto.UserMapper;
import com.mper.springboot.repository.RoleRepository;
import com.mper.springboot.repository.UserRepository;
import com.mper.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDto register(UserDto userDto) {

        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userDto.setStatus(Status.ACTIVE);

        return UserMapper.toDto(userRepository.save(UserMapper.toDao(userDto)));
    }

    @Override
    public UserDto update(UserDto userDto) {
        findById(userDto.getId());
        return UserMapper.toDto(userRepository.save(UserMapper.toDao(userDto)));
    }

    @Override
    public void deleteById(Long id) {
        findById(id);
        userRepository.deleteById(id);
    }

    @Override
    public Collection<UserDto> findAll() {
        Collection<User> users = (Collection<User>) userRepository.findAll();
        return users.stream().map(UserMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public UserDto findById(Long id) {
        return UserMapper.toDto(userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User was not found by id=" + id)));
    }
}
