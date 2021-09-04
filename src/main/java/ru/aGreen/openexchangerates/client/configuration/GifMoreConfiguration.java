package ru.aGreen.openexchangerates.client.configuration;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import ru.aGreen.openexchangerates.client.properties.ClientsProperties;

public class GifMoreConfiguration {
    @Autowired
    private ClientsProperties properties;

    @Bean
    public RequestInterceptor requestInterceptor() {
        return template -> template.insert(0, properties.getMoreUrl());
    }
}