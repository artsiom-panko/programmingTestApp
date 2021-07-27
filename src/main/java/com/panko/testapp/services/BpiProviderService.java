package com.panko.testapp.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.panko.testapp.models.Currency;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

public class BpiProviderService {
    private static final RestTemplate restTemplate = new RestTemplate();
    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * This service provides the current Bitcoin exchange rate
     * in the currency entered by the user to date
     *
     * @param requestedCurrency Requested currency
     * @return String of daily Bitcoin rate in the format "yyyy-mm-dd: rate" (e.g. ""2013-09-01": 128.2597"")
     */
    public Currency getCurrentBitcoinRate(String requestedCurrency) throws IOException {
        String endpointUrl = String.format("https://api.coindesk.com/v1/bpi/currentprice/%s", requestedCurrency);
        String response = restTemplate.getForObject(endpointUrl, String.class);
        JsonNode currencyNode = Optional.ofNullable(objectMapper.readValue(response, JsonNode.class).get("bpi").get(requestedCurrency))
                .orElseThrow(() -> new IOException(String.format("Incorrect input: %s", requestedCurrency)));

        return objectMapper.readValue(currencyNode.toString(), Currency.class);
    }

    /**
     * Provides historical data of the Bitcoin exchange rate
     * for each day in a given period
     *
     * @param startPeriodDate Start date of the period
     * @param endPeriodDate   End date of the period
     * @return String of daily Bitcoin rate in the format "yyyy-mm-dd: rate" (e.g. ""2013-09-01": 128.2597"")
     */
    public Map<String, Double> getHistoricalData(String startPeriodDate, String endPeriodDate) throws IOException {
        String endpointUrl = String.format("https://api.coindesk.com/v1/bpi/historical/close.json?start=%s&end=%s",
                startPeriodDate, endPeriodDate);
        String response = restTemplate.getForObject(endpointUrl, String.class);
        JsonNode currencyNode = objectMapper.readValue(response, JsonNode.class).get("bpi");

        return objectMapper.readValue(currencyNode.toString(), Map.class);
    }
}
