package ru.aGreen.openexchangerates.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import ru.aGreen.openexchangerates.client.configuration.HistoryConfiguration;

@FeignClient(name = "history", url = "Not", configuration = HistoryConfiguration.class)
public interface HistoryClient {
    @GetMapping
    ResponseEntity<String> getHistoryRates();

}
