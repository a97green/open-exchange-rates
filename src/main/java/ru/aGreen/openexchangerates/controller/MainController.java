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
        String currenciesResponse = currenciesClient.getCurrenciesRates().getBody();
        JSONObject jsonDate = new JSONObject();
        try {
            jsonDate = (JSONObject) new JSONParser().parse(currenciesResponse);
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<String> rateKeys = getRateKeys(jsonDate);
        List<String> rateValues = getRateValues(rateKeys, jsonDate);

        model.addAttribute("rate_keys", rateKeys);
        model.addAttribute("rate_values", rateValues);
        model.addAttribute("title", "Курс обмена валют");
        return "index";
    }
    public List<String> getRateKeys(JSONObject jsonDate) {
        List<String> keys = new ArrayList<>();
        for (Object key : jsonDate.keySet()) {
            keys.add(key.toString());
        }
        Collections.sort(keys);
        return keys;
    }
    public List<String> getRateValues(List<String> rateKeys, JSONObject jsonDate) {
        List<String> values = new ArrayList<>();
        for (String value : rateKeys) {
            values.add(jsonDate.get(value).toString());
        }
        return values;
    }

    @PostMapping(path = "/")
    public String getRatesOther(@RequestParam String baseSelect,
                                @RequestParam String rateSelect,
                                @RequestParam String rateDate,
                                Model model) {
        clientsProperties.setEndpointHistory(rateDate);

        String latestRatesResponse = latestClient.getLatestRates().getBody();
        JSONObject latestRate = (JSONObject) parseRates(latestRatesResponse).get("rates");
        double courseLatest = courseCalc(getTypeCourse(latestRate.get(rateSelect)), getTypeCourse(latestRate.get(baseSelect)));

        String historyRatesResponse = historyClient.getHistoryRates().getBody();
        JSONObject historyRate = (JSONObject) parseRates(historyRatesResponse).get("rates");
        double courseHistory = courseCalc(getTypeCourse(historyRate.get(rateSelect)), getTypeCourse(historyRate.get(baseSelect)));

        System.out.println("Курс " + baseSelect + " на сегодня: " + courseLatest + " " + rateSelect);
        System.out.println("Исторический курс " + baseSelect + " на " + rateDate + ": " + courseHistory + " " + rateSelect);

        return compareCourses(courseLatest, courseHistory);
    }
    /*Метод получает строку ответа сервиса курсов валют. Строка в формате JSON.
    Парсится в JSONObject и возвращается*/
    public JSONObject parseRates(String ratesResponse) {
        try {
            return (JSONObject) new JSONParser().parse(ratesResponse);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /*Метод получает обект значения курса, преобразуется в double и возврашается*/
    public double getTypeCourse(Object object) {
        if (object instanceof Long) {
            return (long) object;
        }
        else if (object instanceof Double) {
            return (double) object;
        }
        return (double) object;
    }
    /*Метод получает значения курсов валют: базовой и обменной,
    и переводит значение обменной относительно базовой валюты(1-AED = 32-DFE)
    Делается потому что в используемом сервисе(openexhcnerates) на бесплатном тарифе возможно выбрать в качестве базовой валюты
    только USD*/
    public double courseCalc(double courseRate, double courseBase) {
        double costUnit = (1 / courseBase);
        return (costUnit * courseRate);
    }
    /*Сравнивает полученные значения курсов и в зависимости от результата возвращет страницу с падением курса,*/
    public String compareCourses(double courseLatest, double courseHistory) {
        if (courseLatest > courseHistory) {
            return "redirect:/more";
        }
        else if (courseLatest < courseHistory) {
            return "redirect:/less";
        }
        return "redirect:/";
    }
}
