package com.sda.weather.localization;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class LocalizationDefinition {
    private String cityName;
    private String countryName;
    private double latitude;
    private double longitude;
    private String region;

}
