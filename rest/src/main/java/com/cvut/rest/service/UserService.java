package com.cvut.rest.service;


import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.cvut.rest.dao.UserDao;
import com.cvut.rest.dao.UserAuthorityDao;
import exception.UserNameAlreadyExistException;
import exception.UserNotFoundException;
import model.User;
import model.UserAuthority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserDao userDao;

    private final UserAuthorityDao userAuthorityDao;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserDao userDao, UserAuthorityDao userAuthorityDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.userAuthorityDao = userAuthorityDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public User getUser(int userId) {
        return userDao.find(userId);
    }

    @Transactional
    public User createUser(User user) throws UserNameAlreadyExistException {
        Objects.requireNonNull(user);
        Objects.requireNonNull(user.getUsername());
        Objects.requireNonNull(user.getPassword());

        if (userDao.findByUsername(user.getUsername()).isPresent()) {
            throw new UserNameAlreadyExistException();
        }

        user.encodePassword(passwordEncoder);
        userDao.persist(user);

        return user;
    }

    @Transactional
    public User addAuthorities(int userId, List<String> authorities) throws UserNotFoundException {
        User user = userDao.find(userId);
        if (user == null) {
            throw new UserNotFoundException();
        }
        Set<UserAuthority> userAuthorities = authorities.stream()
                .map(userAuthorityDao::findByName)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toSet());

        userAuthorities.addAll(user.getUserAuthorities());
        user.setUserAuthorities(userAuthorities);
        userDao.persist(user);

        return user;
    }
}
