package com.example.auth.service;

import model.BaseService;
import model.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class SessionService extends BaseService {

    @Autowired
    public SessionService(@Value("${rest.base-url}") String baseUrl,
                       @Value("${rest.header-password-key}") String passwordKey, @Value("${rest.header-password-value}") String passwordValue) {
        super(baseUrl, passwordKey, passwordValue);
    }

    public Mono<Session> getSession(int userId) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("session/{userId}")
                        .build(userId))
                .retrieve()
                .bodyToMono(Session.class);
    }

    public Mono<Session> createSession(int userId, Session session) {
        return webClient.post()
                .uri(uriBuilder -> uriBuilder
                        .path("session/{userId}")
                        .build(userId))
                .bodyValue(session)
                .retrieve()
                .bodyToMono(Session.class);
    }
}
