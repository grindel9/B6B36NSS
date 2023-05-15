package com.cvut.rest.controller;

import java.util.List;

import model.UserAuthority;
import com.cvut.rest.service.UserAuthorityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authority")
@RequiredArgsConstructor
public class UserAuthorityController {

    private final UserAuthorityService userAuthorityService;

    @GetMapping
    public List<UserAuthority> getAuthorities(){
        return userAuthorityService.getUserAuthorities();
    }

    @PostMapping
    public UserAuthority createAuthority(@RequestBody UserAuthority userAuthority) {
        return userAuthorityService.createUserAuthority(userAuthority);
    }
}
