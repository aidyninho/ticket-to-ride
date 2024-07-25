package com.andersen.tickettoride.service;

import com.andersen.tickettoride.dto.UserDto;
import com.andersen.tickettoride.model.User;
import com.andersen.tickettoride.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).map(
                user -> new org.springframework.security.core.userdetails.User(
                        user.getUsername(),
                        user.getPassword(),
                        Collections.singleton(user.getRole())
                )
        ).orElseThrow(() -> new UsernameNotFoundException("Failed to retrieve user " + username));
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Transactional
    public UserDto save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return UserDto.builder()
                .username(user.getUsername())
                .balance(user.getBalance())
                .build();
    }

    @Transactional
    public UserDto update(User user) {
        return save(user);
    }

    @Transactional
    public void delete(User user) {
        userRepository.delete(user);
    }
}
