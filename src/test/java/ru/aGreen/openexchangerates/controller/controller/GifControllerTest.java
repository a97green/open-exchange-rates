package ru.aGreen.openexchangerates.controller.controller;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import ru.aGreen.openexchangerates.client.GifLessClient;
import ru.aGreen.openexchangerates.client.GifMoreClient;
import static org.springframework.http.HttpStatus.OK;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GifControllerTest {
    @MockBean
    private GifMoreClient moreClient;
    @MockBean
    private GifLessClient lessClient;

    @Test
    public void testGetGifMore() {
        ResponseEntity<String> actual = new ResponseEntity<>(OK);
        Mockito.when(moreClient.getGifMore()).thenReturn(actual);
        ResponseEntity<String> expected = moreClient.getGifMore();
        Assert.assertEquals(expected, actual);
        Assert.assertEquals(expected.getBody(), actual.getBody());
    }

    @Test
    public void testGetGifLess() {
        ResponseEntity<String> actual = new ResponseEntity<>(OK);
        Mockito.when(lessClient.getGifLess()).thenReturn(actual);
        ResponseEntity<String> expected = lessClient.getGifLess();
        Assert.assertEquals(expected, actual);
        Assert.assertEquals(expected.getBody(), actual.getBody());
    }
}
