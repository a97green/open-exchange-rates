package ru.aGreen.openexchangerates.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import ru.aGreen.openexchangerates.client.configuration.CurrenciesConfiguration;

@FeignClient(name = "currencies", url = "Not", configuration = CurrenciesConfiguration.class)
public interface CurrenciesClient {
    @GetMapping
    ResponseEntity<String> getCurrenciesRates();

}
