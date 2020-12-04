package com.sda.weather.weather;

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
    String windDirection;
    String windSpeed;
}
