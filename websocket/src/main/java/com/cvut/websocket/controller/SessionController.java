package com.cvut.websocket.controller;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.cvut.websocket.config.ApplicationContextHolder;
import com.cvut.websocket.model.AddUserToSessionRequest;
import com.cvut.websocket.service.SessionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Session;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Controller
public class SessionController extends TextWebSocketHandler {

    private final List<WebSocketSession> webSocketSessions = new CopyOnWriteArrayList<>();

    /**
     * when we add user to socketSession and update all websockets
     *
     * @param socketSession
     * @param message
     * @throws Exception
     */
    @Override
    public void handleTextMessage(WebSocketSession socketSession, TextMessage message) throws Exception {
        JSONObject jsonObject = new JSONObject(message.getPayload());
        AddUserToSessionRequest request = new AddUserToSessionRequest(jsonObject.getInt("userId"), jsonObject.getInt("sessionId"));
        ObjectMapper mapper = new ObjectMapper();
        SessionService sessionService = ApplicationContextHolder.getContext().getBean(SessionService.class);
        Session session = sessionService.addUserToSession(request);
        String sessionJson = mapper.writeValueAsString(session);

        for (WebSocketSession webSocketSession : webSocketSessions) {
            webSocketSession.sendMessage(new TextMessage(sessionJson));
        }
    }

    /**
     * add new websocket to opened connections list
     *
     * @param socketSession
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession socketSession) {
        webSocketSessions.add(socketSession);
    }

    /**
     * remove websocket from opened connections list
     *
     * @param socketSession
     * @param status
     */
    @Override
    public void afterConnectionClosed(WebSocketSession socketSession, CloseStatus status) {
        webSocketSessions.remove(socketSession);
    }
}