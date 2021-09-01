package ru.aGreen.openexchangerates;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = {"ru.aGreen.openexchangerates.client",
        "ru.aGreen.openexchangerates.controller"})
public class OpenExchangeRatesServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OpenExchangeRatesServiceApplication.class, args);
    }
}
