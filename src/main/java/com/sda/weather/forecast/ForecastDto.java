package com.sda.weather.forecast;

import com.sda.weather.localization.Localization;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

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
    int windSpeed;
    String date;           //todo: LocalDateTime
    Localization localization;
}
