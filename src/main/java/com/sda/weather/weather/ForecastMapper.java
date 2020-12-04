package com.sda.weather.weather;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Component
@NoArgsConstructor
public class ForecastMapper {

    ForecastDto mapToForecastDto(Forecast newForecast){
        return ForecastDto.builder()
                .id(newForecast.getId())
                .temperature(newForecast.getTemperature())
                .airPressure(newForecast.getAirPressure())
                .humidity(newForecast.getHumidity())
                .windDirection(newForecast.getWindDirection())
                .windSpeed(newForecast.getWindSpeed())
                .build();
    }
}
