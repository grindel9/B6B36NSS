package model;

import org.springframework.web.reactive.function.client.WebClient;

public abstract class BaseService {
    protected final WebClient webClient;

    public BaseService(String baseUrl, String passwordKey, String passwordValue) {
        webClient = WebClient.builder()
                .baseUrl(baseUrl)
                .defaultHeaders(httpHeaders -> httpHeaders.add(passwordKey, passwordValue))
                .build();
    }
}
