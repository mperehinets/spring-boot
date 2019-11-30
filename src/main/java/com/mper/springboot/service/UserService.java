package com.mper.springboot.service;

import com.mper.springboot.dto.UserDto;

import java.util.Collection;

public interface UserService {

    UserDto register(UserDto userDto);

    UserDto update(UserDto userDto);

    void deleteById(Long id);

    Collection<UserDto> findAll();

    UserDto findById(Long id);

}
