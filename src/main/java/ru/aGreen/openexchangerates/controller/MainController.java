package ru.aGreen.openexchangerates.controller;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.aGreen.openexchangerates.client.*;
import ru.aGreen.openexchangerates.client.properties.ClientsProperties;

import java.util.*;
@RequestMapping
@Controller
public class MainController {
    @Autowired
    private ClientsProperties clientsProperties;
    @Autowired
    private CurrenciesClient currenciesClient;
    @Autowired
    private HistoryClient historyClient;
    @Autowired
    private LatestClient latestClient;


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
            model.addAttribute("title", "Курс обмена валют");
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

        JSONParser parser = new JSONParser();

        try {
            JSONObject jsonHistoryRates = (JSONObject) parser.parse(historyRatesResponse);
            JSONObject jsonLatestRates = (JSONObject) parser.parse(latestRatesResponse);

            String base = (String) jsonLatestRates.get("base");
            JSONObject historyRate = (JSONObject) jsonHistoryRates.get("rates");
            JSONObject latestRate = (JSONObject) jsonLatestRates.get("rates");

            double courseHistory = 0;
            double courseLatest = 0;

            for (Object currencyHistory : historyRate.keySet()) {
                for (Object currencyLatest : latestRate.keySet()) {
                    if ((historyRate.get(currencyHistory) instanceof Double) && (latestRate.get(currencyLatest) instanceof Double)) {
                        courseHistory = (Double) historyRate.get(currencyHistory);
                        courseLatest = (Double) latestRate.get(currencyLatest);
                    }
                    else {
                        courseHistory = (Long) historyRate.get(currencyHistory);
                        courseLatest = (Long) latestRate.get(currencyLatest);
                    }
                    if (courseLatest > courseHistory) {
                        return "redirect:/more";
                    }
                    else if (courseLatest <= courseHistory) {
                        return "redirect:/less";
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/";
    }

    public void setUrl(String baseSelect, String rateSelect, String ratesDate) {
        //clientsProperties.setBase("USD");
        clientsProperties.setSymbol(rateSelect);
        clientsProperties.setEndpointHistory(ratesDate);
    }
}
