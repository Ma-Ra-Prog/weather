package com.sda.weather.forecast;

import com.sda.weather.localization.Localization;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.Instant;

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
    Localization localization;
}
