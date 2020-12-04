package com.sda.weather.localization;

import com.sda.weather.exceptions.IllegalParameterValueException;
import com.sda.weather.exceptions.InvalidCountryOrCityException;
import com.sda.weather.exceptions.NoCountryOrCityException;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class LocalizationInputValidator {

    void isInputDataCorrect(LocalizationDefinition localizationDefinition) {
        if (!isCityNameNotEmpty(localizationDefinition.getCityName())) {
            throw new NoCountryOrCityException("City name shouldn't be empty!");
        } else if (!isCountryNameNotEmpty(localizationDefinition.getCountryName())) {
            throw new NoCountryOrCityException("Country name shouldn't be empty!");
        } else if (!isFormatCorrect(localizationDefinition.getCityName())) {
            throw new InvalidCountryOrCityException("City name");
        } else if (!isFormatCorrect(localizationDefinition.getCountryName())) {
            throw new InvalidCountryOrCityException("Country name");
        } else if (!isLatitudeCorrect(localizationDefinition.getLatitude())) {
            throw new IllegalParameterValueException("latitude value: " + localizationDefinition.getLatitude());
        } else if (!isLongitudeCorrect(localizationDefinition.getLongitude())) {
            throw new IllegalParameterValueException("longitude value: " + localizationDefinition.getLongitude());
        }
    }

    boolean isFormatCorrect(String name) {
        Pattern pattern = Pattern.compile("^[a-zA-Z\\u0080-\\u024F]+(?:([\\ \\-\\']|(\\.\\ ))[a-zA-Z\\u0080-\\u024F]+)*$");
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }

    boolean isCountryNameNotEmpty(String name) {
        return !name.isEmpty();
    }

    boolean isCityNameNotEmpty(String name) {
        return !name.isEmpty();
    }

    boolean isLatitudeCorrect(double value) {
        return !(value > 90d) && !(value < -90d);
    }

    boolean isLongitudeCorrect(double value) {
        return !(value > 180d) && !(value < -180d);
    }
}
