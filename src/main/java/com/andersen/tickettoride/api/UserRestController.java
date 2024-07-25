package com.andersen.tickettoride.api;

import com.andersen.tickettoride.dto.ExceptionDto;
import com.andersen.tickettoride.dto.UserDto;
import com.andersen.tickettoride.exception.UsernameAlreadyExistsException;
import com.andersen.tickettoride.model.User;
import com.andersen.tickettoride.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UserRestController {

    private final UserService userService;

    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.OK)
    public UserDto save(@RequestBody User user) {
        return userService.save(user);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UsernameAlreadyExistsException.class)
    public ExceptionDto handleUsernameAlreadyExistsException() {
        return ExceptionDto.builder()
                .reason("Username already exists")
                .build();
    }
}
