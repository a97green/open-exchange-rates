package ru.aGreen.openexchangerates.controller;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.aGreen.openexchangerates.client.CurrenciesClient;
import ru.aGreen.openexchangerates.client.HistoryClient;
import ru.aGreen.openexchangerates.client.LatestClient;
import ru.aGreen.openexchangerates.client.configuration.RatesProperties;


import java.util.*;

@Controller
public class MainController {
    @Autowired
    private RatesProperties ratesProperties;
    @Autowired
    private final CurrenciesClient currenciesClient;
    @Autowired
    private final HistoryClient historyClient;
    @Autowired
    private final LatestClient latestClient;


    public MainController(CurrenciesClient currenciesClient, HistoryClient historyClient, LatestClient latestClient) {
        this.currenciesClient = currenciesClient;
        this.historyClient = historyClient;
        this.latestClient = latestClient;
    }


    @GetMapping(path = "/")
    public String getRates(Model model) {
        String ratesResponse = currenciesClient.getCurrenciesRates().getBody();
        try {
            JSONParser parser = new JSONParser();
            JSONObject jsonCurrencies = (JSONObject) parser.parse(ratesResponse);
            List<String> rateKeys = new ArrayList<>();
            List<String> rateValues = new ArrayList<>();
            for (Object rate : jsonCurrencies.keySet()) {
                rateKeys.add(rate.toString());

            }
            Collections.sort (rateKeys);
            for (String rate : rateKeys) {
                rateValues.add(jsonCurrencies.get(rate).toString());

            }
            model.addAttribute("rate_keys", rateKeys);
            model.addAttribute("rate_values", rateValues);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "index";
    }

    @PostMapping(path = "/")
    public String getRatesOther(@RequestParam String baseSelect,
                                @RequestParam String rateSelect,
                                @RequestParam String ratesDate,
                                Model model) {
        setUrl(baseSelect, rateSelect, ratesDate);

        String historyRatesResponse = historyClient.getHistoryRates().getBody();
        String latestRatesResponse = latestClient.getLatestRates().getBody();

        System.out.println(ratesProperties.getHistoryUrl());
        System.out.println(ratesProperties.getLatestUrl());
        try {
            JSONParser parser = new JSONParser();
            JSONObject jsonHistoryRates = (JSONObject) parser.parse(historyRatesResponse);
            JSONObject jsonLatestRates = (JSONObject) parser.parse(latestRatesResponse);

            String base = (String) jsonLatestRates.get("base");
            JSONObject historyRate = (JSONObject) jsonHistoryRates.get("rates");
            JSONObject latestRate = (JSONObject) jsonLatestRates.get("rates");

            double courseHistory = 0;
            double courseLatest = 0;

            for (Object currencyHistory : historyRate.keySet()) {
                courseHistory = (double) historyRate.get(currencyHistory);
                for (Object currencyLatest : latestRate.keySet()) {
                    courseLatest = (double) latestRate.get(currencyLatest);
                    if (courseLatest > courseHistory) {
//                        model.addAttribute();
                        System.out.println(base + " по отношению к " + rateSelect + " вырос на " + (courseLatest - courseHistory));
                        System.out.println(rateSelect + " на " + ratesDate + " составлял " + courseHistory);
                        System.out.println(rateSelect + " на сегодня составляет " + courseLatest);
                    }
                    else if (courseLatest == courseHistory){
//                        model.addAttribute();
                        System.out.println(base + " по отношению к " + rateSelect + " не изменился");
                    }
                    else {
                        System.out.println(base + " по отношению к " + rateSelect + " снизился на " + (courseHistory - courseLatest));
                        System.out.println(rateSelect + " на " + ratesDate + " составлял " + courseHistory);
                        System.out.println(rateSelect + " на сегодня составляет " + courseLatest);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/";
    }

    public void setUrl(String baseSelect, String rateSelect, String ratesDate) {
        ratesProperties.setBase("USD");
        ratesProperties.setSymbol(rateSelect);
        ratesProperties.setEndpointHistory(ratesDate);
    }
}
