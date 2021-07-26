package com.panko.testapp.services;

import com.panko.testapp.models.Currency;

import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.Scanner;

import static java.lang.System.out;

public class BpiDisplayService {
    private static final BpiProviderService bpiProviderService = new BpiProviderService();

    public void displayCurrentBpi(Scanner keyboard) {
        out.print("Enter the currency: ");
        String requestedCurrencyCode = keyboard.next();

        Currency currency = bpiProviderService.getCurrentBitcoinRate(requestedCurrencyCode);

        out.println(String.format("1 BTC = %s %s (%s)",
                currency.getRate(), currency.getCode(), currency.getDescription()));
    }

    public void displayMaxAndMinBpiRates(Scanner keyboard) {
        out.println("Enter the start date of the period (YYYY-MM-DD): ");
        String startPeriodDate = keyboard.next();

        out.println("Enter the end date of the period (YYYY-MM-DD): ");
        String endPeriodDate = keyboard.next();

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
