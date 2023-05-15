package com.cvut.rest.service;

import java.util.Objects;

import com.cvut.rest.dao.SessionDao;
import com.cvut.rest.dao.UserDao;
import exception.UserNotFoundException;
import model.BaseService;
import model.Session;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SessionService {

    private final SessionDao sessionDao;
    private final UserDao userDao;

    @Autowired
    public SessionService(SessionDao sessionDao, UserDao userDao) {
        super();
        this.sessionDao = sessionDao;
        this.userDao = userDao;
    }

    public Session getSession(int sessionId) {
        return sessionDao.find(sessionId);
    }

    @Transactional
    public Session createSession(int userId, Session session) throws UserNotFoundException {
        Objects.requireNonNull(session);

        User user = userDao.find(userId);
        if (user == null) {
            throw new UserNotFoundException();
        }

        session.setOwner(user);
        sessionDao.persist(session);

        return session;
    }
}
