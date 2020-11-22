package com.sda.weather.localization;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.Instant;
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

    public Optional<String> getRegion() {
        return Optional.ofNullable(region);
    }
}
