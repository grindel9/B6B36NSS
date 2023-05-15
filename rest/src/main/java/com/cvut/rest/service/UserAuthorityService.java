package com.cvut.rest.service;

import java.util.List;
import java.util.Objects;

import model.UserAuthority;
import com.cvut.rest.dao.UserAuthorityDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserAuthorityService {

    private final UserAuthorityDao userAuthorityDao;

    @Autowired
    public UserAuthorityService(UserAuthorityDao userAuthorityDao) {
        this.userAuthorityDao = userAuthorityDao;
    }

    public List<UserAuthority> getUserAuthorities(){
        return userAuthorityDao.findAll();
    }

    @Transactional
    public UserAuthority createUserAuthority(UserAuthority userAuthority) {
        Objects.requireNonNull(userAuthority);
        Objects.requireNonNull(userAuthority.getName());
        userAuthorityDao.persist(userAuthority);
        return userAuthority;
    }
}
