package ru.aGreen.openexchangerates.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.aGreen.openexchangerates.client.configuration.LatestConfiguration;

@FeignClient(name = "client", url = "NOT_USED", configuration = LatestConfiguration.class)
public interface LatestClient {
    @RequestMapping(method = RequestMethod.GET)
    ResponseEntity<String> getLatestRates();

}
