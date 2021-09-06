package ru.aGreen.openexchangerates.controller;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
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
        JSONObject jsonCurrencies = parseRates(currenciesResponse);

        List<String> rateKeys = getRateKeys(jsonCurrencies);
        List<String> rateValues = getRateValues(rateKeys, jsonCurrencies);

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
                                RedirectAttributes redirectAttributes) {
        if (!baseSelect.equals(rateSelect)) {
            clientsProperties.setEndpointHistory(rateDate);

            String responseLatest = latestClient.getLatestRates().getBody();
            JSONObject jsonLatest = (JSONObject) parseRates(responseLatest).get("rates");
            double courseLatest = courseCalc(getTypeCourse(jsonLatest.get(rateSelect)), getTypeCourse(jsonLatest.get(baseSelect)));

            String responseHistory = historyClient.getHistoryRates().getBody();
            JSONObject jsonHistory = (JSONObject) parseRates(responseHistory).get("rates");
            double courseHistory = courseCalc(getTypeCourse(jsonHistory.get(rateSelect)), getTypeCourse(jsonHistory.get(baseSelect)));

            redirectAttributes(redirectAttributes, baseSelect, rateSelect, courseLatest, courseHistory, rateDate);
            return compareCourses(courseLatest, courseHistory);
        }
        return "redirect:/";
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
        else if (object instanceof Integer) {
            return (int) object;
        }
        else if (object instanceof String) {
            try {
                return Double.parseDouble(object.toString());
            }
            catch (NumberFormatException e) {
                return 0;
            }
        }
        return 0;
    }
    /*Метод получает значения курсов валют: базовой и обменной,
    и переводит значение обменной относительно базовой валюты(1-AED = 32-DFE)
    Делается потому что в используемом сервисе(openexhcnerates) на бесплатном тарифе возможно выбрать в качестве базовой валюты
    только USD*/
    public double courseCalc(double courseRate, double courseBase) {
        double costUnit = (1 / courseBase);
        double result = costUnit * courseRate;
        double scale = Math.pow(10, 3);
        result = Math.ceil(result * scale) / scale;
        return result;
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
    /*Отправляет значения в GifController*/
    public void redirectAttributes(RedirectAttributes redirectAttributes, String baseSelect, String rateSelect, double courseLatest, double courseHistory, String rateDate) {
        redirectAttributes.addFlashAttribute("baseSelect", baseSelect);
        redirectAttributes.addFlashAttribute("rateSelect", rateSelect);
        redirectAttributes.addFlashAttribute("courseLatest", courseLatest);
        redirectAttributes.addFlashAttribute("courseHistory", courseHistory);
        redirectAttributes.addFlashAttribute("rateDate", rateDate);
    }
}