package com.mper.springboot.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class UserDto extends BaseDto {

    private String firstName;

    private String lastName;

    private String email;

    private String password;

}
