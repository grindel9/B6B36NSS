package com.example.auth.controller;

import com.example.auth.service.JwtService;
import com.example.auth.model.AuthenticationRequest;
import com.example.auth.model.AuthenticationResponse;
import com.example.auth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/token")
@RequiredArgsConstructor
public class TokenController {
    private final UserService userService;
    private final JwtService jwtService;

    @PostMapping
    public AuthenticationResponse createToken(@RequestBody AuthenticationRequest authenticationRequest) {
        UserDetails userDetails = userService.login(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        String token = jwtService.createToken(userDetails);
        return new AuthenticationResponse(token);
    }
}
