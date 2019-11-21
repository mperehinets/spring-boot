package com.mper.springboot.service.impl;

import com.mper.springboot.dao.User;
import com.mper.springboot.dto.UserDto;
import com.mper.springboot.exception.UserNotFoundException;
import com.mper.springboot.mapperDto.UserMapper;
import com.mper.springboot.repository.UserRepository;
import com.mper.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        return UserMapper.toDto(userRepository.save(UserMapper.toDao(userDto)));
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        return UserMapper.toDto(userRepository.save(UserMapper.toDao(userDto)));
    }

    @Override
    public UserDto findUserById(Long userId) {
        return UserMapper.toDto(userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User was not found by id=" + userId)));
    }

    @Override
    public Collection<UserDto> findAllUsers() {
        Collection<User> users = (Collection<User>) userRepository.findAll();
        return users.stream().map(UserMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public void deleteUserById(Long userId) {
        findUserById(userId);
        userRepository.deleteById(userId);
    }
}
