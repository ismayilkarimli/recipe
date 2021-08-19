package com.app.recipe.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "category and name are mutually exclusive parameters")
public class IllegalRecipeParameterException extends RuntimeException {

    public IllegalRecipeParameterException(String message) {
        super(message);
    }
}
