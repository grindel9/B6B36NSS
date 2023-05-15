package com.cvut.websocket.dao;

import dao.BaseDao;
import model.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao extends BaseDao<User> {
    public UserDao() {
        super(User.class);
    }
}
