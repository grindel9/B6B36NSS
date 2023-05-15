package com.cvut.rest.controller;

import com.cvut.rest.service.SessionService;
import exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import model.Session;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/session")
@RequiredArgsConstructor
public class SessionController {

    private final SessionService sessionService;

    @GetMapping("/{id}")
    public Session getSession(@PathVariable Integer id) {
        return sessionService.getSession(id);
    }

    @PostMapping("/{id}")
    public Session createSession(@PathVariable Integer id, @RequestBody Session session) throws UserNotFoundException {
        return sessionService.createSession(id, session);
    }
}
