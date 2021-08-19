package com.app.recipe.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FORBIDDEN, reason = "only the user who added the recipe can modify the recipe")
public class IllegalRequestException extends RuntimeException {

    public IllegalRequestException(String message) {
        super(message);
    }
}
