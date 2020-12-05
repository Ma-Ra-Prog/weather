package com.sda.weather.forecast;

import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ForecastDto {
    Long id;
    String temperature;
    String airPressure;
    String humidity;
    String windDegree;
    String windSpeed;
    String date; //todo: LocalDateTime
}
