package com.sda.weather.localization;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}
