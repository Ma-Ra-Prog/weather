package com.sda.weather.localization;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocalizationDto {
    Long id;
    String cityName;
    String countryName;
    float longitude;
    float latitude;
    String region;
}
