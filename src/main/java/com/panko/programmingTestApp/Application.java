package com.panko.programmingTestApp;

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
