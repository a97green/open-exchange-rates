package ru.aGreen.openexchangerates.client.configuration;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CurrenciesConfiguration {
    @Autowired
    private RatesProperties properties;

    @Bean
    public RequestInterceptor requestInterceptor() {
        return template -> template.insert(0, properties.getCurrenciesUrl());
    }
}
