package com.sda.weather.weather;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Forecast {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String temperature;
    String airPressure;
    String humidity;
    String windDirection;
    String windSpeed;
}
