package ru.aGreen.openexchangerates.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import ru.aGreen.openexchangerates.client.configuration.GifMoreConfiguration;

@FeignClient(name = "gif-more", url = "Not", configuration = GifMoreConfiguration.class)
public interface GifMoreClient {
    @GetMapping
    ResponseEntity<String> getGifMore();

}