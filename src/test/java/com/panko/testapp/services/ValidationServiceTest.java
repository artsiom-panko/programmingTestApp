package com.panko.testapp.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ValidationServiceTest {

    private static final ValidationService validationService = new ValidationService();

    @Test
    void testWrongCurrencyCode() {
        String wrongCurrencyCode = "AAA";

        assertThrows(IOException.class, () ->
                validationService.validateEnteredCurrency(wrongCurrencyCode));
    }

    @Test
    void testCorrectCurrencyCode() {
        String correctCurrencyCode = "RUB";

        assertDoesNotThrow(() -> validationService.validateEnteredCurrency(correctCurrencyCode));
    }

    @Test
    void testOverLowerDateLimit() {
        String startPeriodDate = "1990-01-01";
        String endPeriodDate = "2020-02-02";

        Assertions.assertThrows(IOException.class, () ->
                validationService.validateEnteredDates(startPeriodDate, endPeriodDate));
    }

    @Test
    void testWrongDateFormat() {
        String startPeriodDate1 = "1990/01/01";
        String endPeriodDate1 = "2020-02-02";

        Assertions.assertThrows(IOException.class, () ->
                validationService.validateEnteredDates(startPeriodDate1, endPeriodDate1));

        String startPeriodDate2 = "1990.01.01";
        String endPeriodDate2 = "2020-02-02";

        Assertions.assertThrows(IOException.class, () ->
                validationService.validateEnteredDates(startPeriodDate2, endPeriodDate2));

        String startPeriodDate3 = "1990/13/13";
        String endPeriodDate3 = "2020-02-02";

        Assertions.assertThrows(IOException.class, () ->
                validationService.validateEnteredDates(startPeriodDate3, endPeriodDate3));
    }
}
