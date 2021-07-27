package com.panko.testapp;

import com.panko.testapp.services.BpiDisplayService;

import java.io.IOException;
import java.util.Scanner;

import static java.lang.System.out;
import static java.lang.System.err;

// TODO Add more unit and Integration Tests
// TODO Remove spring framework
public class Application {
    private static final BpiDisplayService bpiDisplayService = new BpiDisplayService();

    public static void main(String[] args) {
        err.println("Disclaimer: Powered by CoinDesk https://www.coindesk.com/price/bitcoin \n");

        Scanner keyboardInput = new Scanner(System.in);
        boolean continueFlag;

        do {
            try {
                bpiDisplayService.displayCurrentBpi(keyboardInput);
                bpiDisplayService.displayMaxAndMinBpiRates(keyboardInput);
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
