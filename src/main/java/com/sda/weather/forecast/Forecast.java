package com.sda.weather.forecast;

import com.sda.weather.localization.Localization;
import lombok.Data;

import javax.persistence.*;

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
    int windSpeed;
    String date;
    @ManyToOne
    Localization localization;
}
