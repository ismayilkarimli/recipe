package com.app.recipe.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Illegal email or password")
public class IllegalUserCredentialException extends RuntimeException {

    public IllegalUserCredentialException(String message) {
        super(message);
    }
}
