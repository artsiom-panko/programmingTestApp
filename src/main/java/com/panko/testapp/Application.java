package com.panko.testapp;

import com.panko.testapp.services.BpiDisplayService;

import java.util.Scanner;

import static java.lang.System.out;
import static java.lang.System.err;

// TODO Add more unit and Integration Tests
// TODO Remove spring framework
// TODO Fix all code smells
// TODO Add description to all service's public methods

public class Application {
    private static final BpiDisplayService bpiDisplayService = new BpiDisplayService();

    public static void main(String[] args) {
        err.println("Disclaimer: This data was produced from the CoinDesk Bitcoin Price Index (USD). \n" +
                "Non-USD currency data converted using hourly conversion rate from openexchangerates.org \n" +
                "Powered by CoinDesk https://www.coindesk.com/price/bitcoin \n");

        try (Scanner keyboardInput = new Scanner(System.in)) {
            boolean continueFlag;
            do {
                bpiDisplayService.displayCurrentBpi(keyboardInput);
                bpiDisplayService.displayMaxAndMinBpiRates(keyboardInput);

                out.print("Do you want to continue? (Press Y|y for Yes, any other key for No): ");
                continueFlag = keyboardInput.next().toLowerCase().trim().equals("y");
            } while (continueFlag);
        } catch (RuntimeException e) {
            out.println(e);
        } finally {
            err.println("\n Have a nice day =)");
        }
    }
}
