package com.mper.springboot.service;

import com.mper.springboot.dto.UserDto;

import java.util.Collection;

public interface UserService {

    UserDto createUser(UserDto userDto);

    UserDto updateUser(UserDto userDto);

    UserDto findUserById(Long userId);

    Collection<UserDto> findAllUsers();

    void deleteUserById(Long userId);

}
