package com.panko.testapp.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.panko.testapp.models.Currency;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.*;

public class BpiProviderService {
    private static final RestTemplate restTemplate = new RestTemplate();
    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Provides the current Bitcoin rate in the requested currency
     *
     * @param requestedCurrency Requested currency
     * @return String of daily Bitcoin rate in the format "yyyy-mm-dd: rate" (e.g. ""2013-09-01": 128.2597"")
     */
    public Currency getCurrentBitcoinRate(String requestedCurrency) {
        String endpointUrl = String.format("https://api.coindesk.com/v1/bpi/currentprice/%s", requestedCurrency);

        String response = restTemplate.getForObject(endpointUrl, String.class);

        try {
            JsonNode currencyNode = objectMapper.readValue(response, JsonNode.class).get("bpi").get(requestedCurrency);
            return objectMapper.readValue(currencyNode.toString(), Currency.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // TODO null?
        return null;
    }

    // TODO Refactor description

    /**
     * Provides historical data of the Bitcoin exchange rate for each day in a given period
     *
     * @param startPeriodDate Start date of the period
     * @param endPeriodDate   End date of the period
     * @return String of daily Bitcoin rate in the format "yyyy-mm-dd: rate" (e.g. ""2013-09-01": 128.2597"")
     */
    public Map<String, Double> getHistoricalData(String startPeriodDate, String endPeriodDate) {
        String endpointUrl = String.format("https://api.coindesk.com/v1/bpi/historical/close.json?start=%s&end=%s",
                startPeriodDate, endPeriodDate);

        String response = restTemplate.getForObject(endpointUrl, String.class);

        try {
            JsonNode currencyNode = objectMapper.readValue(response, JsonNode.class).get("bpi");

            return objectMapper.readValue(currencyNode.toString(), Map.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // TODO null?
        return null;
    }
}
