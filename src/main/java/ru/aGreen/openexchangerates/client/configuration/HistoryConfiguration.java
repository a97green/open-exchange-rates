package ru.aGreen.openexchangerates.client.configuration;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HistoryConfiguration {
    @Autowired
    private RatesProperties properties;

    @Bean
    public RequestInterceptor requestInterceptor() {
        return template -> template.insert(0, properties.getHistoryUrl());
    }
//
//    public void setLatestUrl(String endpointHistory, String base, String symbols) {
//        properties.setBase(base);
//        properties.setSymbol(symbols);
//        properties.setEndpointHistory(endpointHistory);
//    }

}
