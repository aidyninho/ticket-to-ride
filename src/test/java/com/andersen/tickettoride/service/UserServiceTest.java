package com.andersen.tickettoride.service;

import com.andersen.tickettoride.dto.RouteInputDto;
import com.andersen.tickettoride.dto.RouteOutputDto;
import com.andersen.tickettoride.model.City;
import com.andersen.tickettoride.model.Role;
import com.andersen.tickettoride.model.Route;
import com.andersen.tickettoride.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
class UserServiceTest {

    private static final long ID = 4L;
    @Autowired
    private UserService userService;
    private User user;

    @BeforeEach
    public void setUser() {
        user = User.builder()
                .username("test")
                .password("test")
                .role(Role.TRAVELLER)
                .build();
    }

    @Test
    public void save() {
        userService.save(user);

        User savedUser = userService.findById(ID).orElse(null);

        assertEquals(ID, savedUser.getId());
    }

    @Test
    public void update() {
        User user1 = userService.findById(4L).orElse(null);

        user1.setUsername("asdf");
        user1.setPassword("asdf");
        user1.setRole(Role.ADMIN);
        user1.setBalance(new BigDecimal("2134"));

        userService.update(user1);
    }

    @Test
    public void delete() {
        userService.delete(userService.findById(4L).orElse(null));
    }

    @Test
    public void findById() {

    }
}
