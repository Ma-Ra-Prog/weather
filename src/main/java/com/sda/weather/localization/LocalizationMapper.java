package com.sda.weather.localization;

import org.springframework.stereotype.Component;

@Component
class LocalizationMapper {

    LocalizationDto mapToLocalizationDto(Localization localization) {
        return new LocalizationDto().builder()
                .id(localization.getId())
                .cityName(localization.getCityName())
                .countryName(localization.getCountryName())
                .latitude(localization.getLatitude())
                .longitude(localization.getLongitude())
                .region(localization.getRegion().orElse(null))
                .build();
    }

    LocalizationDefinition mapToLocalizationDefinition(LocalizationDto localizationDto) {
        return LocalizationDefinition.builder()
                .cityName(localizationDto.getCityName())
                .countryName(localizationDto.getCountryName())
                .latitude(localizationDto.getLatitude())
                .longitude(localizationDto.getLongitude())
                .region(localizationDto.getRegion())
                .build();
    }
}
