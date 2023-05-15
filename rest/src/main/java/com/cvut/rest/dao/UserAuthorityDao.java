package com.cvut.rest.dao;

import java.util.Objects;
import java.util.Optional;

import dao.BaseDao;
import model.UserAuthority;
import org.springframework.stereotype.Repository;

@Repository
public class UserAuthorityDao extends BaseDao<UserAuthority> {
    protected UserAuthorityDao() {
        super(UserAuthority.class);
    }

    public Optional<UserAuthority> findByName(String name) {
        Objects.requireNonNull(name);
        return em.createNamedQuery("UserAuthority.findByName",
                        UserAuthority.class)
                .setParameter("name", name)
                .getResultList()
                .stream()
                .findFirst();
    }
}
