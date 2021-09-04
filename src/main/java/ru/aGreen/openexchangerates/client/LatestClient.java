package ru.aGreen.openexchangerates.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import ru.aGreen.openexchangerates.client.configuration.LatestConfiguration;

@FeignClient(name = "latest", url = "Not", configuration = LatestConfiguration.class)
public interface LatestClient {
    @GetMapping
    ResponseEntity<String> getLatestRates();

}
