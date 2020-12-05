package com.sda.weather.localization;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sda.weather.forecast.Forecast;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

@Entity
@Data
public class Localization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cityName;
    private String countryName;
    private double latitude;
    private double longitude;
    private String region;
    @OneToMany(mappedBy = "localization")
    @JsonBackReference
    List<Forecast> forecastList;

    public Optional<String> getRegion() {
        return Optional.ofNullable(region);
    }
}
