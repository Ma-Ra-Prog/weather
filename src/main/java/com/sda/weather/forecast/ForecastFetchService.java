package com.sda.weather.forecast;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sda.weather.exceptions.BadRequestException;
import com.sda.weather.exceptions.JsonDataProcessingErrorException;
import com.sda.weather.localization.Localization;
import com.sda.weather.localization.LocalizationFetchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@RequiredArgsConstructor
public class ForecastFetchService {

    private final LocalizationFetchService localizationFetchService;
    private final OpenWeatherConfig openWeatherConfig;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public Forecast getForecast(Long id, String period) {
        Localization localization = localizationFetchService.fetchLocalization(id);
        String url = UriComponentsBuilder.newInstance()
                .scheme("http")
                .host("api.openweathermap.org/data/2.5/forecast")
                .queryParam("q", localization.getCityName())
                .queryParam("appid", openWeatherConfig.getAppid())
                .build().toUriString();

        ResponseEntity<String> entity = restTemplate.getForEntity(url, String.class);

        if (!entity.getStatusCode().is2xxSuccessful()) {
            throw new BadRequestException("Unable to get data from service.");
        }

        String response = entity.getBody();

        try {
            ForecastOpenWeatherResponse forecast = objectMapper.readValue(response, ForecastOpenWeatherResponse.class);
        } catch (JsonProcessingException e) {
            throw new JsonDataProcessingErrorException("Unable to process forecast data from Json");
        }

        // todo: zapis do bazy danych

        return new Forecast();
    }

    public Forecast getForecastWithCityNameAndDate(String cityName, int period) {
        return null;
    }
}
