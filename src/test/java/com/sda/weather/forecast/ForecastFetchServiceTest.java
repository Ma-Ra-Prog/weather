package com.sda.weather.forecast;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sda.weather.localization.Localization;
import com.sda.weather.localization.LocalizationFetchService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ForecastFetchServiceTest {

    @Mock
    LocalizationFetchService localizationFetchService;
    @Spy
    RestTemplate restTemplate;
    @Spy
    ObjectMapper objectMapper;
    @Spy
    OpenWeatherConfig openWeatherConfig;
    @InjectMocks
    ForecastFetchService forecastFetchService;

    @Test
    void getForecast_returnsCorrectForecast(){
//        given
        Localization localization = new Localization();
        localization.setCityName("Warsaw");
        when(localizationFetchService.fetchLocalization(2L)).thenReturn(localization);
        when(openWeatherConfig.getAppid()).thenReturn("00bb9cd9ad5f5584c0dbdfedfef9739f");
//        when
        forecastFetchService.getForecast(2L, "1");
//        then
    }
}
