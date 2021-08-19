package com.app.recipe.service;

import com.app.recipe.bean.User;
import com.app.recipe.exception.IllegalUserCredentialException;
import com.app.recipe.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationService(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public void register(User user) {
        Optional<User> optionalUser = repository.findUserByEmailIgnoreCase(user.getEmail());
        if (optionalUser.isPresent()) throw new IllegalUserCredentialException("user with this email or password already exists");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        repository.save(user);
    }

    public User findUserByEmail(String email) {
        Optional<User> optionalUser = repository.findUserByEmailIgnoreCase(email);
        return optionalUser.orElseThrow(() -> new IllegalUserCredentialException("user with this email does not exist"));
    }
}
