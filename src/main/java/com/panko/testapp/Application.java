package com.panko.testapp;

import com.panko.testapp.services.CoinRateService;

import java.io.IOException;

public class Application {
    public static void main(String[] args) {
        try {
            new CoinRateService().run(null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
