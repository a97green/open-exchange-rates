package ru.aGreen.openexchangerates.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import ru.aGreen.openexchangerates.client.configuration.GifLessConfiguration;

@FeignClient(name = "gif-less", url = "Not", configuration = GifLessConfiguration.class)
public interface GifLessClient {
    @GetMapping
    ResponseEntity<String> getGifLess();

}