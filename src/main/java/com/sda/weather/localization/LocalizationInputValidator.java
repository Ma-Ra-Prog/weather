package com.sda.weather.localization;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LocalizationInputValidator {

    boolean isFormatCorrect(String name) {
        Pattern pattern = Pattern.compile("^[a-zA-Z\\u0080-\\u024F]+(?:([\\ \\-\\']|(\\.\\ ))[a-zA-Z\\u0080-\\u024F]+)*$");
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }

    boolean isLatitudeCorrect(float value) {
        return !(value > 90f) && !(value < -90f);
    }

    boolean isLongitudeCorrect(float value) {
        return !(value > 180f) && !(value < -180f);
    }
}
