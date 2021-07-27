package com.panko.testapp.services;

import com.panko.testapp.models.Currency;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.Scanner;

import static java.lang.System.out;

public class BpiDisplayService {
    private static final BpiProviderService bpiProviderService = new BpiProviderService();
    private static final ValidationService validationService = new ValidationService();

    // TODO Refactor description

    /**
     * The method processes the user's date input and displays
     * the current Bitcoin rate in the requested currency on the screen after validation
     *
     * @param keyboard Scanner input for getting input from user
     */
    public void displayCurrentBpi(Scanner keyboard) throws IOException {
        out.print("Enter the currency: ");
        String enteredCurrency = keyboard.next();

        validationService.validateEnteredCurrency(enteredCurrency);

        Currency currency = bpiProviderService.getCurrentBitcoinRate(enteredCurrency.toUpperCase());

        out.println(String.format("1 BTC = %s %s (%s)",
                currency.getRate(), currency.getCode(), currency.getDescription()));
    }


    // TODO Refactor description

    /**
     * The method processes the user's date input and displays
     * the lowest and highest Bitcoin rate in requested date range
     *
     * @param keyboard Scanner input for getting input from user
     */
    public void displayMaxAndMinBpiRates(Scanner keyboard) throws IOException {
        out.println("Enter the start date of the period (YYYY-MM-DD): ");
        String startPeriodDate = keyboard.next();

        out.println("Enter the end date of the period (YYYY-MM-DD): ");
        String endPeriodDate = keyboard.next();

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
