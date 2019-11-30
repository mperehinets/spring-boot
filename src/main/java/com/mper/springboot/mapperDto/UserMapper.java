package com.mper.springboot.mapperDto;

import com.mper.springboot.dao.User;
import com.mper.springboot.dto.UserDto;

public class UserMapper {
    public static User toDao(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setStatus(userDto.getStatus());
        user.setRoles(userDto.getRoles());
        return user;
    }

    public static UserDto toDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setStatus(user.getStatus());
        userDto.setRoles(user.getRoles());
        return userDto;
    }
}
