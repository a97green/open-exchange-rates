package ru.aGreen.openexchangerates.controller.controller;

import junit.framework.TestCase;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import ru.aGreen.openexchangerates.client.*;
import ru.aGreen.openexchangerates.controller.MainController;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.springframework.http.HttpStatus.OK;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MainControllerTest extends TestCase {
    @Autowired
    private MainController mainController;
    @MockBean
    private LatestClient latestClient;
    @MockBean
    private HistoryClient historyClient;
    @MockBean
    private CurrenciesClient currenciesClient;

    @Test
    public void testParseRates() {
        String jsonText = "{\"rates\": {\n" +
                "    \"AED\": 3.6732,\n" +
                "    \"AFN\": 86.840122,\n" +
                "    \"ALL\": 102.574897,\n" +
                "    \"AMD\": 493.191584,\n" +
                "    \"ANG\": 1.793714,\n" +
                "    \"AOA\": 633,\n" +
                "  }}";
        JSONObject expected = mainController.parseRates(jsonText);
        Assert.assertNotNull(expected);
    }

    @Test
    public void testGetTypeCourse() {
        Object testInt = 1;
        Object testDouble = 0.0000376730;
        Object testLong = 2147483647;
        Object testString = "19230.23";

        Assert.assertTrue(mainController.getTypeCourse(testInt) != 0);
        Assert.assertTrue(mainController.getTypeCourse(testDouble) != 0);
        Assert.assertTrue(mainController.getTypeCourse(testLong) != 0);
        Assert.assertTrue(mainController.getTypeCourse(testString) != 0);
    }

    @Test
    public void testGetRateKeys() {
        String jsonText = "{\n" +
                "  \"AED\": \"United Arab Emirates Dirham\",\n" +
                "  \"AFN\": \"Afghan Afghani\",\n" +
                "  \"ALL\": \"Albanian Lek\",\n" +
                "  \"AMD\": \"Armenian Dram\",\n" +
                "  \"ANG\": \"Netherlands Antillean Guilder\",\n" +
                "  \"AOA\": \"Angolan Kwanza\",\n" +
                "  \"ARS\": \"Argentine Peso\",\n" +
                "  \"AUD\": \"Australian Dollar\",\n" +
                "  \"AWG\": \"Aruban Florin\"" +
                "}";
        String[] list = {"AED", "AFN", "ALL", "AMD", "ANG", "AOA", "ARS", "AUD", "AWG"};
        JSONObject jsonDate = new JSONObject();
        try {
            jsonDate = (JSONObject) new JSONParser().parse(jsonText);
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<String> stringList = mainController.getRateKeys(jsonDate);
        Collections.sort(stringList);
        for (int i = 0; i < stringList.size(); i++) {
            assertEquals(stringList.get(i), list[i]);
        }


    }

    @Test
    public void testGetLatestRates() {
        ResponseEntity<String> actual = new ResponseEntity<>(OK);
        Mockito.when(latestClient.getLatestRates()).thenReturn(actual);
        ResponseEntity<String> expected = latestClient.getLatestRates();
        Assert.assertEquals(expected, actual);
        Assert.assertEquals(expected.getBody(), actual.getBody());
    }

    @Test
    public void testGetHistoryRates() {
        ResponseEntity<String> actual = new ResponseEntity<>(OK);
        Mockito.when(historyClient.getHistoryRates()).thenReturn(actual);
        ResponseEntity<String> expected = historyClient.getHistoryRates();
        Assert.assertEquals(expected, actual);
        Assert.assertEquals(expected.getBody(), actual.getBody());
    }

    @Test
    public void testGetCurrenciesRates() {
        ResponseEntity<String> actual = new ResponseEntity<>(OK);
        Mockito.when(currenciesClient.getCurrenciesRates()).thenReturn(actual);
        ResponseEntity<String> expected = currenciesClient.getCurrenciesRates();
        Assert.assertEquals(expected, actual);
        Assert.assertEquals(expected.getBody(), actual.getBody());
    }
}
