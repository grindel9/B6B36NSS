package com.cvut.rest.dao;

import dao.BaseDao;
import model.Session;
import org.springframework.stereotype.Repository;

@Repository
public class SessionDao extends BaseDao<Session> {
    protected SessionDao() {
        super(Session.class);
    }
}
