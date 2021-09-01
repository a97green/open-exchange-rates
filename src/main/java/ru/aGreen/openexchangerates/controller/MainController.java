package ru.aGreen.openexchangerates.controller;

import ru.aGreen.openexchangerates.client.ExchangeRatesClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.aGreen.openexchangerates.client.HistoryRatesClient;

import java.util.Map;
import java.util.Objects;

@RestController
public class MainController {

    private final ExchangeRatesClient exchangeRatesClient;
    private final HistoryRatesClient historyRatesClient;

    @Autowired
    public MainController(ExchangeRatesClient exchangeRatesClient, HistoryRatesClient historyRatesClient) {
        this.exchangeRatesClient = exchangeRatesClient;
        this.historyRatesClient = historyRatesClient;
    }

    @GetMapping(path = "/")
    ResponseEntity<Map> getRates() {
        ResponseEntity<Map> entity = ResponseEntity.ok(Objects.requireNonNull(exchangeRatesClient.getRates().getBody()));
        System.out.println(entity.toString());
        return entity;
    }

    @GetMapping(path = "/history") //изменить на Post когда добавлю кнопку на странице
    ResponseEntity<Map> getHistoryRates() {
        ResponseEntity<Map> entity = ResponseEntity.ok(Objects.requireNonNull(historyRatesClient.getHistoryRates().getBody()));
        System.out.println(entity.toString());
        return entity;
    }

}