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
    String temperature;
    String airPressure;
    String humidity;
    String windDegree;
    String windSpeed;
    String date;
    @ManyToOne
    Localization localization;
}
