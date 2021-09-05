package controller;

import junit.framework.TestCase;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import ru.aGreen.openexchangerates.client.LatestClient;
import ru.aGreen.openexchangerates.controller.MainController;

public class MainControllerTest extends TestCase {
    @Autowired
    private LatestClient latestClient;
    MainController mainController;
    String latestRatesResponse;

    @Override
    protected void setUp() throws Exception {
        mainController = new MainController();
        latestRatesResponse = "{\n" +
                "  \"disclaimer\": \"Usage subject to terms: https://openexchangerates.org/terms\",\n" +
                "  \"license\": \"https://openexchangerates.org/license\",\n" +
                "  \"timestamp\": 1630825210,\n" +
                "  \"base\": \"USD\",\n" +
                "  \"rates\": {\n" +
                "    \"AED\": 3.6732,\n" +
                "    \"AFN\": 86.878888,\n" +
                "    \"ALL\": 102.620677,\n" +
                "    \"AMD\": 493.411507,\n" +
                "    \"ANG\": 1.794514,\n" +
                "    \"AOA\": 633,\n" +
                "    \"ARS\": 97.907089,\n" +
                "    \"AUD\": 1.341151,\n" +
                "    \"AWG\": 1.8005,\n" +
                "    \"AZN\": 1.700805,\n" +
                "    \"BAM\": 1.647313,\n" +
                "    \"BBD\": 2,\n" +
                "    \"BDT\": 85.127411,\n" +
                "    \"BGN\": 1.645682,\n" +
                "    \"BHD\": 0.376909,\n" +
                "    \"BIF\": 1984.621927,\n" +
                "    \"BMD\": 1,\n" +
                "    \"BND\": 1.342643,\n" +
                "    \"BOB\": 6.903145,\n" +
                "    \"BRL\": 5.191226,\n" +
                "    \"BSD\": 1,\n" +
                "    \"BTC\": 0.000020047513,\n" +
                "    \"BTN\": 73.002442,\n" +
                "    \"BWP\": 10.998202,\n" +
                "    \"BYN\": 2.500432,\n" +
                "    \"BZD\": 2.015186,\n" +
                "    \"CAD\": 1.252665,\n" +
                "    \"CDF\": 1985.531875,\n" +
                "    \"CHF\": 0.913662,\n" +
                "    \"CLF\": 0.027828,\n" +
                "    \"CLP\": 767.05,\n" +
                "    \"CNH\": 6.43987,\n" +
                "    \"CNY\": 6.4535,\n" +
                "    \"COP\": 3790.643348,\n" +
                "    \"CRC\": 624.09701,\n" +
                "    \"CUC\": 1,\n" +
                "    \"CUP\": 25.75,\n" +
                "    \"CVE\": 93.053484,\n" +
                "    \"CZK\": 21.36025,\n" +
                "    \"DJF\": 177.977449,\n" +
                "    \"DKK\": 6.259925,\n" +
                "    \"DOP\": 56.824597,\n" +
                "    \"DZD\": 135.666397,\n" +
                "    \"EGP\": 15.704128,\n" +
                "    \"ERN\": 15.004907,\n" +
                "    \"ETB\": 45.712445,\n" +
                "    \"EUR\": 0.841503,\n" +
                "    \"FJD\": 2.0773,\n" +
                "    \"FKP\": 0.721241,\n" +
                "    \"GBP\": 0.721241,\n" +
                "    \"GEL\": 3.103646,\n" +
                "    \"GGP\": 0.721241,\n" +
                "    \"GHS\": 6.05836,\n" +
                "    \"GIP\": 0.721241,\n" +
                "    \"GMD\": 51.18,\n" +
                "    \"GNF\": 9785.873473,\n" +
                "    \"GTQ\": 7.739422,\n" +
                "    \"GYD\": 209.157834,\n" +
                "    \"HKD\": 7.77165,\n" +
                "    \"HNL\": 23.958022,\n" +
                "    \"HRK\": 6.3133,\n" +
                "    \"HTG\": 98.128687,\n" +
                "    \"HUF\": 292.61,\n" +
                "    \"IDR\": 14251.55,\n" +
                "    \"ILS\": 3.2006,\n" +
                "    \"IMP\": 0.721241,\n" +
                "    \"INR\": 72.99465,\n" +
                "    \"IQD\": 1458.611795,\n" +
                "    \"IRR\": 42197.500176,\n" +
                "    \"ISK\": 126.39,\n" +
                "    \"JEP\": 0.721241,\n" +
                "    \"JMD\": 151.064842,\n" +
                "    \"JOD\": 0.7085,\n" +
                "    \"JPY\": 109.7145,\n" +
                "    \"KES\": 110,\n" +
                "    \"KGS\": 84.725593,\n" +
                "    \"KHR\": 4080.890543,\n" +
                "    \"KMF\": 418.999739,\n" +
                "    \"KPW\": 900,\n" +
                "    \"KRW\": 1155.75,\n" +
                "    \"KWD\": 0.300585,\n" +
                "    \"KYD\": 0.833161,\n" +
                "    \"KZT\": 425.495335,\n" +
                "    \"LAK\": 9578.547727,\n" +
                "    \"LBP\": 1511.769004,\n" +
                "    \"LKR\": 199.040017,\n" +
                "    \"LRD\": 171.850008,\n" +
                "    \"LSL\": 14.378688,\n" +
                "    \"LYD\": 4.512986,\n" +
                "    \"MAD\": 8.919847,\n" +
                "    \"MDL\": 17.599529,\n" +
                "    \"MGA\": 3923.952373,\n" +
                "    \"MKD\": 51.85509,\n" +
                "    \"MMK\": 1645.558901,\n" +
                "    \"MNT\": 2843.125572,\n" +
                "    \"MOP\": 8.003049,\n" +
                "    \"MRO\": 356.999828,\n" +
                "    \"MRU\": 36.120223,\n" +
                "    \"MUR\": 42.495898,\n" +
                "    \"MVR\": 15.450189,\n" +
                "    \"MWK\": 812.724795,\n" +
                "    \"MXN\": 19.923725,\n" +
                "    \"MYR\": 4.1465,\n" +
                "    \"MZN\": 63.77,\n" +
                "    \"NAD\": 14.92,\n" +
                "    \"NGN\": 411.52,\n" +
                "    \"NIO\": 35.105303,\n" +
                "    \"NOK\": 8.67225,\n" +
                "    \"NPR\": 116.804305,\n" +
                "    \"NZD\": 1.398258,\n" +
                "    \"OMR\": 0.384996,\n" +
                "    \"PAB\": 1,\n" +
                "    \"PEN\": 4.102722,\n" +
                "    \"PGK\": 3.510952,\n" +
                "    \"PHP\": 49.877563,\n" +
                "    \"PKR\": 167.215634,\n" +
                "    \"PLN\": 3.793411,\n" +
                "    \"PYG\": 6928.601622,\n" +
                "    \"QAR\": 3.685215,\n" +
                "    \"RON\": 4.1622,\n" +
                "    \"RSD\": 99.032668,\n" +
                "    \"RUB\": 72.8288,\n" +
                "    \"RWF\": 1009.12691,\n" +
                "    \"SAR\": 3.750279,\n" +
                "    \"SBD\": 8.061328,\n" +
                "    \"SCR\": 12.951571,\n" +
                "    \"SDG\": 443.5,\n" +
                "    \"SEK\": 8.5455,\n" +
                "    \"SGD\": 1.34131,\n" +
                "    \"SHP\": 0.721241,\n" +
                "    \"SLL\": 10317.35017,\n" +
                "    \"SOS\": 578.379144,\n" +
                "    \"SRD\": 21.396,\n" +
                "    \"SSP\": 130.26,\n" +
                "    \"STD\": 20747.790504,\n" +
                "    \"STN\": 21.2,\n" +
                "    \"SVC\": 8.747689,\n" +
                "    \"SYP\": 1257.544657,\n" +
                "    \"SZL\": 14.378687,\n" +
                "    \"THB\": 32.468548,\n" +
                "    \"TJS\": 11.336453,\n" +
                "    \"TMT\": 3.51,\n" +
                "    \"TND\": 2.779561,\n" +
                "    \"TOP\": 2.245994,\n" +
                "    \"TRY\": 8.3228,\n" +
                "    \"TTD\": 6.789187,\n" +
                "    \"TWD\": 27.6223,\n" +
                "    \"TZS\": 2318.382,\n" +
                "    \"UAH\": 26.897307,\n" +
                "    \"UGX\": 3519.046951,\n" +
                "    \"USD\": 1,\n" +
                "    \"UYU\": 42.595495,\n" +
                "    \"UZS\": 10667.267133,\n" +
                "    \"VES\": 4073469.565,\n" +
                "    \"VND\": 22737.403509,\n" +
                "    \"VUV\": 111.383463,\n" +
                "    \"WST\": 2.560467,\n" +
                "    \"XAF\": 551.989718,\n" +
                "    \"XAG\": 0.04046951,\n" +
                "    \"XAU\": 0.00054721,\n" +
                "    \"XCD\": 2.70255,\n" +
                "    \"XDR\": 0.704316,\n" +
                "    \"XOF\": 551.989718,\n" +
                "    \"XPD\": 0.00041217,\n" +
                "    \"XPF\": 100.418007,\n" +
                "    \"XPT\": 0.00097466,\n" +
                "    \"YER\": 250.849982,\n" +
                "    \"ZAR\": 14.31121,\n" +
                "    \"ZMW\": 16.102199,\n" +
                "    \"ZWL\": 322\n" +
                "  }\n" +
                "}";
    }

    public void testParseRates() {
        assertNotNull(mainController.parseRates(latestRatesResponse));
    }

    public void testGetTypeCourse() {
        JSONObject jsonObject = (JSONObject) mainController.parseRates(latestRatesResponse).get("rates");
        double numTest = mainController.getTypeCourse(jsonObject.get("RUB"));
        assertTrue(numTest != 0);
    }



}
