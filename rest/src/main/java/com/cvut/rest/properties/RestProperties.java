package com.cvut.rest.properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import properties.SecurityProperties;

@Component
public class RestProperties extends SecurityProperties {

    @Autowired
    public RestProperties(@Value("${header-password-key}") String passwordKey, @Value("${header-password-value}") String passwordValue) {
        RestProperties.passwordKey = passwordKey;
        RestProperties.passwordValue = passwordValue;
    }
}