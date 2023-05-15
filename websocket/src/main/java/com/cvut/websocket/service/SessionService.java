package com.cvut.websocket.service;

import java.util.Objects;
import java.util.Set;

import com.cvut.websocket.dao.SessionDao;
import com.cvut.websocket.dao.UserDao;
import com.cvut.websocket.model.AddUserToSessionRequest;
import exception.UserNotFoundException;
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
        this.sessionDao = sessionDao;
        this.userDao = userDao;
    }

    @Transactional
    public Session addUserToSession(AddUserToSessionRequest request) throws UserNotFoundException {
        Objects.requireNonNull(request);

        User user = userDao.find(request.getUserId());
        Session session = sessionDao.find(request.getSessionId());
        if (user == null || session == null) {
            throw new UserNotFoundException();
        }

        Set<User> users = session.getUsers();
        users.add(user);

        session.setUsers(users);
        sessionDao.persist(session);
        return session;
    }
}
