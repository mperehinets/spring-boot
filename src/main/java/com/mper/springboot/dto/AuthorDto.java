package com.mper.springboot.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class AuthorDto extends UserDto {
    private Long id;

    private String education;
}
