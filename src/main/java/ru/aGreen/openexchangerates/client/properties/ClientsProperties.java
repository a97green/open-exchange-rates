package ru.aGreen.openexchangerates.client.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Component
@RefreshScope
@ConfigurationProperties("client-properties")
public class ClientsProperties {
    private String url;
    private String endpointCurrencies;
    private String endpointLatest;
    private String endpointHistory;
    private String appId;
    private String base;
    private String symbol;

    private String urlGif;
    private String apiKey;
    private String tagMore;
    private String tagLess;
    private String rating;

    public String getCurrenciesUrl() {
        return getUrl() + getEndpointCurrencies() + ".json";
    }

    public String getLatestUrl() {
        return  getUrl() + getEndpointLatest() + ".json" + "?app_id=" + getAppId() + "&base=" + getBase() + "&symbols=";
    }

    public String getHistoryUrl() {
        return  getUrl() + "historical/" + getEndpointHistory() + ".json" + "?app_id=" + getAppId() + "&base=" + getBase() + "&symbols=";
    }



    public String getMoreUrl() {
        return getUrlGif() + "?api_key=" + getApiKey() + "&tag=" + getTagMore() + "&rating=" + getRating();
    }

    public String getLessUrl() {
        return getUrlGif() + "?api_key=" + getApiKey() + "&tag=" + getTagLess() + "&rating=" + getRating();
    }



    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getEndpointCurrencies() {
        return endpointCurrencies;
    }

    public void setEndpointCurrencies(String endpointCurrencies) {
        this.endpointCurrencies = endpointCurrencies;
    }

    public String getEndpointLatest() {
        return endpointLatest;
    }

    public void setEndpointLatest(String endpointLatest) {
        this.endpointLatest = endpointLatest;
    }

    public String getEndpointHistory() {
        return endpointHistory;
    }

    public void setEndpointHistory(String endpointHistory) {
        this.endpointHistory = endpointHistory;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }



    public String getUrlGif() {
        return urlGif;
    }

    public void setUrlGif(String urlGif) {
        this.urlGif = urlGif;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getTagMore() {
        return tagMore;
    }

    public void setTagMore(String tagMore) {
        this.tagMore = tagMore;
    }

    public String getTagLess() {
        return tagLess;
    }

    public void setTagLess(String tagLess) {
        this.tagLess = tagLess;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}