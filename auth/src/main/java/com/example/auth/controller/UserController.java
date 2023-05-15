package com.example.auth.controller;

import java.util.List;

import com.example.auth.service.UserService;
import exception.UserNameAlreadyExistException;
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
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public Mono<User> getUser(@PathVariable Integer id) {
        return userService.getUser(id);
    }

    @PostMapping
    public Mono<User> createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PutMapping("/{id}")
    public Mono<User> addAuthority(@PathVariable Integer id, @RequestBody List<String> authorities) throws UserNotFoundException {
        return userService.addAuthorities(id, authorities);
    }
}
