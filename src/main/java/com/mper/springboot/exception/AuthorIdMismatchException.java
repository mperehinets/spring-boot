package com.mper.springboot.exception;

import com.mper.springboot.dto.AuthorDto;

public class AuthorIdMismatchException extends RuntimeException {
    public AuthorIdMismatchException(AuthorDto authorDto, Long idFromPath) {
        super(String.format("Author id from entity ('%s') not equal author id from path ('%s')"
                , authorDto.getId(), idFromPath));
    }
}
