package com.panko.testapp.services;

import com.panko.testapp.models.BpiCurrency;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BpiProviderServiceTest {

    @Mock
    private RestTemplate mock;

    private static final BpiProviderService bpiProviderService = new BpiProviderService();

    //    @Test
    public void getCurrentBitcoinRate() throws IOException {
        String expectedRate = "32,243.7393";
        String expectedCode = "EUR";
        float expectedRateFloat = 32243.7393f;

        when(mock.getForObject(any(), any()))
                .thenReturn(new FileReader("src/test/resources/dataStub/currentBitcoinRate.json").toString());

        BpiCurrency realBpiCurrency = bpiProviderService.getCurrentBitcoinRate(expectedCode);

        assertEquals(expectedRate, realBpiCurrency.getRate());
        assertEquals(expectedCode, realBpiCurrency.getCode());
        assertEquals(expectedRateFloat, realBpiCurrency.getRateFloat());
    }

    //    @Test
    public void testGettingHistoricalData() throws IOException {
        Map<String, Double> expectedMap = new HashMap<>();
        expectedMap.put("2020-01-01", 123.4633);
        expectedMap.put("2020-01-02", 6961.5683);
        expectedMap.put("2020-01-03", 7346.58);
        expectedMap.put("2020-01-04", 7355.855);
        expectedMap.put("2020-01-05", 7356.3117);

        Map<String, Double> actualMap;
        String actualStartPeriodDate = "2020-01-01";
        String actualEndPeriodDate = "2020-01-05";

        when(mock.getForObject(any(), any()))
                .thenReturn(new FileReader("src/test/resources/dataStub/historicalBitcoinRate.json").toString());

        actualMap = bpiProviderService.getHistoricalData(actualStartPeriodDate, actualEndPeriodDate);

        assertEquals(expectedMap, actualMap);
    }
}
