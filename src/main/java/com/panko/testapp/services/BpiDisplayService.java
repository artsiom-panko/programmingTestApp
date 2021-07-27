package com.panko.testapp.services;

import com.panko.testapp.models.BpiCurrency;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;

import static java.lang.System.out;

public class BpiDisplayService {
    private static final BpiProviderService bpiProviderService = new BpiProviderService();
    private static final ValidationService validationService = new ValidationService();

    /**
     * The method processes the user's date input and displays
     * the current Bitcoin rate in the requested currency on the screen after validation
     *
     * @param keyboard Scanner input for getting input from user
     */
    public void displayCurrentBpi(String enteredCurrency) throws IOException {
        validationService.validateEnteredCurrency(enteredCurrency);

        Currency currency = bpiProviderService.getCurrentBitcoinRate(enteredCurrency.toUpperCase());

        out.printf("1 BTC = %s %s (%s)%n", currency.getRate(), currency.getCode(), currency.getDescription());
    }

    /**
     * The method processes the user's date input and displays
     * the lowest and highest Bitcoin rate in requested date range
     *
     * @param keyboard Scanner input for getting input from user
     */
    public void displayMaxAndMinBpiRates(String startPeriodDate, String endPeriodDate) throws IOException {
        validationService.validateEnteredDates(startPeriodDate, endPeriodDate);

        Map<String, Double> bitcoinDateExchangeRateMap =
                bpiProviderService.getHistoricalData(startPeriodDate, endPeriodDate);

        String highestBitcoinRate = Collections
                .max(bitcoinDateExchangeRateMap.entrySet(), Comparator.comparingDouble(Map.Entry::getValue)).getKey();
        String lowestBitcoinRate = Collections
                .min(bitcoinDateExchangeRateMap.entrySet(), Comparator.comparingDouble(Map.Entry::getValue)).getKey();

        out.printf("The highest Bitcoin rate was %s$ on the %s%n",
                bitcoinDateExchangeRateMap.get(highestBitcoinRate), highestBitcoinRate);
        out.printf("The lowest Bitcoin rate was %s$ on the %s%n",
                bitcoinDateExchangeRateMap.get(lowestBitcoinRate), lowestBitcoinRate);
    }
}
