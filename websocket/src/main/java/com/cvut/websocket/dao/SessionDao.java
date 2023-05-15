package com.cvut.websocket.dao;

import dao.BaseDao;
import model.Session;
import org.springframework.stereotype.Repository;

@Repository
public class SessionDao extends BaseDao<Session> {
    public SessionDao() {
        super(Session.class);
    }
}
