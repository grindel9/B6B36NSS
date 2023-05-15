package com.example.auth.service;

import java.util.Objects;

import model.BaseService;
import model.UserAuthority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserAuthorityService extends BaseService {


    @Autowired
    public UserAuthorityService(@Value("${rest.base-url}") String baseUrl,
                          @Value("${rest.header-password-key}") String passwordKey, @Value("${rest.header-password-value}") String passwordValue) {
        super(baseUrl, passwordKey, passwordValue);
    }

    public Flux<UserAuthority> getUserAuthorities(){
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("authority")
                        .build())
                .retrieve()
                .bodyToFlux(UserAuthority.class);
    }

    public Mono<UserAuthority> createUserAuthority(UserAuthority userAuthority) {
        return webClient.post()
                .uri(uriBuilder -> uriBuilder
                        .path("authority")
                        .build())
                .bodyValue(userAuthority)
                .retrieve()
                .bodyToMono(UserAuthority.class);
    }
}
