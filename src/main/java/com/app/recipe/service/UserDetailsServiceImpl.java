package com.app.recipe.service;

import com.app.recipe.bean.User;
import com.app.recipe.repository.UserRepository;
import com.app.recipe.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findUserByEmailIgnoreCase(email);
        user.orElseThrow(() -> new UsernameNotFoundException(email + " not found"));
        return user.map(UserDetailsImpl::new).get();
    }
}
