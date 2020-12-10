package com.sda.weather.forecast;

import com.sda.weather.localization.Localization;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Data
@Component
@RequiredArgsConstructor
public class ForecastMapper {

    private final WindMapper windMapper;

    ForecastDto mapToForecastDto(Forecast newForecast) {
        return ForecastDto.builder()
                .temperature(newForecast.getTemperature())
                .airPressure(newForecast.getAirPressure())
                .humidity(newForecast.getHumidity())
                .windDegree(newForecast.getWindDegree())
                .windDirection(newForecast.getWindDirection())
                .windSpeed(newForecast.getWindSpeed())
                .forecastDate(instantToStringFormatter(newForecast.getForecastDate()))
                .build();
    }

    Forecast mapToForecast(ForecastOpenWeatherResponse.SingleForecast singleForecastResponse, Localization localization) {
        LocalDateTime forecastLocalDateTime = mapToLocalDateTime(singleForecastResponse);
        Instant forecastDateInstant = forecastLocalDateTime.atZone(ZoneId.systemDefault()).toInstant();

        Forecast forecast = new Forecast();
        forecast.setTemperature(singleForecastResponse.getWeather().getTemperature());
        forecast.setAirPressure(singleForecastResponse.getWeather().getPressure());
        forecast.setHumidity(singleForecastResponse.getWeather().getHumidity());
        forecast.setWindDegree(singleForecastResponse.getWind().getDegree());
        forecast.setWindDirection(windMapper.mapWindDegreeToWindDirection(singleForecastResponse.getWind().getDegree()));
        forecast.setWindSpeed(singleForecastResponse.getWind().getSpeed());
        forecast.setForecastDate(forecastDateInstant);
        forecast.setLocalization(localization);

        return forecast;
    }

    private LocalDateTime mapToLocalDateTime(ForecastOpenWeatherResponse.SingleForecast singleForecastResponse) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(singleForecastResponse.getDate(), dateTimeFormatter);
    }

    private String instantToStringFormatter(Instant instant) {
        String instantToString = instant.toString();
        return instantToString.substring(0, 10) + " " + instantToString.substring(11, 19);
    }
}
