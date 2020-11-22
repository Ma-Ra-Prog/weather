package com.sda.weather.localization;

import com.sda.weather.exceptions.BlankCountryOrCityException;
import com.sda.weather.exceptions.IllegalParameterValueException;
import com.sda.weather.exceptions.NoCountryOrCityExcepton;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LocalizationCreateService {

    final LocalizationRepository localizationRepository;
    private LocalizationInputValidator localizationInputValidator = new LocalizationInputValidator();

    public Localization createNewLocalization(LocalizationDefinition localizationDefinition) {
        String countryName = localizationDefinition.getCountryName();
        String cityName = localizationDefinition.getCityName();
        float latitude = localizationDefinition.getLatitude();
        float longitude = localizationDefinition.getLongitude();
        String region = localizationDefinition.getRegion();

        if (cityName.isEmpty() || countryName.isEmpty()) {
            throw new NoCountryOrCityExcepton("Country or city shouldn't be empty!");
        } else if (!localizationInputValidator.isFormatCorrect(cityName) || !localizationInputValidator.isFormatCorrect(countryName)) {
            throw new BlankCountryOrCityException("Country or city shouldn't be blank!");
        } else if (!localizationInputValidator.isLatitudeCorrect(latitude)) {
            throw new IllegalParameterValueException("latitude value: " + latitude);
        } else if (!localizationInputValidator.isLongitudeCorrect(longitude)) {
            throw new IllegalParameterValueException("longitude value: " + longitude);
        }

        Localization localization = new Localization();
        localization.setCityName(cityName);
        localization.setCountryName(countryName);
        localization.setLatitude(latitude);
        localization.setLongitude(longitude);
        localization.setRegion(region);

        return localizationRepository.save(localization);
    }

}
