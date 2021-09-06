package ru.aGreen.openexchangerates.controller.client.properties;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.aGreen.openexchangerates.client.properties.ClientsProperties;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClientPropertiesTest {
    @Autowired
    private ClientsProperties clientsProperties;

    @Test
    public void testGetCurrenciesUrl() {
        String actual = "https://openexchangerates.org/api/currencies.json";
        String expected = clientsProperties.getCurrenciesUrl();
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testGetLatestUrl() {
        String actual = "https://openexchangerates.org/api/latest.json?app_id=c3adc7ab6704420e99804954ca051375&base=USD&symbols=";
        String expected = clientsProperties.getLatestUrl();
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testGetHistoryUrl() {
        String actual = "https://openexchangerates.org/api/historical/1999-01-01.json?app_id=c3adc7ab6704420e99804954ca051375&base=USD&symbols=";
        String expected = clientsProperties.getHistoryUrl();
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void  testGetMoreUrl() {
        String actual = "https://api.giphy.com/v1/gifs/random?api_key=DGilNBDTObJK8AbMlgIG9fidA07eToRS&tag=rich&rating=";
        String expected = clientsProperties.getMoreUrl();
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void  testGetLessUrl() {
        String actual = "https://api.giphy.com/v1/gifs/random?api_key=DGilNBDTObJK8AbMlgIG9fidA07eToRS&tag=broke&rating=";
        String expected = clientsProperties.getLessUrl();
        Assert.assertEquals(actual, expected);
    }

}
