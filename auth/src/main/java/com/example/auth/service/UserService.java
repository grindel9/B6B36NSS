package com.example.auth.service;


import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.example.auth.dao.UserDao;
import model.BaseService;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
public class UserService extends BaseService implements UserDetailsService {

    private final UserDao userDao;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserDao userDao, PasswordEncoder passwordEncoder, @Value("${rest.base-url}") String baseUrl,
                       @Value("${rest.header-password-key}") String passwordKey, @Value("${rest.header-password-value}") String passwordValue) {
        super(baseUrl, passwordKey, passwordValue);
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDao.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username does not exist"));
    }

    @Transactional
    public User login(String username, String password) throws UsernameNotFoundException {
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);
        Optional<User> user = userDao.findByUsername(username);
        if (user.isEmpty()){
            throw new UsernameNotFoundException("Username does not exist");
        }
        if (!passwordEncoder.matches(password, user.get().getPassword())){
            throw new UsernameNotFoundException("Password and encoded password does not match");
        }
        return user.get();
    }

    public Mono<User> getUser(int userId) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("user/{userId}")
                        .build(userId))
                .retrieve()
                .bodyToMono(User.class);
    }

    public Mono<User> createUser(User user) {
        return webClient.post()
                .uri(uriBuilder -> uriBuilder
                        .path("user")
                        .build())
                .bodyValue(user)
                .retrieve()
                .bodyToMono(User.class);
    }

    public Mono<User> addAuthorities(Integer userId, List<String> authorities) {
        return webClient.put()
                .uri(uriBuilder -> uriBuilder
                        .path("user/{userId}")
                        .build(userId))
                .bodyValue(authorities)
                .retrieve()
                .bodyToMono(User.class);
    }
}
