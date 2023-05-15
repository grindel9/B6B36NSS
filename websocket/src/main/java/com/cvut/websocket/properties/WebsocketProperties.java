package com.cvut.websocket.properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import properties.SecurityProperties;

@Component
public class WebsocketProperties extends SecurityProperties {

    @Autowired
    public WebsocketProperties(@Value("${header-password-key}") String passwordKey, @Value("${header-password-value}") String passwordValue) {
        WebsocketProperties.passwordKey = passwordKey;
        WebsocketProperties.passwordValue = passwordValue;
    }
}