package com.mper.springboot.dto;

import com.mper.springboot.dao.Role;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

@Data
@EqualsAndHashCode
public class UserDto extends BaseDto {

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private Set<Role> roles;

}
