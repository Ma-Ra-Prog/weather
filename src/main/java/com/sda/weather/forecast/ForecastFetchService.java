package com.sda.weather.forecast;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sda.weather.exceptions.BadRequestException;
import com.sda.weather.exceptions.ForecastFetchError;
import com.sda.weather.exceptions.JsonDataProcessingErrorException;
import com.sda.weather.localization.Localization;
import com.sda.weather.localization.LocalizationFetchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ForecastFetchService {

    private final LocalizationFetchService localizationFetchService;
    private final OpenWeatherConfig openWeatherConfig;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final ForecastMapper forecastMapper;
    private final ForecastRepository forecastRepository;

    public Forecast getForecast(Long id, Integer period) {

        Localization localization = localizationFetchService.fetchLocalization(id);
        ForecastOpenWeatherResponse forecastResponse = getForecastOpenWeatherResponseWithLocalization(localization);

        LocalDate localDateNow = LocalDate.now().plusDays(period);
        ForecastOpenWeatherResponse.SingleForecast singleForecast = getSingleForecastForTomorrow(forecastResponse, localDateNow).orElseGet(null);

        if (singleForecast == null) {
            throw new ForecastFetchError("Unable to fetch forecast for date: " + localDateNow + " 12:00:00");
        }

        Forecast forecast = forecastMapper.mapToForecast(singleForecast, localization);
        forecast.setCreationDate(Instant.now());

        return forecastRepository.save(forecast);
    }

    private ForecastOpenWeatherResponse getForecastOpenWeatherResponseWithLocalization(Localization localization) {
        String url = UriComponentsBuilder.newInstance()
                .scheme("http")
                .host("api.openweathermap.org/data/2.5/forecast")
                .queryParam("q", localization.getCityName())
                .queryParam("appid", openWeatherConfig.getAppid())
                .queryParam("units", "metric")
                .build().toUriString();

        ResponseEntity<String> entity = restTemplate.getForEntity(url, String.class);

        if (!entity.getStatusCode().is2xxSuccessful()) {
            throw new BadRequestException("Unable to get data from service.");
        }

        String response = entity.getBody();
        ForecastOpenWeatherResponse forecastResponse;

        try {
            forecastResponse = objectMapper.readValue(response, ForecastOpenWeatherResponse.class);
        } catch (JsonProcessingException e) {
            throw new JsonDataProcessingErrorException("Unable to process forecast data from Json");
        }
        return forecastResponse;
    }

    private Optional<ForecastOpenWeatherResponse.SingleForecast> getSingleForecastForTomorrow(ForecastOpenWeatherResponse forecastResponse, LocalDate localDateNow) {
        return Optional.ofNullable(forecastResponse.getList()
                .stream()
                .filter(x -> x.getDate()
                        .equals(localDateNow + " 12:00:00"))
                .findFirst().orElseGet(null));
    }

    public Forecast getForecastWithCityNameAndDate(String cityName, int period) {
        // todo: wypełnić metodę po opracowaniu poprzedniej
        return null;
    }
}
