package com.sda.weather.exceptions;

public class BlankCountryOrCityException extends IllegalArgumentException {
    public BlankCountryOrCityException(String message) {
        super("Invalid argument: " + message);
    }
}
