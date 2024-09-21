package com.andersen.tickettoride.service;

import com.andersen.tickettoride.dto.UserDto;
import com.andersen.tickettoride.model.Role;
import com.andersen.tickettoride.model.User;
import com.andersen.tickettoride.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    private final long ID = 1L;
    private final String USERNAME = "user";
    private final String PASSWORD = "aidyninho";
    private final User expectedUser = User.builder()
            .id(ID)
            .username(USERNAME)
            .password(PASSWORD)
            .role(Role.TRAVELLER)
            .balance(BigDecimal.TEN)
            .build();

    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @InjectMocks
    private UserService userService;

    @Test
    void findById_Returns_User() {
        doReturn(Optional.of(expectedUser)).when(userRepository).findById(ID);

        Optional<User> actualUser = userService.findById(ID);

        assertTrue(actualUser.isPresent());
        assertEquals(Optional.of(expectedUser), actualUser);
    }

    @Test
    void findByUsername_Returns_User() {
        doReturn(Optional.of(expectedUser)).when(userRepository).findByUsername(USERNAME);

        Optional<User> actualUser = userService.findByUsername(USERNAME);

        assertTrue(actualUser.isPresent());
        assertEquals(Optional.of(expectedUser), actualUser);
    }

    @Test
    void save_Returns_UserDto() {
        User user = User.builder()
                .username(USERNAME)
                .password(PASSWORD)
                .role(Role.TRAVELLER)
                .balance(BigDecimal.TEN)
                .build();

        UserDto userDto = UserDto.builder()
                .username(USERNAME)
                .balance(BigDecimal.TEN)
                .build();

        doReturn("$2a$10$fcBTNPwEDDJRYAI7PbcYHODh6/R7HNfbFwQ9Z8arXd6qTWqceZk5C")
                .when(passwordEncoder).encode(PASSWORD);
        doReturn(user).when(userRepository).save(user);

        UserDto actualUser = userService.save(user);

        assertEquals(userDto.getUsername(), actualUser.getUsername());
    }
}