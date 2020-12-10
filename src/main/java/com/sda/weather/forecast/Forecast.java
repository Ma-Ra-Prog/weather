package com.sda.weather.forecast;

import com.sda.weather.localization.Localization;
import lombok.Data;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Data
public class Forecast {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    float temperature;
    int airPressure;
    int humidity;
    float windDegree;
    String windDirection;
    float windSpeed;
    Instant forecastDate;
    Instant creationDate;
    @ManyToOne
    Localization localization;
}
