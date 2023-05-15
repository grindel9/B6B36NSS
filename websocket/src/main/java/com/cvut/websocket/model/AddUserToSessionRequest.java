package com.cvut.websocket.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AddUserToSessionRequest {
    private final int userId;
    private final int sessionId;
}
