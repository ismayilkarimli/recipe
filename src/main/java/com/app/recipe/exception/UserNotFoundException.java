package com.app.recipe.exception;

public class UserNotFoundException extends RuntimeException {

    UserNotFoundException(String message) {
        super(message);
    }
}
