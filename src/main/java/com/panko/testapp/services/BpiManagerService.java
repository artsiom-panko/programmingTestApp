package com.panko.testapp.services;

import java.io.IOException;
import java.util.Scanner;

import static java.lang.System.err;
import static java.lang.System.out;

public class BpiManagerService {
    private static final BpiDisplayService bpiDisplayService = new BpiDisplayService();

    /**
     * Requests input data from the user, provides informative errors about incorrect input,
     * controls the exit from the application on demand
     */
    public void manage() {
        err.println("Disclaimer: Powered by CoinDesk https://www.coindesk.com/price/bitcoin \n");

        Scanner keyboardInput = new Scanner(System.in);
        boolean continueFlag;

        do {
            try {
                out.print("Enter the currency: ");
                String enteredCurrency = keyboardInput.next();
                bpiDisplayService.displayCurrentBpi(enteredCurrency);

                out.println("Enter the start date of the period (YYYY-MM-DD): ");
                String startPeriodDate = keyboardInput.next();

                out.println("Enter the end date of the period (YYYY-MM-DD): ");
                String endPeriodDate = keyboardInput.next();
                bpiDisplayService.displayMaxAndMinBpiRates(startPeriodDate, endPeriodDate);
            } catch (IOException e) {
                out.println(e.getMessage());
            }
            out.print("Do you want to continue? (Press Y|y for Yes, any other key for No): ");
            continueFlag = keyboardInput.next().toLowerCase().trim().equals("y");

        } while (continueFlag);

        keyboardInput.close();
        err.println("\n Have a nice day =)");
    }
}
