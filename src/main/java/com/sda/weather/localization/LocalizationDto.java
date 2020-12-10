package com.sda.weather.localization;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
class LocalizationDto {

    Long id;
    String cityName;
    String countryName;
    double latitude;
    double longitude;
    String region;

    public Optional<String> getRegion() {
        return Optional.ofNullable(region);
    }
}
