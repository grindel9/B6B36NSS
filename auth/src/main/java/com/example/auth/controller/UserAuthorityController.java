package com.example.auth.controller;

import com.example.auth.service.UserAuthorityService;
import lombok.RequiredArgsConstructor;
import model.UserAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/authority")
@RequiredArgsConstructor
public class UserAuthorityController {

    private final UserAuthorityService userAuthorityService;

    @GetMapping
    public Flux<UserAuthority> getAuthorities(){
        return userAuthorityService.getUserAuthorities();
    }

    @PostMapping
    public Mono<UserAuthority> createAuthority(@RequestBody UserAuthority userAuthority) {
        return userAuthorityService.createUserAuthority(userAuthority);
    }
}
