package com.vmoska.mybookstore.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.ALREADY_REPORTED)
public class CustomerEmailAlreadyExistsException extends RuntimeException {
    public CustomerEmailAlreadyExistsException(String message) {
        super(message);

    }
}
