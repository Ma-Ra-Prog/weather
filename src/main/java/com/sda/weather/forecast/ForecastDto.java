package com.sda.weather.forecast;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ForecastDto {
    float temperature;
    int airPressure;
    int humidity;
    float windDegree;
    String windDirection;
    float windSpeed;
    String forecastDate;
}
