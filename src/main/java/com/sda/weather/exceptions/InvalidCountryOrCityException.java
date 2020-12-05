package com.sda.weather.exceptions;

public class InvalidCountryOrCityException extends IllegalArgumentException {
    public InvalidCountryOrCityException(String message) {
        super("INVALID ARGUMENT: " + message);
    }
}
