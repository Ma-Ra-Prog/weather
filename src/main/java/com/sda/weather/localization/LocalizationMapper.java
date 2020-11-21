package com.sda.weather.localization;

public class LocalizationMapper {

    LocalizationDto mapToLocalizationDto(Localization localization) {
        LocalizationDto localizationDto = new LocalizationDto();
        localizationDto.setId(localization.getId());
        localizationDto.setCityName(localization.getCityName());
        localizationDto.setCountryName(localization.getCountryName());
        localizationDto.setLatitude(localization.getLatitude());
        localizationDto.setLongitude(localization.getLongitude());
        localizationDto.setRegion(localization.getRegion());
        return localizationDto;
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
