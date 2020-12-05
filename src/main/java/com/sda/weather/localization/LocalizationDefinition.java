package com.sda.weather.localization;

import lombok.Builder;
import lombok.Getter;

import java.util.Optional;

@Builder
@Getter
class LocalizationDefinition {

    private String cityName;
    private String countryName;
    private double latitude;
    private double longitude;
    private String region;

    public Optional<String> getRegion() {
        return Optional.ofNullable(region);
    }
}
