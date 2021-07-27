package com.panko.testapp.services;

import java.io.IOException;
import java.time.LocalDate;

public class ValidationService {
    private static final String DATE_REGEX = "^\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])$";

    /**
     * Validates the date entered by the user for errors and inaccuracies.
     * In case of finding errors returns IOException with an error with the corresponding message
     *
     * @param startPeriodDate Start date of the period
     * @param endPeriodDate   End date of the period
     */
    public void validateEnteredDates(String startPeriodDate, String endPeriodDate) throws IOException {
        if (!startPeriodDate.matches(DATE_REGEX) || !endPeriodDate.matches(DATE_REGEX)) {
            throw new IOException("Invalid entered date format. (e.g 2021-02-01)");
        } else if (LocalDate.parse(endPeriodDate).isAfter(LocalDate.now())) {
            throw new IOException("Entered end day has not yet arrived.");
        } else if (LocalDate.parse(startPeriodDate).isBefore(LocalDate.of(2010, 7, 17))) {
            throw new IOException("The CoinDesk BPI only covers data from 2010-07-17 onwards.");
        }
    }

    /**
     * Validates the currency entered by the user for errors and inaccuracies.
     * In case of finding errors returns IOException with an error with the corresponding message.
     *
     * @param enteredCurrency Start date of the period
     */
    public void validateEnteredCurrency(String enteredCurrency) throws IOException {
        if (!isCurrencyCodeExists(enteredCurrency)) {
            throw new IOException("Incorrect or unsupported currency.");
        }
    }

    private boolean isCurrencyCodeExists(String currencyCode) {
        try {
            java.util.Currency.getInstance(currencyCode);
        } catch (IllegalArgumentException exception) {
            return false;
        }
        return true;
    }
}
