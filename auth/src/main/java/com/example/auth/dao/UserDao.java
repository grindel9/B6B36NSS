package com.example.auth.dao;

import java.util.Objects;
import java.util.Optional;

import dao.BaseDao;
import model.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao extends BaseDao<User> {
    protected UserDao() {
        super(User.class);
    }

    public Optional<User> findByUsername(String username) {
        Objects.requireNonNull(username);
        return em.createNamedQuery("User.findByUsername",
                        User.class)
                .setParameter("username", username)
                .getResultList()
                .stream()
                .findFirst();
    }
}