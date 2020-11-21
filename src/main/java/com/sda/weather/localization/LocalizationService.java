package com.sda.weather.localization;

import com.sda.weather.exceptions.IllegalParameterValueException;
import com.sda.weather.exceptions.NoCountryOrCityExcepton;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LocalizationService {

    final LocalizationRepository localizationRepository;

    public Localization createNewLocalization(LocalizationDefinition localizationDefinition) {
        String countryName = localizationDefinition.getCountryName();
        String cityName = localizationDefinition.getCityName();
        float latitude = localizationDefinition.getLatitude();
        float longitude = localizationDefinition.getLongitude();
        String region = localizationDefinition.getRegion();

        if (cityName.isEmpty() || countryName.isEmpty()) {
            throw new NoCountryOrCityExcepton("Country or city shouldn't be empty!");
        } else if (latitude>90f || latitude<-90f){
            throw new IllegalParameterValueException("latitude value: " + latitude);
        } else if (longitude>90f || longitude<-90f){
            throw new IllegalParameterValueException("longitude value: " + longitude);
        }

        Localization localization = new Localization();
        localization.setCityName(cityName);
        localization.setCityName(countryName);
        localization.setLatitude(latitude);
        localization.setLongitude(longitude);
        localization.setRegion(region);

        return localizationRepository.save(localization);
    }
}
