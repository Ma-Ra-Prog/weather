package com.sda.weather.localization;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.Instant;

@Entity
@Data
public class Localization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String cityName;
    String countryName;
    float longitude;
    float latitude;
    String region;
    Instant creationDate;
}
