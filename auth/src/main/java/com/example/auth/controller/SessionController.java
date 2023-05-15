package com.example.auth.controller;

import com.example.auth.service.SessionService;
import com.example.auth.service.UserService;
import lombok.RequiredArgsConstructor;
import model.Session;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/session")
@RequiredArgsConstructor
public class SessionController {

    private final SessionService sessionService;

    @GetMapping("/{id}")
    public Mono<Session> getSession(@PathVariable Integer id) {
        return sessionService.getSession(id);
    }

    @PostMapping("/{id}")
    public Mono<Session> createSession(@PathVariable Integer id, @RequestBody Session session) {
        return sessionService.createSession(id, session);
    }
}
