package ru.aGreen.openexchangerates.client.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Component
@RefreshScope
@ConfigurationProperties("properties")
public class RatesProperties {
    private String url;
    private String endpointCurrencies;
    private String endpointLatest;
    private String endpointHistory;
    private String appId;
    private String base;
    private String symbol;

    public String getCurrenciesUrl() {
        return getUrl() + getEndpointCurrencies() + ".json";
    }

    public String getLatestUrl() {
        return  getUrl() + getEndpointLatest() + ".json" + "?app_id=" + getAppId() + "&base=" + getBase() + "&symbols=" + getSymbol();
    }

    public String getHistoryUrl() {
        return  getUrl() + "historical/" + getEndpointHistory() + ".json" + "?app_id=" + getAppId() + "&base=" + getBase() + "&symbols=" + getSymbol();
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
}