package com.andersen.tickettoride.service;

import com.andersen.tickettoride.dto.UserDto;
import com.andersen.tickettoride.exception.UsernameAlreadyExistsException;
import com.andersen.tickettoride.model.User;
import com.andersen.tickettoride.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
@Slf4j
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    private static org.springframework.security.core.userdetails.User getUserDetailsUserFromModelUser(User user) {
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                Collections.singleton(user.getRole())
        );
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).map(
                UserService::getUserDetailsUserFromModelUser
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
        if (findByUsername(user.getUsername()).isPresent()) {
            throw new UsernameAlreadyExistsException();
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        log.info("User " + user.getUsername() + " was saved.");

        return UserDto.builder()
                .username(user.getUsername())
                .balance(user.getBalance())
                .build();
    }

    @Transactional
    public UserDto update(User user) {
        userRepository.save(user);

        log.info("User " + user.getUsername() + " was updated.");

        return UserDto.builder()
                .username(user.getUsername())
                .balance(user.getBalance())
                .build();
    }

    @Transactional
    public void delete(User user) {
        log.info("User " + user.getUsername() + " was deleted.");

        userRepository.delete(user);
    }
}
