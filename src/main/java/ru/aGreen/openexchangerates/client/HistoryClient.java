package ru.aGreen.openexchangerates.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.aGreen.openexchangerates.client.configuration.HistoryConfiguration;

@FeignClient(name = "date", url = "NOT_USED", configuration = HistoryConfiguration.class)
public interface HistoryClient {
    @RequestMapping(method = RequestMethod.GET)
    ResponseEntity<String> getHistoryRates();

}
