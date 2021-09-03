package ru.aGreen.openexchangerates.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.aGreen.openexchangerates.client.configuration.CurrenciesConfiguration;

@FeignClient(name = "data", url = "NOT_USED", configuration = CurrenciesConfiguration.class)
public interface CurrenciesClient {
    @RequestMapping(method = RequestMethod.GET)
    ResponseEntity<String> getCurrenciesRates();

}
