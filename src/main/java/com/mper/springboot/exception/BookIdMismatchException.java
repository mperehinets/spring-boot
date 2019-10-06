package com.mper.springboot.exception;

import com.mper.springboot.dao.Book;
import com.mper.springboot.dto.BookDto;

public class BookIdMismatchException extends RuntimeException {

    public BookIdMismatchException(BookDto bookDto, Long bookIdFromPath) {
        super(String.format("Book id from entity ('%s') not equal book id from path ('%s')."
                , bookDto.getId(), bookIdFromPath));
    }

}
