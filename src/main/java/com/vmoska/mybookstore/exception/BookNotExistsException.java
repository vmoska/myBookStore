package com.vmoska.mybookstore.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class BookNotExistsException extends RuntimeException {
    public BookNotExistsException(String message) {
        super(message);
    }
}
