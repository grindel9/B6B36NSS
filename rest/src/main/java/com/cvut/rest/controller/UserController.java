package com.cvut.rest.controller;

import java.util.List;

import exception.UserNameAlreadyExistException;
import com.cvut.rest.service.UserService;
import exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public User getUser(@PathVariable Integer id) {
        return userService.getUser(id);
    }

    @PostMapping
    public User createUser(@RequestBody User user) throws UserNameAlreadyExistException {
        return userService.createUser(user);
    }

    @PutMapping("/{id}")
    public User addAuthority(@PathVariable Integer id, @RequestBody List<String> authorities) throws UserNotFoundException {
        return userService.addAuthorities(id, authorities);
    }
}
