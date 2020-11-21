package com.sda.weather.localization;

import com.sda.weather.exceptions.BlankCountryOrCityException;
import com.sda.weather.exceptions.IllegalParameterValueException;
import com.sda.weather.exceptions.NoCountryOrCityExcepton;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class LocalizationCreateService {

    final LocalizationRepository localizationRepository;

    public Localization createNewLocalization(LocalizationDefinition localizationDefinition) {
        String countryName = localizationDefinition.getCountryName();
        String cityName = localizationDefinition.getCityName();
        float latitude = localizationDefinition.getLatitude();
        float longitude = localizationDefinition.getLongitude();
        String region = localizationDefinition.getRegion();

        if (cityName.isEmpty() || countryName.isEmpty()) {
            throw new NoCountryOrCityExcepton("Country or city shouldn't be empty!");
        } else if (!countryOrCityNameValidator(cityName) || !countryOrCityNameValidator(countryName)) {
            throw new BlankCountryOrCityException("Country or city shouldn't be blank!");
        } else if (latitude > 90f || latitude < -90f) {
            throw new IllegalParameterValueException("latitude value: " + latitude);
        } else if (longitude > 180f || longitude < -180f) {
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

    boolean countryOrCityNameValidator(String name) {
        Pattern pattern = Pattern.compile("^[a-zA-Z\\u0080-\\u024F]+(?:([\\ \\-\\']|(\\.\\ ))[a-zA-Z\\u0080-\\u024F]+)*$");
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }

}
