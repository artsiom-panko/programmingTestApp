package com.panko.testapp.services;

import junit.framework.TestCase;
import org.junit.Test;

public class ValidationServiceTest extends TestCase {

    @Test
    void test() {
        String testResult = "{\"bpi\":{\"2021-01-01\":29391.775,\"2021-01-02\":32198.48,\"2021-01-03\":33033.62,\"2021-01-04\":32017.565,\"2021-01-05\":34035.0067,\"2021-01-06\":36826.9783,\"2021-01-07\":39475.6067,\"2021-01-08\":40616.7217,\"2021-01-09\":40227.8683,\"2021-01-10\":38191.55,\"2021-01-11\":35463.1883,\"2021-01-12\":34059.9133,\"2021-01-13\":37375.8033,\"2021-01-14\":39138.075,\"2021-01-15\":36770.0433,\"2021-01-16\":36029.1967,\"2021-01-17\":35845.0217,\"2021-01-18\":36656.9933,\"2021-01-19\":35929.1683,\"2021-01-20\":35510.67,\"2021-01-21\":30838.5,\"2021-01-22\":33018.825,\"2021-01-23\":32106.7033,\"2021-01-24\":32297.165,\"2021-01-25\":32255.35,\"2021-01-26\":32518.3583,\"2021-01-27\":30425.3933,\"2021-01-28\":33420.045,\"2021-01-29\":34264.01,\"2021-01-30\":34324.2717,\"2021-01-31\":33129.7433,\"2021-02-01\":33543.77},\"disclaimer\":\"This data was produced from the CoinDesk Bitcoin Price Index. BPI value data returned as USD.\",\"time\":{\"updated\":\"Feb 2, 2021 00:03:00 UTC\",\"updatedISO\":\"2021-02-02T00:03:00+00:00\"}}";

    }

    // todo MAX and Min for заданный промежуток

    // todo проверка валидности ввода

    // Проверка За границы даты

    // Проверка перепутывания дат

    // Интеграционный тест на связь с биткоин сайтом
}