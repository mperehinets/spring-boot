package com.mper.springboot.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class BookDto extends BaseDto {
    private String title;

    private AuthorDto authorDto;
}
