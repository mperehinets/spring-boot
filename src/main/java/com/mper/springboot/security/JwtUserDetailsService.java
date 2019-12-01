package com.mper.springboot.security;

import com.mper.springboot.dao.User;
import com.mper.springboot.mapperDto.UserMapper;
import com.mper.springboot.security.jwt.JwtUserFactory;
import com.mper.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public JwtUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        User user = UserMapper.toDao(userService.findByEmail(email));
        return JwtUserFactory.create(user);
    }
}
