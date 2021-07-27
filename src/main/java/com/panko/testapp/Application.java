package com.panko.testapp;

import com.panko.testapp.services.BpiManagerService;

public class Application {
    private static final BpiManagerService bpiManagerService = new BpiManagerService();

    public static void main(String[] args) {
        bpiManagerService.manage();
    }
}
