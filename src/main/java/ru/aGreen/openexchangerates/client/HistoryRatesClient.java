package ru.aGreen.openexchangerates.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@FeignClient(name = "data", url = "${history_rate.client.url}")
public interface HistoryRatesClient {
    @RequestMapping(method = RequestMethod.GET)
    ResponseEntity<Map> getHistoryRates();

}
