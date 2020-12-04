package com.sda.weather.weather;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sda.weather.exceptions.BadRequestException;
import com.sda.weather.exceptions.JsonDataProcessingErrorException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@RequiredArgsConstructor
public class ForecastFetchService {

    private final ForecastRepository forecastRepository;
    private final WeatherStackConfig weatherStackConfig;
    private final ForecastMapper forecastMapper;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public ForecastDto getForecast(String location){

        UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl(weatherStackConfig.getUri())
                .queryParam("access_key", weatherStackConfig.getApiKey())
                .queryParam("query", location)
                .queryParam("units", weatherStackConfig.getUnits())
                .queryParam("lang", weatherStackConfig.getLang())
                .build();

        ResponseEntity<String> responseEntity = restTemplate.getForEntity(uriComponents.toUri(), String.class);

        if (!responseEntity.getStatusCode().is2xxSuccessful()) {
            throw new BadRequestException("Unable to get data from service.");
        }

        String responseBody = responseEntity.getBody();

        try {
            ForecastDto forecastDto = objectMapper.readValue(responseBody, ForecastDto.class);
            return saveToDatabase(forecastDto);
        } catch (JsonProcessingException e) {
            throw new JsonDataProcessingErrorException("Unable to process forecast data.");
        }
    }

    public ForecastDto saveToDatabase(ForecastDto forecastDTO) {
        Forecast forecast = new Forecast();
        forecast.setTemperature(forecastDTO.getTemperature());
        forecast.setAirPressure(forecastDTO.getAirPressure());
        forecast.setHumidity(forecastDTO.getHumidity());
        forecast.setWindDirection(forecastDTO.getWindDirection());
        forecast.setWindSpeed(forecastDTO.getWindSpeed());
        return forecastMapper.mapToForecastDto(forecastRepository.save(forecast));
    }
}
